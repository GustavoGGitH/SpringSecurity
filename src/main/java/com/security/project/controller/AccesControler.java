package com.security.project.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.security.project.Model.Autorizar;
import com.security.project.Model.Users;
import com.security.project.services.AutorizarService;
import com.security.project.services.UsersService;

@Controller
@RequestMapping("/access")
public class AccesControler {
	
	@Autowired
	public UsersService usersService;
	
	@Autowired
	public AutorizarService autorizarService;
	
	@Autowired
	public BCryptPasswordEncoder bcCryptPasswordEncoder;
	
	@GetMapping("login")
	public String login(@RequestParam(value="error",required=false )String error,
					   @RequestParam(value="logout",required=false )String logout,
					   RedirectAttributes flash,Principal principal) {
		
		if (principal!=null) {
			flash.addFlashAttribute("clase","sucess");
			flash.addFlashAttribute("mensaje","Ya ha iniciado sesion anteriormente");
			return "redirect:/";
		}
		
		if (error!=null) {
			flash.addFlashAttribute("clase","danger");
			flash.addFlashAttribute("mensaje","Los datos ingresados no son correctos por favor reintente");
			return "redirect:/access/login ";
		}

		if (logout!=null) {
			flash.addFlashAttribute("clase","sucess");
			flash.addFlashAttribute("mensaje","Ha cerrado exitosamente");
			return "redirect:/access/login ";
		}
		
		return "access/login";
		
	}
	
	
	@GetMapping("/register")
		
		public String register(Model model)
		{
			model.addAttribute("usuario", new Users());
			
			return "access/register";
			
		}		
	
	@PostMapping("/register")
	
	public String saveregister(@Valid Users usuario, BindingResult result, RedirectAttributes flash, Model model)
	{
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),
						"El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			return "access/register";
		
		}
		// creamos el usuario
		
		Users guardar=  usersService.saveUsers(new Users(usuario.getNombre(),usuario.getCorreo(),usuario.getTelefono(),
				bcCryptPasswordEncoder.encode(usuario.getPassword()),1,null) ) ;
		
		
		//creamos un rol
		
		autorizarService.saveAutorizar(new Autorizar("ROLE_USER",guardar));
		
		//redireccionamos
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "Te has registrado exitosamente");
		return "redirect:/access/register";
	}
	
		
	

}
