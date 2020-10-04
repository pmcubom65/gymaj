package com.pedromanuelcubo.migimnasio.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Sede extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nombre;
	private String direccion;

	@Transient
	@OneToMany(mappedBy = "sede")
	@Where(clause = "deleted=false")
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "sede")
	@Where(clause = "deleted=false")
	private List<Entrenador> entrenadores;

	@OneToMany(mappedBy = "sede")
	@Where(clause = "deleted=false")
	private List<Actividad> actividades;

	@OneToOne(cascade = CascadeType.MERGE)
	private Provincia provincia;

	public Sede() {
	}

	public Sede(Long id, String nombre, String direccion, List<Usuario> usuarios, List<Entrenador> entrenadores,
			List<Actividad> actividades, Provincia provincia) {

		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.usuarios = usuarios;
		this.entrenadores = entrenadores;
		this.actividades = actividades;
		this.provincia = provincia;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@JsonManagedReference(value = "sede")
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	// @JsonManagedReference(value="sede-entrenador")
	// @JsonBackReference(value="sede-entrenador")
	@JsonManagedReference(value = "sede-entrenador")
	public List<Entrenador> getEntrenadores() {
		return entrenadores;
	}

	public void setEntrenadores(List<Entrenador> entrenadores) {
		this.entrenadores = entrenadores;
	}

	@JsonManagedReference(value = "sede-actividad")
	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Sede [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", usuarios=" + usuarios
				+ ", entrenadores=" + entrenadores + ", actividades=" + actividades + ", provincia=" + provincia + "]";
	}

}
