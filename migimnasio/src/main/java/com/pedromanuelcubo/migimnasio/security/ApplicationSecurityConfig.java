package com.pedromanuelcubo.migimnasio.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;




@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring().antMatchers(HttpMethod.GET, "/sedes/**");
		web.ignoring().antMatchers(HttpMethod.GET,  "/entrenadores");

		web.ignoring().antMatchers("/provincias");
		web.ignoring().antMatchers(HttpMethod.PUT, "/validarlogin");
		web.ignoring().antMatchers("/login");
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		
		http
		.cors().and()
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/sedes").hasRole(ApplicationRoles.ADMIN.name())
		.antMatchers(HttpMethod.PUT, "/sedes/**").hasRole(ApplicationRoles.ADMIN.name())
		.antMatchers(HttpMethod.DELETE, "/sedes/**").hasRole(ApplicationRoles.ADMIN.name())
		.antMatchers(HttpMethod.GET,"/usuarios/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.USUARIO.name())
		.antMatchers(HttpMethod.GET,"/usuarios").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.USUARIO.name())
		.antMatchers(HttpMethod.PUT,"/usuarios/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.USUARIO.name())
		.antMatchers(HttpMethod.DELETE,"/usuarios/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.USUARIO.name())
		.antMatchers(HttpMethod.POST,"/actividades").hasRole(ApplicationRoles.ADMIN.name())
		.antMatchers("/entrenamientos/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.ENTRENADOR.name(),  ApplicationRoles.USUARIO.name())
		.antMatchers(HttpMethod.POST, "/validarlogin").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.ENTRENADOR.name(),  ApplicationRoles.USUARIO.name())
	//	.antMatchers(HttpMethod.PUT, "/validarlogin").hasRole(ApplicationRoles.ADMIN.name())
		.antMatchers("/apuntarse/*").hasRole(ApplicationRoles.USUARIO.name())
		.antMatchers(HttpMethod.POST, "/usuarios").hasRole(ApplicationRoles.USUARIO.name())
		.anyRequest().authenticated().and().httpBasic();  
		
	}
	
	

	
	@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();

		CorsConfiguration config=new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		config.setAllowedHeaders(Arrays.asList("Authorization", "content-type"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		config.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
		
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	
	
	//recuperar los usuarios desde la base de datos
/*	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails pedroUser= User.builder().username("pedro").password(passwordEncoder.encode("123"))
				.roles(ApplicationRoles.USUARIO.name()).build();
		
		
		UserDetails pedroEntrena= User.builder().username("manuel").password(passwordEncoder.encode("123"))
				.roles(ApplicationRoles.ENTRENADOR.name()).build();
	
		return new InMemoryUserDetailsManager(pedroUser);
	
	}*/
}














/*http
.csrf().disable()
.authorizeRequests()
.antMatchers(HttpMethod.POST, "/sedes").hasRole(ApplicationRoles.ADMIN.name())
.antMatchers(HttpMethod.PUT, "/sedes/*").hasRole(ApplicationRoles.ADMIN.name())
.antMatchers(HttpMethod.DELETE, "/sedes/*").hasRole(ApplicationRoles.ADMIN.name())
.antMatchers("/usuarios/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.USUARIO.name())
.antMatchers("/actividades/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.ENTRENADOR.name())
.antMatchers("/entrenamientos/*").hasAnyRole(ApplicationRoles.ADMIN.name(), ApplicationRoles.ENTRENADOR.name(),  ApplicationRoles.USUARIO.name())
.anyRequest().authenticated().and().httpBasic();*/