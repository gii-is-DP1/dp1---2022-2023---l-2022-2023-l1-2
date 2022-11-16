package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JugadorController {

    private static final String VIEWS_PLAYER_CREATE_OR_UPDATE = "jugadores/updateForm";

    private final JugadorService jugadorService;
    private final UsuarioService usuarioService;

    @Autowired
    public JugadorController(JugadorService jugadorService, UsuarioService uService, AutoridadService admnistradorService){
        this.jugadorService=jugadorService; 
        this.usuarioService=uService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/jugadores/find")
    public ModelAndView listJugadores(){
		ModelAndView mav = new ModelAndView("jugadores/listJugadores");
		List<Jugador> jugadores = jugadorService.findAll();
		mav.addObject("jugadores", jugadores);
		return mav;
	}


    @GetMapping(value = "/jugadores/delete/{jugadorId}")
    public String deleteJugador(@PathVariable("jugadorId") int jugadorId){
        Optional<Jugador> opt = jugadorService.findJugadorById(jugadorId);
        if(opt.isPresent()){
            var jugador = opt.get();
            jugadorService.deleteJugador(jugador);
        }
        return "redirect:/jugadores/find";
    }

    @GetMapping(value = "/jugadores/edit/{jugadorId}")
	public String initUpdateJugadorForm(@PathVariable("jugadorId") int jugadorId, Model model) {
		Jugador jugador = this.jugadorService.findJugadorById(jugadorId).get();
		model.addAttribute("jugador", jugador);
		return VIEWS_PLAYER_CREATE_OR_UPDATE;
	}

	@PostMapping(value = "/jugadores/edit/{jugadorId}")
	public String processUpdateJugadorForm(@Valid Jugador jugador, BindingResult result,
			@PathVariable("jugadorId") int jugadorId) {
		if (result.hasErrors()) {
			return VIEWS_PLAYER_CREATE_OR_UPDATE;
		}
		else {
			jugador.setId(jugadorId);
            Usuario usuario = jugador.getUsuario();
			Integer a = jugador.getPartidasGanadas();
			Integer b= jugador.getPartidasJugadas();
			Integer c = jugador.getRecordPuntos();
			Integer e = jugador.getTotalPuntos();
			jugador.setPartidasGanadas(a);
			jugador.setPartidasJugadas(b);
			jugador.setRecordPuntos(c);
			jugador.setTotalPuntos(e);
			this.jugadorService.saveJugador(jugador);
            this.usuarioService.saveUser(usuario);
			return "redirect:/jugadores/{jugadorId}";
		}
	}

    @GetMapping("/jugadores/{jugadorId}")
	public ModelAndView showJugador(@PathVariable("jugadorId") int jugadorId) {
		ModelAndView mav = new ModelAndView("jugadores/jugadorDetails");
		mav.addObject("jugador", this.jugadorService.findJugadorById(jugadorId).get());
		return mav;
	}
    
}
