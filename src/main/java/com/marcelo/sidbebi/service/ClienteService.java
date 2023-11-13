package com.marcelo.sidbebi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.Cliente;
import com.marcelo.sidbebi.domain.dtos.ClienteDTO;
import com.marcelo.sidbebi.repositories.ClienteRepository;
import com.marcelo.sidbebi.service.exceptions.DataIntegrityViolationException;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEmail(objDTO);
		Cliente newObj = new Cliente();
		BeanUtils.copyProperties(objDTO, newObj);
		return repository.save(newObj);
	}	

	public Cliente update(Integer id, ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEmail(objDTO);
		oldObj = new Cliente();
		BeanUtils.copyProperties(objDTO, oldObj);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		@SuppressWarnings("unused")
		Cliente obj = findById(id);
		repository.deleteById(id);
	}
	
	private void validaPorCpfEmail(ClienteDTO objDTO) {
		Optional<Cliente> obj = repository.findByCpfCnpj(objDTO.getCpfCnpj());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF/CNPJ já cadastrado no sistema.");
		}
		
		obj = repository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
		}
	}

}
