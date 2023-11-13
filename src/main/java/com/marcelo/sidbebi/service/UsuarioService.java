package com.marcelo.sidbebi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcelo.sidbebi.domain.Usuario;
import com.marcelo.sidbebi.domain.dtos.UsuarioDTO;
import com.marcelo.sidbebi.repositories.UsuarioRepository;
import com.marcelo.sidbebi.service.exceptions.DataIntegrityViolationException;
import com.marcelo.sidbebi.service.exceptions.ObjectnotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. Id: "+id));
	}	
	
	public Usuario create(UsuarioDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorEmail(objDTO);
		Usuario newObj = new Usuario();
		BeanUtils.copyProperties(objDTO, newObj);
		return repository.save(newObj);
	}	
	
	private void validaPorEmail(UsuarioDTO objDTO) {		
		Optional<Usuario> obj = repository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado.");
		}
	}

	public Usuario findByEmail(String email) {
		Optional<Usuario> obj = repository.findByEmail(email);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. E-mail: "+email));
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public void delete(Integer id) {
		@SuppressWarnings("unused")
		Usuario obj = findById(id);
		repository.deleteById(id);
	}

}
