package com.security.project.security_config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.security.project.Model.Autorizar;
import com.security.project.Model.Users;
import com.security.project.services.UsersService;

@Component
public class UserLogin implements UserDetailsService {

	@Autowired
	public UsersService userService;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user= userService.findByCorreo(username, 1);
		
		if (user==null) {
			throw new UsernameNotFoundException("El E-Mail: " + username + " no existe en el sistema");
		}
		
		
		// Configuración de atuhorites
		
		List<GrantedAuthority> authorities= new ArrayList<GrantedAuthority>();
		for (Autorizar role: user.getAutorizar()) 
		{
			authorities.add(new SimpleGrantedAuthority(role.getNombre())); 
		}
		
		if (authorities.isEmpty())
		{
			throw new UsernameNotFoundException("El E-Mail: " + username + " no tiene roles asignados");
		}
		return new User(user.getNombre(),user.getPassword(), true, true, true, true, authorities);
	}

}
