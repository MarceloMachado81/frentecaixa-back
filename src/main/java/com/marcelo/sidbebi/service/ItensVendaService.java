package com.marcelo.sidbebi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.ItensProduto;
import com.marcelo.sidbebi.domain.ItensVenda;
import com.marcelo.sidbebi.domain.Produto;
import com.marcelo.sidbebi.domain.Venda;
import com.marcelo.sidbebi.domain.dtos.ItensProdutoDTO;
import com.marcelo.sidbebi.domain.dtos.ItensVendaDTO;
import com.marcelo.sidbebi.domain.dtos.VendaDTO;
import com.marcelo.sidbebi.repositories.ItensProdutoRepository;
import com.marcelo.sidbebi.repositories.ItensVendaRepository;
import com.marcelo.sidbebi.repositories.ProdutoRepository;
import com.marcelo.sidbebi.repositories.VendaRepository;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class ItensVendaService {
	
	@Autowired
	private ItensVendaRepository repository;
	
	@Autowired
	private ItensProdutoRepository itensProdutoRepository;
	
	@Autowired
	private ItensProdutoService itensProdutoService;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public ItensVenda findById(Integer id) {
		Optional<ItensVenda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}	
	
	public Venda create(VendaDTO vendaDTO) {
		Venda venda = new Venda();		
		BeanUtils.copyProperties(vendaDTO, venda);
		venda.setPagamento(vendaDTO.getPagamento().toString());	
		
		String dataHoraString = venda.getDataHoraRegistro().toString();
		
		String dia = dataHoraString.substring(8, 10);
		String mes = dataHoraString.substring(5,7);
		String ano = dataHoraString.substring(0, 4);
		
		LocalDate dataHoraLocalDate = LocalDate.parse(ano+"-"+mes+"-"+dia);
		
		venda.setDataHora(dataHoraLocalDate);
		
		venda = vendaRepository.save(venda);
		
		for(String codBarras : venda.getItensVenda()) {			
			Optional<ItensProduto> item = itensProdutoRepository.findByCodBarra(codBarras);
			Produto produto = item.get().getProduto();
			produto.setQtd(produto.getQtd()-1);
			produto.setValorTotal(produto.getValorTotal() - produto.getValorUnit());
			produtoRepository.save(produto);
		}
		
		for(int x = 0; x < vendaDTO.getItensVenda().length; x++){						
			ItensVenda itensVenda = new ItensVenda();
			Optional<ItensProduto> itensProduto = itensProdutoRepository.findByCodBarra(vendaDTO.getItensVenda()[x]);
			itensVenda.setCodBarra(itensProduto.get().getCodBarra());
			itensVenda.setValorUnit(itensProduto.get().getProduto().getValorUnit());			
			itensVenda.setFornecedor(itensProduto.get().getFornecedor().getNome());
			itensVenda.setItem(itensProduto.get().getProduto().getNome());
			itensVenda.setTamanho(itensProduto.get().getProduto().getTamanho());
			itensVenda.setVenda(venda);		
			repository.save(itensVenda);			
			itensProdutoRepository.deleteById(itensProduto.get().getId());
		}	
		
		return venda;
	}	
	
	public List<ItensVenda> findAll() {
		return repository.findAll();
	}

	public ItensVenda update(Integer id, ItensVendaDTO objDTO) {
		objDTO.setId(id);
		ItensVenda oldObj = findById(id);
		oldObj = new ItensVenda();
		BeanUtils.copyProperties(objDTO, oldObj);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		ItensVenda obj = findById(id);
		ItensProdutoDTO itensProdutoDTO = new  ItensProdutoDTO();
		itensProdutoDTO.setCodBarra(obj.getCodBarra());
		itensProdutoService.create(itensProdutoDTO);
		repository.deleteById(id);
	}
	
}
