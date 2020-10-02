package com.pedromanuelcubo.migimnasio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;
import com.pedromanuelcubo.migimnasio.repositorios.ActividadRepository;

@Service
public class ActividadService {

	@Autowired
	private com.pedromanuelcubo.migimnasio.repositorios.ActividadRepository actividadrepository;

	public List<Actividad> getAllActividades() {

		ArrayList<Actividad> listadoactividades = new ArrayList<>();

		actividadrepository.findAll().forEach(actividad -> {
			listadoactividades.add(actividad);

		});

		return listadoactividades;

	}

	public Actividad addActividad(Actividad a) {

		return actividadrepository.save(a);
	}

	public Optional<Actividad> getActividad(Long id) {
		return actividadrepository.findById(id);

	}

	public Actividad updateActividad(Long id, Actividad a) {
		a.setId(id);
		return actividadrepository.save(a);
	}

	public void deleteActividad(Long id) {
		actividadrepository.deleteById(id);
	}

	
	
	@Transactional
	public void updateInicial(Long idinicial, Long idfinal) {
		
		actividadrepository.setActividadId(idinicial, idfinal);
	}
	
	
	
}
