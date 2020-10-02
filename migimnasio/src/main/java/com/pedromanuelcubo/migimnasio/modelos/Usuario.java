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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Usuario extends Auditable<String>  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String nombre;

	private String domicilio;

	@Column(unique = true, nullable = false)
	private String dni;
	
	private Date fechanacimiento;

	private String telefono;
	
	@ManyToOne
	@JoinColumn(name="sede_id")
	private Sede sede;
	
	@ManyToMany(mappedBy="listadousuarios")
	private List<Actividad> listadoactividades;

	@OneToOne(cascade = {CascadeType.ALL})
	Provincia provincia;

	@OneToOne(cascade = {CascadeType.ALL})
	UsuarioLogin usuariologin;

	public Usuario(Long id, String nombre, String domicilio, String dni, Date fechanacimiento, String telefono,
			Sede sede, List<Actividad> listadoactividades, Provincia provincia, UsuarioLogin usuariologin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.dni = dni;
		this.fechanacimiento = fechanacimiento;
		this.telefono = telefono;
		this.sede = sede;
		this.listadoactividades = listadoactividades;
		this.provincia = provincia;
		this.usuariologin=usuariologin;
	}







	public Usuario() {
	}

	
	
	
	
	
	
	public UsuarioLogin getUsuariologin() {
		return usuariologin;
	}







	public void setUsuariologin(UsuarioLogin usuariologin) {
		this.usuariologin = usuariologin;
	}







	public Provincia getProvincia() {
		return provincia;
	}







	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}







	public String getDni() {
		return dni;
	}







	public void setDni(String dni) {
		this.dni = dni;
	}






	@JsonBackReference(value="sede")
	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	
	@JsonIgnore
	public List<Actividad> getListadoactividades() {
		return listadoactividades;
	}

	public void setListadoactividades(List<Actividad> listadoactividades) {
		this.listadoactividades = listadoactividades;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
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







	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", domicilio=" + domicilio + ", dni=" + dni
				+ ", fechanacimiento=" + fechanacimiento + ", telefono=" + telefono + ", sede=" + sede
				+ ", listadoactividades=" + listadoactividades + ", provincia=" + provincia + ", usuariologin="
				+ usuariologin + "]";
	}

















	
	
	
	
	
}
