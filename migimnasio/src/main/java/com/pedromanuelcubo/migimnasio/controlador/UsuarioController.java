package com.pedromanuelcubo.migimnasio.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;
import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.services.ProvinciaService;
import com.pedromanuelcubo.migimnasio.services.SedeService;
import com.pedromanuelcubo.migimnasio.services.UsuarioLoginService;
import com.pedromanuelcubo.migimnasio.services.UsuarioService;

@RestController
@CrossOrigin
public class UsuarioController {

	@Autowired
	private com.pedromanuelcubo.migimnasio.services.UsuarioService usuarioservice;

	@Autowired
	private ProvinciaService provinciaservice;

	@Autowired
	private SedeService sedeservice;

	@Autowired
	private UsuarioLoginService usuariologinservice;

	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public List<Usuario> getAllUsuarios() {

		return usuarioservice.getAllUsuarios();
	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public HashMap<String, String> getUsuario(@PathVariable Long id) {

		Usuario usuario = usuarioservice.getUsuarioById(id).get();

		HashMap<String, String> retorno = new HashMap<>();

		retorno.put("nombre", usuario.getNombre());
		retorno.put("email", usuario.getUsuariologin().getEmail());
		retorno.put("domicilio", usuario.getDomicilio());
		retorno.put("provincia", usuario.getProvincia().getId().toString());
		retorno.put("dni", usuario.getDni());
		retorno.put("telefono", usuario.getTelefono());
		retorno.put("fechanacimiento", usuario.getFechanacimiento().toString());
		retorno.put("sede", usuario.getSede().getNombre());
		retorno.put("sedeid", usuario.getSede().getId().toString());
		retorno.put("usuariologin_id", usuario.getUsuariologin().getId().toString());

		return retorno;

	}

	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public Usuario addUsuario(@RequestBody Usuario usuario) {

		usuario.setProvincia(provinciaservice.getById(usuario.getProvincia().getId()).get());
		usuario.setSede(sedeservice.getSede(usuario.getSede().getId()).get());
		usuario.setUsuariologin(usuariologinservice.getUsuarioLoginById(usuario.getUsuariologin().getId()));

		return usuarioservice.addUsuario(usuario);
	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.PUT)
	public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {

		return usuarioservice.updateUsuario(id, usuario);
	}

	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.DELETE)
	public void deleteUsuario(@PathVariable Long id) {
		usuarioservice.deleteUsuario(id);
	}

}
