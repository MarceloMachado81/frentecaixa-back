package com.marcelo.sidbebi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.Fornecedor;
import com.marcelo.sidbebi.domain.ItensProduto;
import com.marcelo.sidbebi.domain.Produto;
import com.marcelo.sidbebi.domain.dtos.ItensProdutoDTO;
import com.marcelo.sidbebi.domain.enums.NivelEstoque;
import com.marcelo.sidbebi.repositories.FornecedorRepository;
import com.marcelo.sidbebi.repositories.ItensProdutoRepository;
import com.marcelo.sidbebi.repositories.ProdutoRepository;
import com.marcelo.sidbebi.service.exceptions.DataIntegrityViolationException;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class ItensProdutoService {
	
	@Autowired
	private ItensProdutoRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public ItensProduto findById(Integer id) {
		Optional<ItensProduto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}
	
	public ItensProduto findByCodBarra(String codBarra) {
		Optional<ItensProduto> obj = repository.findByCodBarra(codBarra);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Item: "+codBarra));
	}
	
	public List<ItensProduto> findAll() {
		return repository.findAll();
	}
	
	public ItensProduto findByCodBarra(ItensProdutoDTO objDTO) {
		Optional<ItensProduto> obj = repository.findByCodBarra(objDTO.getCodBarra());
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Código de Barra: "+objDTO.getCodBarra()));
	}

	public ItensProduto create(ItensProdutoDTO objDTO) {
		objDTO.setId(null);
		validaPorCodBarras(objDTO);
		Optional<Produto> produto = produtoRepository.findById(objDTO.getProduto());
		produto.get().setQtd(produto.get().getQtd() + 1);
		produto.get().setValorTotal(produto.get().getValorUnit() * produto.get().getQtd());
		produto.get().setNivel(nivelEstoque(produto.get().getQtd()));
		produtoRepository.save(produto.get());
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(objDTO.getFornecedor());
		ItensProduto newObj = new ItensProduto();
		newObj.setProduto(produto.get());
		newObj.setFornecedor(fornecedor.get());
		BeanUtils.copyProperties(objDTO, newObj);
		return repository.save(newObj);
	}	

	public ItensProduto update(Integer id, ItensProdutoDTO objDTO) {
		objDTO.setId(id);
		ItensProduto oldObj = findById(id);
		oldObj = new ItensProduto();
		BeanUtils.copyProperties(objDTO, oldObj);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Optional<ItensProduto> itens = repository.findById(id);
		Optional<Produto> produto = produtoRepository.findById(itens.get().getProduto().getId());
		produto.get().setQtd(produto.get().getQtd() - 1);
		produto.get().setValorTotal(produto.get().getValorUnit() * produto.get().getQtd());
		produto.get().setNivel(nivelEstoque(produto.get().getQtd()));
		produtoRepository.save(produto.get());
		@SuppressWarnings("unused")
		ItensProduto obj = findById(id);
		repository.deleteById(id);
	}
	
	public NivelEstoque nivelEstoque(Integer qtd) {
		NivelEstoque nivel;
		if(qtd <= 20) {
			nivel = NivelEstoque.BAIXO;
		}
		else {
			if (qtd >20 && qtd <=60) {
				nivel = NivelEstoque.NORMAL;
			}
			else
				nivel = NivelEstoque.ALTO;
		}		
		return nivel;
	}
	
	private void validaPorCodBarras(ItensProdutoDTO objDTO) {
		Optional<ItensProduto> obj = repository.findByCodBarra(objDTO.getCodBarra());
		if(obj.isPresent()) {
			throw new DataIntegrityViolationException("Código de Barras já cadastrado no sistema.");
		}
	}
	
}
