package com.pedromanuelcubo.migimnasio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Entrenador;

public interface EntrenadorRepository extends CrudRepository<Entrenador, Long> {

	@Modifying
	@Query("update Entrenador s set s.id=?2 where s.id=?1")
	void setEntrenadorId(Long idinicial, Long idfinal);
	
	
	@Query(value="SELECT id from entrenador where usuariologin_id=?1", nativeQuery = true)
	Integer entrenadorEntorno(Long usuariologin_id);
	
}
