package com.pedromanuelcubo.migimnasio.repositorios;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Provincia;

public interface ProvinciaRepository extends CrudRepository<Provincia, Long> {

	
	@Modifying
	@Query(value="TRUNCATE TABLE provincia", nativeQuery = true)
	void truncateTable();
	

}
