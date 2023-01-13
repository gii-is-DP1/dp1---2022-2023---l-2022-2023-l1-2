package org.springframework.samples.petclinic.usuario;

import java.time.LocalDate;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

    private static final String VIEW_CREATE_FORM = "usuario/createForm";

    private final JugadorService jugadorService;
	private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(JugadorService jService, UsuarioService usuarioService){
        this.jugadorService = jService;
		this.usuarioService = usuarioService;
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
			//creating jugador, usuario, and administrador`
			jugador.getUsuario().setCreatedDate(LocalDate.now());
			jugador.setPartidasGanadas(0);	
			jugador.setPartidasJugadas(0);
			jugador.setRecordPuntos(0);
			jugador.setTotalPuntos(0);
			this.jugadorService.saveJugador(jugador);
			return "redirect:/";
		}
	}
    
	@GetMapping("/auditoria")
	public ModelAndView auditoriaUsuarios(){
		ModelAndView mav = new ModelAndView("auditoria");
		List<Usuario> lista = usuarioService.findAllUsuarios();
		mav.addObject("usuarios", lista);
		return mav;
	}
}
