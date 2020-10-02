package com.pedromanuelcubo.migimnasio.modelos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Entrenador extends Auditable<String>  {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	private String nombre;
	
	
	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="sede_id")
	private Sede sede;
	
	@OneToMany(mappedBy="entrenador")
	private List<Actividad> listadoactividades;
	
	
	@Column(unique = true, nullable = false)
	private String dni;
	

	
	@OneToOne(cascade = {CascadeType.ALL})
	UsuarioLogin usuariologin;
	
	
	private String descripcion;
	
	
	
	public Entrenador() {}


	public Entrenador(Long id, String nombre,  String telefono, Sede sede,
			List<Actividad> listadoactividades, String dni, UsuarioLogin usuariologin, String descripcion) {

		this.id = id;
		this.nombre = nombre;
	
		this.telefono = telefono;
		this.sede = sede;
		this.listadoactividades = listadoactividades;
		this.dni = dni;
		this.descripcion=descripcion;
		this.usuariologin=usuariologin;
	}


	
	
	
	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public UsuarioLogin getUsuariologin() {
		return usuariologin;
	}


	public void setUsuariologin(UsuarioLogin usuariologin) {
		this.usuariologin = usuariologin;
	}





	public String getDni() {
		return dni;
	}




	public void setDni(String dni) {
		this.dni = dni;
	}




	//@JsonBackReference(value="sede-entrenador")
	//@JsonManagedReference(value="sede-entrenador")
	@JsonBackReference(value="sede-entrenador")
	public Sede getSede() {
		return sede;
	}








	public void setSede(Sede sede) {
		this.sede = sede;
	}







	@JsonManagedReference(value="actividad-entrenador")
	public List<Actividad> getListadoactividades() {
		return listadoactividades;
	}








	public void setListadoactividades(List<Actividad> listadoactividades) {
		this.listadoactividades = listadoactividades;
	}








	public String getTelefono() {
		return telefono;
	}





	public void setTelefono(String telefono) {
		this.telefono = telefono;
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






	
	
	
	
}
