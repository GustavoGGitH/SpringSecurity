package com.security.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.security.project.Model.Users;
import com.security.project.repository.UsersRepository;

@Service
@Primary

public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public Users findByCorreo(String correo, Integer estado) {
		return usersRepository.findByCorreoAndEstado(correo, estado);
	}
	
	public Users saveUsers(Users user) {
		
		return usersRepository.save(user);
	}

}
