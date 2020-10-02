package com.pedromanuelcubo.migimnasio;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;




 

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class MigimnasioApplication {
	

	
	@Bean
	public AuditorAware<String> auditorAware(){
		return new com.pedromanuelcubo.migimnasio.modelos.SpringSecurityAutidorAware();
	}
	
	

	public static void main(String[] args) {
	

		SpringApplication.run(MigimnasioApplication.class, args);
		
	}

	
	
	
	
}



