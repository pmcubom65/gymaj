package com.pedromanuelcubo.migimnasio.security;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.pedromanuelcubo.migimnasio.modelos.Entrenador;
import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.repositorios.EntrenadorRepository;
import com.pedromanuelcubo.migimnasio.repositorios.UsuarioRepository;

@Component
public class Guard {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EntrenadorRepository entrenadorRepository;

	public boolean revisarUsuarioId(int id) {

		boolean resultado;

		Optional<Usuario> usuario = usuarioRepository.findById((long) id);

		try {
			resultado = usuario.get().getId() == (long) id;

		} catch (NoSuchElementException exception) {
			resultado = false;
		}

		return resultado;
	}

	public boolean revisarEntrenadorId(int id) {

		boolean resultado;

		Optional<Entrenador> entrenador = entrenadorRepository.findById((long) id);

		try {
			resultado = entrenador.get().getId() == (long) id;

		} catch (NoSuchElementException exception) {
			resultado = false;
		}

		return resultado;
	}

}
