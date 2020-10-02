package com.pedromanuelcubo.migimnasio.services;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.modelos.UsuarioLogin;
import com.pedromanuelcubo.migimnasio.repositorios.UsuarioLoginRepository;
import com.pedromanuelcubo.migimnasio.security.ApplicationRoles;


@Service
public class UsuarioLoginService implements UserDetailsService  {

	
	@Autowired
	private UsuarioLoginRepository usuariologinrepositorio;
	

	
	private final PasswordEncoder passwordEncoder;
	

	@Autowired
	public UsuarioLoginService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
	}
	
	
	public UsuarioLogin saveUsuario(String email, String password, String role) {
		UsuarioLogin usuarioLogin=new UsuarioLogin();
		
		usuarioLogin.setEmail(email);
		usuarioLogin.setPassword(passwordEncoder.encode(password));
		usuarioLogin.setRole(ApplicationRoles.valueOf(role));
		
		return  this.usuariologinrepositorio.save(usuarioLogin);
		

	}
	
	
	public void saveUsuarioEntity(UsuarioLogin usuariologin) {
		
		usuariologin.setPassword(passwordEncoder.encode(usuariologin.getPassword()));
		this.usuariologinrepositorio.save(usuariologin);
	}
	
	
	
	
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		   UsuarioLogin usuariologin = usuariologinrepositorio.findByEmail(email);
		   
		   if (usuariologin == null) {
	            throw new UsernameNotFoundException(email);
	        }
		   
			UserDetails usuariodetails= User.builder().username(email).password(usuariologin.getPassword())
					.roles(usuariologin.getRole().name()).build();
		   
		   
		   
	        return usuariodetails;
		
		
	}
	
	
	@Transactional
	public Integer getUsuarioLogin(String email) {
		
		return usuariologinrepositorio.getUsuarioId(email);
	}
	
	
	public UsuarioLogin getUsuarioLoginById(Long id) {
		return usuariologinrepositorio.findById(id).get();
	}
	
	
	public UsuarioLogin updateUsuarioLogin(Long id, String email, String password) {
		UsuarioLogin ul= this.getUsuarioLoginById(id);
		ul.setEmail(email);
		ul.setPassword(this.passwordEncoder.encode(password));
		return usuariologinrepositorio.save(ul);

	}
	
	
	
	
	
}
