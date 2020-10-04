package com.pedromanuelcubo.migimnasio.repositorios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Entrenador;
import com.pedromanuelcubo.migimnasio.modelos.Sede;

public interface SedeRepository extends CrudRepository<Sede, Long> {

	
	@Modifying
	@Query("update Sede s set s.id=?2 where s.id=?1")
	void setSedeId(Long idinicial, Long idfinal);
	
	@Transactional
	@Modifying
	@Query(value ="SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
	void iniciarBorrado();
	
	@Transactional
	@Modifying
	@Query(value ="SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
	void acabarBorrado();
	
	
}
