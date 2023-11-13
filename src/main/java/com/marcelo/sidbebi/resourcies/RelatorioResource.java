package com.marcelo.sidbebi.resourcies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.sidbebi.domain.dtos.ItensVendaDTO;
import com.marcelo.sidbebi.service.VendaService;

@RestController
@RequestMapping(value="/relatorios")
public class RelatorioResource {
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping(value = "/{data}") 
	public ResponseEntity<List<ItensVendaDTO>> find(@PathVariable String data){
		List<ItensVendaDTO> obj = vendaService.find(data);
		return ResponseEntity.ok().body(obj);
	}
	
	/**	
	@GetMapping
	public ResponseEntity<List<VendaDTO>> findAll(){
		List<Venda> list = vendaService.findAll();
		List<VendaDTO> listDTO = list.stream().map(obj -> new VendaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	**/
}