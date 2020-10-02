package com.pedromanuelcubo.migimnasio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pedromanuelcubo.migimnasio.modelos.Actividad;
import com.pedromanuelcubo.migimnasio.modelos.Entrenador;
import com.pedromanuelcubo.migimnasio.modelos.Provincia;
import com.pedromanuelcubo.migimnasio.modelos.ProvinciaListado;
import com.pedromanuelcubo.migimnasio.modelos.Sede;
import com.pedromanuelcubo.migimnasio.modelos.Usuario;
import com.pedromanuelcubo.migimnasio.modelos.UsuarioLogin;
import com.pedromanuelcubo.migimnasio.repositorios.SedeRepository;
import com.pedromanuelcubo.migimnasio.security.ApplicationRoles;
import com.pedromanuelcubo.migimnasio.services.ActividadService;
import com.pedromanuelcubo.migimnasio.services.EntrenadorService;
import com.pedromanuelcubo.migimnasio.services.ProvinciaService;
import com.pedromanuelcubo.migimnasio.services.SedeService;
import com.pedromanuelcubo.migimnasio.services.UsuarioLoginService;
import com.pedromanuelcubo.migimnasio.services.UsuarioService;

@Component
public class DataLoader  implements ApplicationRunner
{

    private SedeService sedeservice;
    private UsuarioService usuarioservice;
    private EntrenadorService entrenadorservice;
    private ActividadService actividadservice;
    private ProvinciaService provinciaservice;
    private UsuarioLoginService usuariologinservice;
    
    
    
    @Autowired
    public DataLoader(SedeService sedeservice, UsuarioService usuarioservice, EntrenadorService entrenadorservice,
    		ActividadService actividadservice, ProvinciaService provinciaservice, UsuarioLoginService usuariologinservice) {
    	
        this.sedeservice = sedeservice;
        this.usuarioservice=usuarioservice;
        this.entrenadorservice=entrenadorservice;
        this.actividadservice=actividadservice;
        this.provinciaservice=provinciaservice;
        this.usuariologinservice=usuariologinservice;
    }

 

	@Override
	public void run(ApplicationArguments args) throws Exception {
		

		 ApplicationContext context= new ClassPathXmlApplicationContext("esquemabean.xml");
		 
		 	ProvinciaListado provincias=(ProvinciaListado) context.getBean("lasprovincias");
		 	
		 	if (provinciaservice.getAllProvincias().size()==0) {
		 		provincias.getProvincias().forEach(p->provinciaservice.add(new Provincia(p)));
		 	}
		 	
		 
		 	
		    UsuarioLogin ul= (UsuarioLogin) context.getBean("login1");
		    UsuarioLogin ul2= (UsuarioLogin) context.getBean("login2");
		    UsuarioLogin ul3= (UsuarioLogin) context.getBean("login3");
		    UsuarioLogin ul4= (UsuarioLogin) context.getBean("login4");
		    
		    UsuarioLogin ul5= (UsuarioLogin) context.getBean("login5");
		    UsuarioLogin ul6= (UsuarioLogin) context.getBean("login6");
		    UsuarioLogin ul7= (UsuarioLogin) context.getBean("login7");
		    UsuarioLogin ul8= (UsuarioLogin) context.getBean("login8");
		    UsuarioLogin ul9= (UsuarioLogin) context.getBean("login9");
		    UsuarioLogin ul10= (UsuarioLogin) context.getBean("login10");
		    
		    
		      List<UsuarioLogin> listadoUsuarioLogin=Arrays.asList(
		    		  ul, ul2, ul3, ul4, ul5, ul6, ul7, ul8, ul9, ul10
		    		  );
		        
		        listadoUsuarioLogin.forEach(i-> {
		       
		        	try {
		        		this.usuariologinservice.loadUserByUsername(i.getEmail());
		        	}catch (UsernameNotFoundException exception) {
		        		this.usuariologinservice.saveUsuarioEntity(i);
		        	}
		        	
	
		        });
		    
		 	
	        Sede misedebean= (Sede) context.getBean("sede1");
	        Sede misedebean2= (Sede) context.getBean("sede2");
	        Sede misedebean3= (Sede) context.getBean("sede3");
	        Sede misedebean4= (Sede) context.getBean("sede4");
	        
	        List<Sede> listadosedes=Arrays.asList(misedebean, misedebean2, misedebean3, misedebean4);
	        
	        listadosedes.forEach(i-> {
	        	Long id=i.getId();
	        	Sede nuevasede=sedeservice.addSede(i);
	        	
	        	 sedeservice.updateInicial(nuevasede.getId(), id);
	        });

	        
	        Usuario usuario1= (Usuario) context.getBean("usuario1");
	        Usuario usuario2= (Usuario) context.getBean("usuario2");
	        Usuario usuario3= (Usuario) context.getBean("usuario3");
	        Usuario usuario4= (Usuario) context.getBean("usuario4");
	        Usuario usuario5= (Usuario) context.getBean("usuario5");
	        
      List<Usuario> listadousuarios=Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5);
	        
      listadousuarios.forEach(i-> {
    	
    	  		Long id=i.getId();
    	  		Usuario u;
	        	if ((u=usuarioservice.addUsuario(i)) instanceof Usuario) {
	        		usuarioservice.updateInicial(u.getId(), id);
	        		
	        //		usuariologinservice.updateUsuarioLogin(u.getUsuariologin().getId(), id);
	        	}
	
	        });
      
      
      Entrenador entrenador1= (Entrenador) context.getBean("entrenador1");
      Entrenador entrenador2= (Entrenador) context.getBean("entrenador2");
      Entrenador entrenador3= (Entrenador) context.getBean("entrenador3");
      Entrenador entrenador4= (Entrenador) context.getBean("entrenador4");
      
      
      List<Entrenador> listadoentrenadores=Arrays.asList(entrenador1, entrenador2, entrenador3, entrenador4);
      
      listadoentrenadores.forEach(i-> {
    	  Long id=i.getId();
    	  Entrenador e;
    	  if ((e= entrenadorservice.addEntrenador(i)) instanceof Entrenador) {
    		  entrenadorservice.updateInicial(e.getId(), id); 
    		  
    	//	usuariologinservice.updateUsuarioLogin(e.getUsuariologin().getId(), id);
    	  }

	        });
      
      
      
      Actividad actividad1= (Actividad) context.getBean("actividad1");
      Actividad actividad2= (Actividad) context.getBean("actividad2");
      Actividad actividad3= (Actividad) context.getBean("actividad3");
      Actividad actividad4= (Actividad) context.getBean("actividad4");
      
      
      List<Actividad> listadoactividades=Arrays.asList(actividad1, actividad2, actividad3, actividad4);
      
     
      listadoactividades.forEach(i-> {
    	  Actividad a;	
    	  Long id=i.getId();
    	 if ((a=actividadservice.addActividad(i)) instanceof Actividad) {
    		 actividadservice.updateInicial(a.getId(), id);
	        	
    	 }

	        });
      
      
      
      
      
      
      
      
      
      
      
      
		
	}
	
	
	
	
	
	
}
