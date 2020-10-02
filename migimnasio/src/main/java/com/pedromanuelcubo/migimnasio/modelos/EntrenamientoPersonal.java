package com.pedromanuelcubo.migimnasio.modelos;

import java.time.LocalDate;

import javax.persistence.OneToOne;

public class EntrenamientoPersonal {

	@OneToOne
	Entrenador entrenador;
	
	@OneToOne
	Usuario usuario;
	
	LocalDate dia;
	
	
	public EntrenamientoPersonal() {}


	public EntrenamientoPersonal(Entrenador entrenador, Usuario usuario, LocalDate dia) {

		this.entrenador = entrenador;
		this.usuario = usuario;
		this.dia = dia;
	}


	public Entrenador getEntrenador() {
		return entrenador;
	}


	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public LocalDate getDia() {
		return dia;
	}


	public void setDia(LocalDate dia) {
		this.dia = dia;
	}
	
	
	
	
}
