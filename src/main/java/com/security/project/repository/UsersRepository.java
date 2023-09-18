package com.security.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.project.Model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>{
	
	
	public Users findByCorreoAndEstado(String correo, Integer estado) ;
}
