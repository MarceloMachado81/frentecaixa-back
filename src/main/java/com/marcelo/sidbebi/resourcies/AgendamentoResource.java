package com.marcelo.sidbebi.resourcies;

import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcelo.sidbebi.domain.Agendamento;
import com.marcelo.sidbebi.domain.dtos.AgendamentoDTO;
import com.marcelo.sidbebi.service.AgendamentoService;

@RestController
@RequestMapping(value="/agendamentos")
public class AgendamentoResource {
	
	@Autowired
	private AgendamentoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AgendamentoDTO> findById(@PathVariable Integer id){
		Agendamento obj = service.findById(id);
		return ResponseEntity.ok().body(new AgendamentoDTO(obj));
	}
		
	@GetMapping
	public ResponseEntity<List<AgendamentoDTO>> findAll(){
		List<Agendamento> list = service.findAll();
		List<AgendamentoDTO> listDTO = list.stream().map(obj -> new AgendamentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<AgendamentoDTO> create(@Valid @RequestBody AgendamentoDTO objDTO){
		Agendamento newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AgendamentoDTO> update(@PathVariable Integer id, @Valid @RequestBody AgendamentoDTO objDTO) {
		Agendamento obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new AgendamentoDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<AgendamentoDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
