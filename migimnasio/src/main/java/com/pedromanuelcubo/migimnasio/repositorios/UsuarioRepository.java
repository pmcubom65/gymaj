package com.pedromanuelcubo.migimnasio.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	
	@Modifying
	@Query("update Usuario s set s.id=?2 where s.id=?1")
	void setUsuarioId(Long idinicial, Long idfinal);
	
	

	@Query(value="SELECT id from usuario where usuariologin_id=?1", nativeQuery = true)
	Integer usuarioEntorno(Long usuariologin_id);
	
	@Query(value="SELECT id from usuario where sede_id=?1", nativeQuery = true)
	List<Integer> usuariosDeLaSede(Long usuariologin_id);
}
