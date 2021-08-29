package com.taller.security.recursos;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class RecursosWeb extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/publica").permitAll();

		http.requestMatchers().antMatchers("/privada").and().authorizeRequests().antMatchers("/privada")
				.access("hasRole('USER')").and().requestMatchers().antMatchers("/admin").and().authorizeRequests()
				.antMatchers("/admin").access("hasRole('ADMIN')");

	}
	
	@GetMapping("/publica")
	public String publica() {
		return "Recurso publico";
	}
	
	@GetMapping("/privada")
	public String privada() {
		return "Recurso que necesita autorización y un usuario con rol USER";
	}
	
	@GetMapping("/admin")
	public String admnin() {
		return "Recurso que necesita autorización y un usuario con rol ADMIN";
	}
	
	
	
	

}
