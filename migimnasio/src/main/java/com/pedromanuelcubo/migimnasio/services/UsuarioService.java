package com.pedromanuelcubo.migimnasio.services;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;
import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.repositorios.ProvinciaRepository;
import com.pedromanuelcubo.migimnasio.repositorios.SedeRepository;
import com.pedromanuelcubo.migimnasio.repositorios.UsuarioLoginRepository;
import com.pedromanuelcubo.migimnasio.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuariorepositorio;
	
	@Autowired
	private ProvinciaRepository provinciarepositorio;
	
	@Autowired
	private UsuarioLoginRepository  usuariologinrepositorio;
	
	@Autowired
	private SedeRepository sederepositorio;

	public List<Usuario> getAllUsuarios() {
		ArrayList<Usuario> listausuarios = new ArrayList<>();
		usuariorepositorio.findAll().forEach(usuario -> listausuarios.add(usuario));
		return listausuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		
		try {
			return usuariorepositorio.save(usuario);
		}
		catch (DataIntegrityViolationException e) {
			
			return null;
		}

	
	}

	public Optional<Usuario> getUsuarioById(Long id) {
		return usuariorepositorio.findById(id);
	}

	public Usuario updateUsuario(Long id, Usuario usuario) {
	
		usuario.setId(id);

		usuario.setProvincia(provinciarepositorio.findById(usuario.getProvincia().getId()).get());
		usuario.setUsuariologin(usuariologinrepositorio.findById(usuario.getUsuariologin().getId()).get());
		usuario.setSede(sederepositorio.findById(usuario.getSede().getId()).get());
		return usuariorepositorio.save(usuario);

	}

	public void deleteUsuario(Long id) {
		usuariorepositorio.deleteById(id);
	}
	
	
	public void deleteUsuariosDeUnaSede(Long id) {
		System.out.println(usuariorepositorio.usuariosDeLaSede(id));
		usuariorepositorio.usuariosDeLaSede(id).forEach(usuario->{
			System.out.println(usuario);
			this.deleteUsuario(new Long(usuario));
		});
	}

	
	@Transactional
	public void updateInicial(Long idinicial, Long idfinal) {
		
		usuariorepositorio.setUsuarioId(idinicial, idfinal);
	}
	
	
	public Integer entornoUsuario(Long usuario_id) {
		return usuariorepositorio.usuarioEntorno(usuario_id);
	}
	
}
