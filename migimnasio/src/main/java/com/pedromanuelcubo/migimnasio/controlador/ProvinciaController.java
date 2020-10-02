package com.pedromanuelcubo.migimnasio.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedromanuelcubo.migimnasio.modelos.Provincia;
import com.pedromanuelcubo.migimnasio.services.ProvinciaService;

@RestController
@CrossOrigin
public class ProvinciaController {

	@Autowired
	private ProvinciaService provinciaservice;
	
	@RequestMapping(value="/provincias")
	public List<Provincia> getAllProvincias() {
		
		return provinciaservice.getAllProvincias();
		
	}
	
}
