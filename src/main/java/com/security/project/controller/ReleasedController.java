package com.security.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("released")
public class ReleasedController {

	@GetMapping("")
	public String released()
	{
		return "released/home";
	}
	
	@GetMapping("/released_2")
	public String released_2()
	{
		return "released/released_2";
	}
}
