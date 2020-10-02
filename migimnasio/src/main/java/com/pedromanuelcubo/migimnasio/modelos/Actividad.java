package com.pedromanuelcubo.migimnasio.modelos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Actividad extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String nombre;
	private String plazas;
	private Date dia;
	
	
	@ManyToOne
	@JoinColumn(name="sede_id")
	Sede sede;
	

	@ManyToOne
	@JoinColumn(name="entrenador_id")
	Entrenador entrenador;
	

	@ManyToMany(cascade = CascadeType.ALL)
	List<Usuario> listadousuarios;

	public Actividad() {
	}


	public Actividad(Long id, String nombre, String plazas, Date dia, Sede sede, Entrenador entrenador,
			List<Usuario> listadousuarios) {
	
		this.id = id;
		this.nombre = nombre;
		this.plazas = plazas;
		this.dia = dia;
		this.sede = sede;
		this.entrenador = entrenador;
		this.listadousuarios = listadousuarios;
	}



	@JsonBackReference(value="sede-actividad")
	public Sede getSede() {
		return sede;
	}


	public void setSede(Sede sede) {
		this.sede = sede;
	}


	@JsonBackReference(value="actividad-entrenador")
	public Entrenador getEntrenador() {
		return entrenador;
	}


	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}


	public List<Usuario> getListadousuarios() {
		return listadousuarios;
	}


	public void setListadousuarios(List<Usuario> listadousuarios) {
		this.listadousuarios = listadousuarios;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPlazas() {
		return plazas;
	}

	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}


	@Override
	public String toString() {
		return "Actividad [id=" + id + ", nombre=" + nombre + ", plazas=" + plazas + ", dia=" + dia + ", sede=" + sede
				+ ", entrenador=" + entrenador + ", listadousuarios=" + listadousuarios + "]";
	}
	
	

}
