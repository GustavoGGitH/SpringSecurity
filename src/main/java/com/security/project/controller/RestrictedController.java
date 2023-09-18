package com.security.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.security.project.security_config.PermissionsService;

@Controller
@RequestMapping("restricted")
public class RestrictedController {
	
	@Autowired 
	
	public PermissionsService permissionsService;
	
	@GetMapping("")
	public String restricted(RedirectAttributes flash)
	{
		if( permissionsService.checkRole("ROLE_ADMIN")) {
			return "restricted/home";
		}
		else {
			flash.addFlashAttribute("clase","warning");
			flash.addFlashAttribute("mensaje","No tienes acceso a este contenido");
			return "redirect: /";
			
		}

	}

	@GetMapping("/restricted_2")
	public String restricted_2()
	{
		return "restricted/restricted_2";
	}
}
