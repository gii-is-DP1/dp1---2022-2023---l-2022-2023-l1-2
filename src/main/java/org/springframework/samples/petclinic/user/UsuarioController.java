package org.springframework.samples.petclinic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/users")
public class UsuarioController {

	@Autowired
	UsuarioService service;

	@GetMapping("/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") long id){
		service.deleteUsuario(id);
		return null;
	}


}
