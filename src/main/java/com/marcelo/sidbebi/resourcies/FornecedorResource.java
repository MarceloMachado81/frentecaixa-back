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
import com.marcelo.sidbebi.domain.Fornecedor;
import com.marcelo.sidbebi.domain.dtos.FornecedorDTO;
import com.marcelo.sidbebi.service.FornecedorService;

@RestController
@RequestMapping(value="/fornecedores")
public class FornecedorResource {
	
	@Autowired
	private FornecedorService service;
	
	@GetMapping(value = "/{id}") //variável de path
	public ResponseEntity<FornecedorDTO> findById(@PathVariable Integer id){
		Fornecedor obj = service.findById(id);
		return ResponseEntity.ok().body(new FornecedorDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<FornecedorDTO>> findAll(){
		List<Fornecedor> list = service.findAll();
		List<FornecedorDTO> listDTO = list.stream().map(obj -> new FornecedorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<FornecedorDTO> create(@Valid @RequestBody FornecedorDTO objDTO){
		Fornecedor newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<FornecedorDTO> update(@PathVariable Integer id, @Valid @RequestBody FornecedorDTO objDTO) {
		Fornecedor obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new FornecedorDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FornecedorDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
	
}
