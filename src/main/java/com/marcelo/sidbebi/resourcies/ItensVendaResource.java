package com.marcelo.sidbebi.resourcies;

import java.util.List;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.sidbebi.domain.ItensVenda;
import com.marcelo.sidbebi.domain.dtos.ItensVendaDTO;
import com.marcelo.sidbebi.service.ItensVendaService;

@RestController
@RequestMapping(value="/itensvendas")
public class ItensVendaResource {
	
	@Autowired
	private ItensVendaService service;
	
	@GetMapping(value = "/{id}") 
	public ResponseEntity<ItensVendaDTO> findById(@PathVariable Integer id){
		ItensVenda obj = service.findById(id);
		return ResponseEntity.ok().body(new ItensVendaDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ItensVendaDTO>> findAll(){
		List<ItensVenda> list = service.findAll();
		List<ItensVendaDTO> listDTO = list.stream().map(obj -> new ItensVendaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ItensVendaDTO> update(@PathVariable Integer id, @Valid @RequestBody ItensVendaDTO objDTO) {
		ItensVenda obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ItensVendaDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ItensVendaDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
