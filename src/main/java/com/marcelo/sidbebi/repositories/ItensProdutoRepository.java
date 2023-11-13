package com.marcelo.sidbebi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.sidbebi.domain.ItensProduto;

public interface ItensProdutoRepository extends JpaRepository<ItensProduto, Integer>{
	
	Optional<ItensProduto> findByCodBarra(String codBarra);

}
