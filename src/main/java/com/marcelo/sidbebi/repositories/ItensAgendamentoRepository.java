package com.marcelo.sidbebi.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcelo.sidbebi.domain.ItensAgendamento;

public interface ItensAgendamentoRepository extends JpaRepository<ItensAgendamento, Integer>{
	
	Optional<ItensAgendamento> findByCodBarra(String codBarra);
	
}
