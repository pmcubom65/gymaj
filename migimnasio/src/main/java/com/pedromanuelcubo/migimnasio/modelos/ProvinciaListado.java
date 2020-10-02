package com.pedromanuelcubo.migimnasio.modelos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class ProvinciaListado {
	

	
	private List<String> provincias;
	
	
	public ProvinciaListado() {}


	


	public ProvinciaListado(List<String> provincias) {

		this.provincias = provincias;
	}





	public List<String> getProvincias() {
		return provincias;
	}


	public void setProvincias(List<String> provincias) {
		this.provincias = provincias;
	}


	@Override
	public String toString() {
		return "Provincia [provincias=" + provincias + "]";
	}
	
	

}