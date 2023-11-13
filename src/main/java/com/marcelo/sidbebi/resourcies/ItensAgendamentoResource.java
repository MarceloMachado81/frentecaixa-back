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

import com.marcelo.sidbebi.domain.ItensAgendamento;
import com.marcelo.sidbebi.domain.dtos.ItensAgendamentoDTO;
import com.marcelo.sidbebi.service.ItensAgendamentoService;

@RestController
@RequestMapping(value="/itensagendamentos")
public class ItensAgendamentoResource {
	
	@Autowired
	private ItensAgendamentoService service;
	
	@GetMapping(value = "/{codBarra}") 
	public ResponseEntity<ItensAgendamentoDTO> findByCodBarra(@PathVariable String codBarra){
		ItensAgendamento obj = service.findByCodBarra(codBarra);
		return ResponseEntity.ok().body(new ItensAgendamentoDTO(obj));
	}
	
	/**@GetMapping(value = "/{id}") 
	public ResponseEntity<ItensAgendamentoDTO> findById(@PathVariable Integer id){
		ItensAgendamento obj = service.findById(id);
		return ResponseEntity.ok().body(new ItensAgendamentoDTO(obj));
	}
	**/
	@GetMapping
	public ResponseEntity<List<ItensAgendamentoDTO>> findAll(){
		List<ItensAgendamento> list = service.findAll();
		List<ItensAgendamentoDTO> listDTO = list.stream().map(obj -> new ItensAgendamentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ItensAgendamentoDTO> update(@PathVariable Integer id, @Valid @RequestBody ItensAgendamentoDTO objDTO) {
		ItensAgendamento obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ItensAgendamentoDTO(obj));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ItensAgendamentoDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
