package com.marcelo.sidbebi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.sidbebi.domain.ItensVenda;
import com.marcelo.sidbebi.domain.Venda;

public interface ItensVendaRepository extends JpaRepository<ItensVenda, Integer>{
	
	List<ItensVenda> findByItem(String item);
	
	List<ItensVenda> findByVenda(Venda venda);

}
