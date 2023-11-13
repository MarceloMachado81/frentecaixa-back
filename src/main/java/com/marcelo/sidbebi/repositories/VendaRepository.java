package com.marcelo.sidbebi.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marcelo.sidbebi.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer>{
	
	@Query("SELECT t FROM Venda t WHERE t.dataHora BETWEEN :di and :df")
	List<Venda> findByIntervalo(@Param("di") LocalDate dataInicial, @Param("df") LocalDate dataFinal);
	
}
