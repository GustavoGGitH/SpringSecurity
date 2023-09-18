package com.security.project.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.security.project.Model.Autorizar;
import com.security.project.repository.AutorizarRepository;

@Service
@Primary
public class AutorizarService {
	
	@Autowired
	private AutorizarRepository autorizarRepository;
	
	public void saveAutorizar(Autorizar autorizar ) {
		autorizarRepository.save(autorizar);
	}

}
