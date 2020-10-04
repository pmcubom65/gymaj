package com.pedromanuelcubo.migimnasio.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedromanuelcubo.migimnasio.modelos.Provincia;
import com.pedromanuelcubo.migimnasio.modelos.Sede;
import com.pedromanuelcubo.migimnasio.services.EntrenadorService;
import com.pedromanuelcubo.migimnasio.services.ProvinciaService;
import com.pedromanuelcubo.migimnasio.services.SedeService;
import com.pedromanuelcubo.migimnasio.services.UsuarioService;



@RestController
@CrossOrigin
public class SedeController {

	@Autowired
	private com.pedromanuelcubo.migimnasio.services.SedeService sedeservice;
	
	@Autowired
	private ProvinciaService provinciaservice;
	
	@Autowired
	private UsuarioService usuarioservice;

	@RequestMapping(value="/sedes")
	public List<Sede> getAllSedes() {
		
		return sedeservice.getAllSedes();
		
	}
	
	@RequestMapping(value="/sedes/{id}")
	public Optional<Sede> getSedeById(@PathVariable Long id) {
		
		return sedeservice.getSede(id);
	}
	
	@RequestMapping(value="/sedes", method=RequestMethod.POST)
	public Sede addSede(@RequestBody Sede s) {
	
		
		return sedeservice.addSede(s);
	}
	
	@RequestMapping(value="/sedes/{id}", method=RequestMethod.PUT)
	public Sede updateSede(@RequestBody Sede s, @PathVariable Long id) {
		
		Provincia p=provinciaservice.getById(s.getProvincia().getId()).get();
		s.setProvincia(p);

		return sedeservice.updateSede(id, s);

	}
	
	@RequestMapping(value="/sedes/{id}", method=RequestMethod.DELETE)
	public void deleteSede(@PathVariable Long id) {
		
	//	usuarioservice.deleteUsuariosDeUnaSede(id);
		sedeservice.iniciarBorrado();
				
		sedeservice.deleteSede(id);
		
		sedeservice.acabarBorrado();
	}
	
	
}
