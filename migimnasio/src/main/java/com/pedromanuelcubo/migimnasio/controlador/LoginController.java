package com.pedromanuelcubo.migimnasio.controlador;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.modelos.UsuarioLogin;
import com.pedromanuelcubo.migimnasio.services.EntrenadorService;
import com.pedromanuelcubo.migimnasio.services.UsuarioLoginService;
import com.pedromanuelcubo.migimnasio.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping({ "/validarlogin" })
public class LoginController {

	@Autowired
	private UsuarioLoginService usuariologinservice;

	@Autowired
	private UsuarioService usuarioservice;

	@Autowired
	private EntrenadorService entrenadorservice;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PutMapping
	public UsuarioLogin crearUsuario(@RequestBody UsuarioLogin usuario) {

			return usuariologinservice.saveUsuario(usuario.getEmail(), usuario.getPassword(), usuario.getRole().toString());
	}
	
	
	
	

	@PostMapping
	public HashMap<String, String> comprobarLogin(@RequestBody UsuarioLogin usuario) {

		Integer id;
		Integer idusuario;
		HashMap<String, String> retorno = new HashMap<>();

		UserDetails ud = this.usuariologinservice.loadUserByUsername(usuario.getEmail());
		id = usuariologinservice.getUsuarioLogin(usuario.getEmail());

		switch (ud.getAuthorities().toArray()[0].toString()) {
		case "ROLE_ENTRENADOR":

			idusuario = entrenadorservice.entornoEntrenador(new Long(id));
			retorno.put("id", String.valueOf(idusuario));

			break;

		case "ROLE_USUARIO":

			idusuario = usuarioservice.entornoUsuario(new Long(id));
			retorno.put("id", String.valueOf(idusuario));
			break;
			
			
		default:
			retorno.put("id", String.valueOf(id));
			break;

		}

	

		retorno.put("email", usuario.getEmail());
		retorno.put("authority", ud.getAuthorities().stream().findFirst().get().toString());

		return retorno;
	}

}
