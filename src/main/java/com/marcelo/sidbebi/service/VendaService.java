package com.marcelo.sidbebi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.ItensVenda;
import com.marcelo.sidbebi.domain.Venda;
import com.marcelo.sidbebi.domain.dtos.ItensProdutoDTO;
import com.marcelo.sidbebi.domain.dtos.ItensVendaDTO;
import com.marcelo.sidbebi.domain.dtos.VendaDTO;
import com.marcelo.sidbebi.repositories.FornecedorRepository;
import com.marcelo.sidbebi.repositories.ItensVendaRepository;
import com.marcelo.sidbebi.repositories.ProdutoRepository;
import com.marcelo.sidbebi.repositories.VendaRepository;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class VendaService {
	
	@Autowired
	private VendaRepository repository;
	
	@Autowired
	private ItensVendaService itensVendaService;
	
	@Autowired
	private ItensVendaRepository itensVendaRepository;
	
	@Autowired
	private ItensProdutoService itensProdutoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	public Venda findById(Integer id) {
		Optional<Venda> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}
	
	public List<Venda> findAll() {
		return repository.findAll();
	}
	
	public Venda create(VendaDTO objDTO) {
		objDTO.setId(null);		
		Venda venda = itensVendaService.create(objDTO);		
		return venda;
	}	

	public Venda update(Integer id, VendaDTO objDTO) {
		objDTO.setId(id);
		Venda oldObj = findById(id);
		oldObj = new Venda();
		BeanUtils.copyProperties(objDTO, oldObj);
		return repository.save(oldObj);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ItensVendaDTO> find(String data){
		String dia1 = data.substring(0, 2);
		String mes1 = data.substring(2, 4);
		String ano1 = data.substring(4, 8);
		
		String dia2 = data.substring(8, 10);
		String mes2 = data.substring(10, 12);
		String ano2 = data.substring(12, 16);
		
		String inicial = ano1+"-"+mes1+"-"+dia1;
		String finall = ano2+"-"+mes2+"-"+dia2;
		
		LocalDate dataInicial = LocalDate.parse(inicial);
		LocalDate dataFinal = LocalDate.parse(finall);
		
		List<Venda> vendas = repository.findByIntervalo(dataInicial, dataFinal);
		List<ItensVendaDTO> itensVendaDTO = new ArrayList();		
		for(Venda venda : vendas) {				
			List<ItensVenda> listItensVenda = itensVendaRepository.findByVenda(venda);
			for(ItensVenda itensVenda : listItensVenda) {
				ItensVendaDTO itensDTO = new ItensVendaDTO();
				BeanUtils.copyProperties(itensVenda, itensDTO);
				itensVendaDTO.add(itensDTO);
			}
		}		
		return itensVendaDTO;		
	}

	public void delete(Integer id) {
		Venda obj = findById(id);
		List<ItensVenda> itensVenda = itensVendaRepository.findByVenda(obj);
		for(ItensVenda itens : itensVenda) {
			ItensProdutoDTO itensProdutoDTO = new ItensProdutoDTO();
			itensProdutoDTO.setNomeProduto(itens.getItem());
			itensProdutoDTO.setNomeFornecedor(itens.getFornecedor());
			itensProdutoDTO.setProduto(produtoRepository.findByNome(itens.getItem()).get().getId());
			itensProdutoDTO.setFornecedor(fornecedorRepository.findByNome(itens.getFornecedor()).get().getId());
			itensProdutoDTO.setCodBarra(itens.getCodBarra());
			itensProdutoService.create(itensProdutoDTO);
			itensVendaRepository.delete(itens);
		}		
		repository.deleteById(id);
		
	}
	
}
