package com.marcelo.sidbebi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelo.sidbebi.service.DBService;

//Esta classe configura que o DBService (camada de serviço) irá utilizar o perfil "test" do .porperties para instanciar e persistir os objetos do BD
@Configuration
@Profile("test")
public class TestConfig { 
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}

}
