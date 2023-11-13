package com.marcelo.sidbebi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.sidbebi.domain.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>{

	Optional<Fornecedor> findByNome(String nome);
	
	Optional<Fornecedor> findByCpfCnpj(String cpfCnpj);
	
	Optional<Fornecedor> findByEmail(String email);

}
