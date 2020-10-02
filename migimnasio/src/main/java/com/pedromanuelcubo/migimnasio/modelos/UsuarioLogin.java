package com.pedromanuelcubo.migimnasio.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.pedromanuelcubo.migimnasio.security.ApplicationRoles;

@Entity
public class UsuarioLogin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(unique=true)
	private String email;

	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	private ApplicationRoles role;
	
	
	

	public UsuarioLogin(Long id, String email, String password, ApplicationRoles role) {
	
		this.id = id;
		this.email = email;
		this.password = password;
		this.role=role;

	}
	
	
	public UsuarioLogin() {}




	public Long getId() {
		return id;
	}
	
	

	public ApplicationRoles getRole() {
		return role;
	}


	public void setRole(ApplicationRoles role) {
		this.role = role;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
