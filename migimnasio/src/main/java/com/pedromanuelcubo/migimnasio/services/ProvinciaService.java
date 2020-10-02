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
import com.pedromanuelcubo.migimnasio.modelos.Provincia;
import com.pedromanuelcubo.migimnasio.repositorios.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	private ProvinciaRepository provinciarepository;
	
	
	public void add(Provincia p) {
		
		provinciarepository.save(p);
	
	}
	
	@Transactional
	public void truncar() {
		provinciarepository.truncateTable();
	}
	
	
	public List<Provincia> getAllProvincias() {

		ArrayList<Provincia> todaslasprovincias = new ArrayList<>();

		provinciarepository.findAll().forEach(p -> {
			todaslasprovincias.add(p);

		});

		return todaslasprovincias;

	}
	
	public Optional<Provincia> getById(Long id) {
		return provinciarepository.findById(id);
	}
	
	
	
	
}
