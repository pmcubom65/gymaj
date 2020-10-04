package com.pedromanuelcubo.migimnasio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedromanuelcubo.migimnasio.modelos.Sede;
import com.pedromanuelcubo.migimnasio.repositorios.SedeRepository;


@Service
public class SedeService {

	
	@Autowired
	private SedeRepository sederepository;

	
	public List<Sede> getAllSedes() {

		ArrayList<Sede> listadosedes = new ArrayList<>();

		sederepository.findAll().forEach(s -> {
			listadosedes.add(s);

		});

		return listadosedes;

	}

	public Sede addSede(Sede s) {

		return sederepository.save(s);
	}

	public Optional<Sede> getSede(Long id) {
		return sederepository.findById(id);

	}

	public Sede updateSede(Long id, Sede s) {
		s.setId(id);
		return sederepository.save(s);
	}

	public void deleteSede(Long id) {
		sederepository.deleteById(id);
	}

	@Transactional
	public void updateInicial(Long idinicial, Long idfinal) {
		
		sederepository.setSedeId(idinicial, idfinal);
	}
	
	public void iniciarBorrado() {
		sederepository.iniciarBorrado();
	}
	
	public void acabarBorrado() {
		sederepository.acabarBorrado();
	}
	
}
