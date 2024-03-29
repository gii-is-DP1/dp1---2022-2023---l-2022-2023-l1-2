package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/usuario/new").permitAll()
				.antMatchers("/jugadores/delete/**").hasAnyAuthority("admin")
				.antMatchers("/jugadores/edit/**").authenticated()
				.antMatchers("/jugadores/profile").authenticated()
				.antMatchers("/session/**").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/owners/**").hasAnyAuthority("owner","admin")				
				.antMatchers("/vets/**").authenticated()
				.antMatchers("/partidas/**").authenticated()
				.antMatchers("/find/**").permitAll()
				.antMatchers("/jugadores/find").authenticated()
				.antMatchers("/partidas/join2").authenticated()
				.antMatchers("/logros/list").authenticated()
				.antMatchers("/logros/delete/**").hasAnyAuthority("admin")
				.antMatchers("/logros/create").hasAnyAuthority("admin")
				.antMatchers("/logros/edit/**").hasAnyAuthority("admin")
				.antMatchers("/partidas/historial").hasAnyAuthority("admin")
				.antMatchers("/auditoria").hasAnyAuthority("admin")
				.antMatchers("/partidas/{partidaId}/tablero/{cartaId}").authenticated()
				.antMatchers("/partidas/{partidaId}/inicio").authenticated()
				.antMatchers("/partidas/${partidaId}/tablero/cogerCarta").authenticated()
				.antMatchers("/jugadores/rankingPuntos").authenticated()
				.antMatchers("/jugadores/rankingPartidasGanadas").authenticated()
				.antMatchers("/estadisticas/generales").permitAll()
				.antMatchers("/jugadores/{jugadorId}").authenticated()
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select nombre_usuario,contrasena,enabled "
	        + "from usuarios "
	        + "where nombre_usuario = ?")
	      .authoritiesByUsernameQuery(
	       "select nombre_usuario,autoridad "
	        + "from autoridades "
	        + "where nombre_usuario = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  new BCryptPasswordEncoder();
	    return encoder;
	}
	
}


