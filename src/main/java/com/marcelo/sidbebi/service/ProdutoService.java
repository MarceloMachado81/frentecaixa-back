package com.marcelo.sidbebi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.Produto;
import com.marcelo.sidbebi.domain.dtos.ProdutoDTO;
import com.marcelo.sidbebi.domain.enums.Tipo;
import com.marcelo.sidbebi.repositories.ProdutoRepository;
import com.marcelo.sidbebi.service.exceptions.DataIntegrityViolationException;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}
	
	public Produto findByNome(String produto) {
		Optional<Produto> obj = repository.findByNome(produto);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado: "+produto));
	}
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto create(ProdutoDTO objDTO) {			
		objDTO.setId(null);
		Produto produto = new Produto();
		objDTO.setValorTotal(objDTO.getQtd() * objDTO.getValorUnit());
		BeanUtils.copyProperties(objDTO, produto);
		produto.setTipo(Tipo.toEnum(objDTO.getTipo()));
		return repository.save(produto);
	}

	public Produto update(Integer id, ProdutoDTO objDTO) {
		objDTO.setId(id);
		Produto oldObj = findById(id);
		oldObj = new Produto();
		BeanUtils.copyProperties(objDTO, oldObj);
		oldObj.setTipo(Tipo.toEnum(objDTO.getTipo()));
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Produto obj = findById(id);

		if (obj.getItens().size() > 0) {
			throw new DataIntegrityViolationException("Este Produto possui itens associados a ele e não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	
}
