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

import com.marcelo.sidbebi.domain.ItensProduto;
import com.marcelo.sidbebi.domain.dtos.ClienteDTO;
import com.marcelo.sidbebi.domain.dtos.ItensProdutoDTO;
import com.marcelo.sidbebi.service.ItensProdutoService;

@RestController
@RequestMapping(value="/itensprodutos")
public class ItensProdutoResource {
	
	@Autowired
	private ItensProdutoService service;
	
	@GetMapping(value = "/{codBarra}") 
	public ResponseEntity<ItensProdutoDTO> findByCodBarra(@PathVariable String codBarra){
		ItensProduto obj = service.findByCodBarra(codBarra);
		return ResponseEntity.ok().body(new ItensProdutoDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ItensProdutoDTO>> findAll(){
		List<ItensProduto> list = service.findAll();
		List<ItensProdutoDTO> listDTO = list.stream().map(obj -> new ItensProdutoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ItensProdutoDTO> create(@Valid @RequestBody ItensProdutoDTO objDTO){
		ItensProduto newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ItensProdutoDTO> update(@PathVariable Integer id, @Valid @RequestBody ItensProdutoDTO objDTO) {
		ItensProduto obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ItensProdutoDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}


}
