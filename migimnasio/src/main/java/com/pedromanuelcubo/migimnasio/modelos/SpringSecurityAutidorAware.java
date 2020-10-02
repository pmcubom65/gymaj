package com.pedromanuelcubo.migimnasio.modelos;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class SpringSecurityAutidorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		
		return Optional.ofNullable("pedro").filter(s->!s.isEmpty());
	}

}
