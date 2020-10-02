package com.pedromanuelcubo.migimnasio.repositorios;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;

public interface ActividadRepository extends CrudRepository<Actividad, Long> {

	@Modifying
	@Query("update Actividad s set s.id=?2 where s.id=?1")
	void setActividadId(Long idinicial, Long idfinal);
	
	
}
