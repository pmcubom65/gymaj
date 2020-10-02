package com.pedromanuelcubo.migimnasio.repositorios;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.modelos.UsuarioLogin;



public interface UsuarioLoginRepository extends CrudRepository<UsuarioLogin, Long> {

	
	UsuarioLogin findByEmail(String email);
	
	
	
	@Query(value="SELECT id from usuario_login where email=?1", nativeQuery = true)
	Integer getUsuarioId(String email);
	
	
}
