package com.security.project.security_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
	@Autowired
	public CustomLogin customLogin;

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();

	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Bean
	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        http.authorizeHttpRequests().
	        //vistas públicas que no requieren autorizacion
	            antMatchers(
	            		"/",
	            		"/released/**",
	            		"/access/register"
	            		).permitAll()
	        //asignar acceso por roles
	        	.antMatchers(
	        			"/protected"
	        			).hasAnyAuthority("ROLE_ADMIN")
	        // se hacen las configuraciones generales 
	        	.anyRequest().authenticated()
	        //login por defecto de security	
	        //	.and().formLogin().permitAll()
	        //	.and().formLogin().loginPage("/access/login").permitAll()
	        	.and().formLogin().successHandler(customLogin).loginPage("/access/login").permitAll()
	        	
	        //logout por defecto de security	
	        	//.and().logout().permitAll()
	        	  .and().logout()
	              .logoutUrl("/logout")
	              .logoutSuccessUrl("/access/login")
	              .permitAll();
	        	
	         ;
	        return http.build();
	    }
	  
	  @Bean
	  //Customizar trabaja directamente con la carpeta static de configuraciones por eso ponemos /images/**
	  public WebSecurityCustomizer webSecurityCustomizer()
	  {
		 return (web) -> web.ignoring().antMatchers("/images/**","/js/**","/css/**") ;
		  
	  }

}
