package com.security.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/protected")
public class ProtectedController {
	
	
	@GetMapping("")
	public String protected1()
	{
		return "protected/home";
	}
	
	@GetMapping("/protected_2")
	public String protected2()
	{
		return "protected/protected_2";
	}

}
