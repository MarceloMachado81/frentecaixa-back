package com.marcelo.sidbebi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.Fornecedor;
import com.marcelo.sidbebi.domain.dtos.FornecedorDTO;
import com.marcelo.sidbebi.repositories.FornecedorRepository;
import com.marcelo.sidbebi.service.exceptions.DataIntegrityViolationException;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repository;
	
	public Fornecedor findById(Integer id) {
		Optional<Fornecedor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}

	public List<Fornecedor> findAll() {
		return repository.findAll();
	}
	
	public Fornecedor create(FornecedorDTO objDTO) {			
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);		
		Fornecedor fornecedor = new Fornecedor();		
		BeanUtils.copyProperties(objDTO, fornecedor);		
		return repository.save(fornecedor);
	}
	
	public Fornecedor update(Integer id, @Valid FornecedorDTO objDTO) {
		objDTO.setId(id);
		Fornecedor oldObj = findById(id);
		validaPorCpfEmail(objDTO);	
		BeanUtils.copyProperties(objDTO, oldObj);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Fornecedor obj = findById(id);
		if (obj.getItens().size() > 0) {
			throw new DataIntegrityViolationException("Este Fornecedor possui itens associados a ele e não pode ser deletado!");
		}
		repository.deleteById(id);
	}
	
	private void validaPorCpfEmail(FornecedorDTO objDTO) {
		Optional<Fornecedor> obj = repository.findByCpfCnpj(objDTO.getCpfCnpj());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF/CNPJ já cadastrado no sistema.");
		}
		
		obj = repository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
		}
	}
}
