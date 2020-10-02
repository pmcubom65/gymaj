package com.pedromanuelcubo.migimnasio.controlador;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;
import com.pedromanuelcubo.migimnasio.modelos.Sede;
import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.services.ActividadService;
import com.pedromanuelcubo.migimnasio.services.SedeService;
import com.pedromanuelcubo.migimnasio.services.UsuarioService;

@RestController
@CrossOrigin
public class ActividadController {

	@Autowired
	private com.pedromanuelcubo.migimnasio.services.ActividadService actividadservice;
	
	
	@Autowired
	private com.pedromanuelcubo.migimnasio.services.UsuarioService usuarioservice;
	
	
	@Autowired
	private com.pedromanuelcubo.migimnasio.services.SedeService sedeservice;

	@RequestMapping(value="/actividades")
	public List<Actividad> getAllActividades() {
		
		return actividadservice.getAllActividades();
		
	}
	
	
	@RequestMapping(value="/actividadesporsede/{id}", method=RequestMethod.GET)
	public List<Actividad> getAllActividadesPorSede(@PathVariable Long id) {

		
		List<Actividad> act=actividadservice.getAllActividades().stream()
		  .filter(c -> c.getSede().getId().equals(id))
		  .collect(Collectors.toList());
		
		
		return act;
		
	}
	
	
	
	@RequestMapping(value="/actividades/{id}")
	public HashMap<String, String> getActividadById(@PathVariable Long id) {
		
		Actividad a=actividadservice.getActividad(id).get();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dfh = new SimpleDateFormat("HH:mm");


		HashMap<String, String> retorno=new HashMap<>();
		retorno.put("nombre", a.getNombre());
		retorno.put("plazas", a.getPlazas());
		retorno.put("sede", a.getSede().getId().toString());
		retorno.put("hora", dfh.format(a.getDia()));
		retorno.put("fecha", df.format(a.getDia()));
		retorno.put("entrenador", a.getEntrenador().getId().toString());
		retorno.put("entrenadornombre", a.getEntrenador().getNombre());
		
		return retorno;
	}
	
	@RequestMapping(value="/actividades", method=RequestMethod.POST)
	public Actividad addActividad(@RequestBody Actividad actividad) {

		return actividadservice.addActividad(actividad);
	}
	
	@RequestMapping(value="/actividades/{id}", method=RequestMethod.PUT)
	public Actividad updateActividad(@RequestBody Actividad actividad, @PathVariable Long id) {
		
		Sede sede=sedeservice.getSede(actividad.getSede().getId()).get();
		
		actividad.setSede(sede);
		
		return actividadservice.updateActividad(id, actividad);
		
	}
	
	
	@RequestMapping(value="/apuntarse/{id}", method=RequestMethod.PUT)
	public Actividad apuntarseActividad(@RequestBody Usuario usuario, @PathVariable Long id) {
		Actividad actividadelejida=actividadservice.getActividad(id).get();
		Usuario usuarioe= usuarioservice.getUsuarioById(usuario.getId()).get();
		
		actividadelejida.getListadousuarios().add(usuarioe);
		String recalcular=String.valueOf(Integer.valueOf(actividadelejida.getPlazas())-1);
		actividadelejida.setPlazas(recalcular);
		
		return actividadservice.updateActividad(id, actividadelejida);
		
	}
	
	
	
	
	
	
	@RequestMapping(value="/actividades/{id}", method=RequestMethod.DELETE)
	public void deleteActividad(@PathVariable Long id) {
		actividadservice.deleteActividad(id);
	}
}
