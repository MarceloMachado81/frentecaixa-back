package com.marcelo.sidbebi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.sidbebi.domain.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{
	
}
