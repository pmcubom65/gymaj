package com.pedromanuelcubo.migimnasio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;
import com.pedromanuelcubo.migimnasio.modelos.Entrenador;
import com.pedromanuelcubo.migimnasio.modelos.Sede;
import com.pedromanuelcubo.migimnasio.repositorios.EntrenadorRepository;



@Service
public class EntrenadorService {

	@Autowired
	private EntrenadorRepository entrenadorrepository;

	public List<Entrenador> getAllEntrenadores() {

		ArrayList<Entrenador> listadoentrenadores = new ArrayList<>();

		entrenadorrepository.findAll().forEach(e -> {
			listadoentrenadores.add(e);

		});

		return listadoentrenadores;

	}

	public Entrenador addEntrenador(Entrenador a) {
		
		try {
			return entrenadorrepository.save(a);
		}catch (DataIntegrityViolationException e) {
			
			return null;
		}
	
	}

	public Optional<Entrenador> getEntrenador(Long id) {
		return entrenadorrepository.findById(id);

	}
	

	

	public Sede getSededelEntrenador(Long id) {
		
		return entrenadorrepository.findById(id).get().getSede();

	}
	

	public Entrenador updateEntrenador(Long id, Entrenador a) {
		a.setId(id);
		return entrenadorrepository.save(a);
	}

	public void deleteEntrenador(Long id) {
		entrenadorrepository.deleteById(id);
	}

	
	
	@Transactional
	public void updateInicial(Long idinicial, Long idfinal) {
		
		entrenadorrepository.setEntrenadorId(idinicial, idfinal);
	}
	
	public Integer entornoEntrenador(Long usuario_id) {
		return entrenadorrepository.entrenadorEntorno(usuario_id);
	}
}
