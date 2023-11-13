package com.marcelo.sidbebi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.marcelo.sidbebi.service.DBService;

//Esta classe configura que o DBService (camada de serviço) irá utilizar o perfil "test" do .porperties para instanciar e persistir os objetos do BD
@Configuration
@Profile("dev")
public class DevConfig { 
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String valueString;
	
	@Bean
	public boolean instanciaDB() {
		if(valueString.equals("create")) {//esta condição verifica se a propriedade create do .properties está acionada, para criar as instancias
			this.dbService.instanciaDB();
		}
		return false;
	}

}
