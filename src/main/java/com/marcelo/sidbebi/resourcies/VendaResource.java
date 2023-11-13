package com.marcelo.sidbebi.resourcies;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcelo.sidbebi.domain.Venda;
import com.marcelo.sidbebi.domain.dtos.VendaDTO;
import com.marcelo.sidbebi.service.VendaService;

@RestController
@RequestMapping(value="/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<VendaDTO> findById(@PathVariable Integer id){
		Venda obj = service.findById(id);
		return ResponseEntity.ok().body(new VendaDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<VendaDTO>> findAll(){
		List<Venda> list = service.findAll();
		List<VendaDTO> listDTO = list.stream().map(obj -> new VendaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<VendaDTO> create(@Valid @RequestBody VendaDTO objDTO){
		Venda newObj = service.create(objDTO);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<VendaDTO> update(@PathVariable Integer id, @Valid @RequestBody VendaDTO objDTO) {
		Venda obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new VendaDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<VendaDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
