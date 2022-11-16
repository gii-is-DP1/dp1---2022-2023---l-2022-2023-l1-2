package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JugadorController {

    private static final String VIEWS_PLAYER_CREATE_OR_UPDATE = "";

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService, UsuarioService uService, AutoridadService admnistradorService){
        this.jugadorService=jugadorService; 
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

    @GetMapping(value = "/jugador/delete/{jugadorId}")
    public String deleteJugador(@PathVariable("jugadorId") int jugadorId){
        Optional<Jugador> opt = jugadorService.findJugadorById(jugadorId);
        if(opt.isPresent()){
            var jugador = opt.get();
            jugadorService.deleteJugador(jugador);
        }
        return "redirect:/jugadores/find";
    }


    
}
