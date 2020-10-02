package com.pedromanuelcubo.migimnasio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Entrenador;
import com.pedromanuelcubo.migimnasio.modelos.Sede;

public interface SedeRepository extends CrudRepository<Sede, Long> {

	
	@Modifying
	@Query("update Sede s set s.id=?2 where s.id=?1")
	void setSedeId(Long idinicial, Long idfinal);
	
}
