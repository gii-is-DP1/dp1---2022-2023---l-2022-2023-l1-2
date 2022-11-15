package org.springframework.samples.petclinic.usuario;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsuarioController {

    private static final String VIEW_CREATE_FORM = "usuario/createForm";

    private final JugadorService jugadorService; 

    @Autowired
    public UsuarioController(JugadorService jService){
        this.jugadorService = jService;
    }

    @InitBinder
 	public void setAllowedFields(WebDataBinder dataBinder) {
 		dataBinder.setDisallowedFields("id");
 	}

    @GetMapping(value = "/usuario/new")
 	public String initCreationForm(Map<String, Object> model) {
 		Jugador jugador = new Jugador();
 		model.put("jugador", jugador);
 		return VIEW_CREATE_FORM;
 	}

    
	@RequestMapping(value = "/usuario/new", method = RequestMethod.POST)
	public String processCreationForm(@Valid Jugador jugador, BindingResult result) {
		if (result.hasErrors()) {
			return VIEW_CREATE_FORM;
		}
		else {
			//creating jugador, usuario, and administrador
			this.jugadorService.saveJugador(jugador);
			return "redirect:/";
		}
	}
    
}
