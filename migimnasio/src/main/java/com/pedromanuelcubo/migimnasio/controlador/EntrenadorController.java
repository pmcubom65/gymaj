package com.pedromanuelcubo.migimnasio.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedromanuelcubo.migimnasio.modelos.Entrenador;
import com.pedromanuelcubo.migimnasio.modelos.Sede;
import com.pedromanuelcubo.migimnasio.modelos.UsuarioLogin;
import com.pedromanuelcubo.migimnasio.security.PasswordConfig;
import com.pedromanuelcubo.migimnasio.services.EntrenadorService;
import com.pedromanuelcubo.migimnasio.services.SedeService;
import com.pedromanuelcubo.migimnasio.services.UsuarioLoginService;


@RestController
@CrossOrigin
public class EntrenadorController {

	
	@Autowired
	private com.pedromanuelcubo.migimnasio.services.EntrenadorService entrenadorservice;
	
	@Autowired
	private UsuarioLoginService usuariologinservice;
	
	@Autowired
	private SedeService sedeservice;
	
	
	

	@RequestMapping(value="/entrenadores")
	public List<Entrenador> getAllEntrenadores() {
		

		return entrenadorservice.getAllEntrenadores();
		
	}
	
	
	
	
	@RequestMapping(value="/entrenadores/{id}")
	public HashMap<String, String> getEntrenadorById(@PathVariable Long id) {
		
		Entrenador e=entrenadorservice.getEntrenador(id).get();
		
		HashMap<String, String> retorno=new HashMap<>();
		retorno.put("nombre", e.getNombre());
		retorno.put("email", e.getUsuariologin().getEmail());
		retorno.put("password", e.getUsuariologin().getPassword());
		retorno.put("telefono", e.getTelefono());
		retorno.put("sede", e.getSede().getId().toString());
		retorno.put("descripcion", e.getDescripcion());
		retorno.put("dni", e.getDni());
		retorno.put("id", e.getId().toString());
		retorno.put("idlogin", e.getUsuariologin().getId().toString());
		

		return retorno;
	}
	
	@RequestMapping(value="/entrenadores", method=RequestMethod.POST)
	public Entrenador addEntrenador(@RequestBody Entrenador e) {
		
		UsuarioLogin ul=usuariologinservice.getUsuarioLoginById(e.getUsuariologin().getId());
		
		e.setUsuariologin(ul);
		
		e.setSede(sedeservice.getSede(e.getSede().getId()).get());
		


		return entrenadorservice.addEntrenador(e);
	}
	
	@RequestMapping(value="/entrenadores/{id}", method=RequestMethod.PUT)
	public Entrenador updateEntrenador(@RequestBody Entrenador e, @PathVariable Long id) {
		
		Sede s=sedeservice.getSede(e.getSede().getId()).get();
		
		e.setSede(s);
		UsuarioLogin ul=usuariologinservice.updateUsuarioLogin(e.getUsuariologin().getId(), e.getUsuariologin().getEmail(), e.getUsuariologin().getPassword());
		
		e.setUsuariologin(ul);
		
		return entrenadorservice.updateEntrenador(id, e);
		
	}
	
	@RequestMapping(value="/entrenadores/{id}", method=RequestMethod.DELETE)
	public void deleteEntrenador(@PathVariable Long id) {
		entrenadorservice.deleteEntrenador(id);
	}
	
	
}
