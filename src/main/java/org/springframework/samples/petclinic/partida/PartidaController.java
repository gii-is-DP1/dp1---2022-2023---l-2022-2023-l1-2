package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;
	private final JugadorService jugadorService;
	private final UsuarioService usuarioService;
    private static final String PARTIDA_CREATE = "partidas/createPartidas";

    @Autowired
	public PartidaController(PartidaService partidaService, JugadorService jugadorService, UsuarioService usuarioService) {
		this.partidaService = partidaService;
		this.jugadorService = jugadorService;
		this.usuarioService = usuarioService;
	}


    @GetMapping(value = "/create")
 	public String initCreationForm(Map<String, Object> model) {
 		Partida partida = new Partida();
 		model.put("partida", partida);
 		return PARTIDA_CREATE;
 	}

 	@PostMapping(value = "/create")
	public String processCreationForm(Principal principal, @Valid Partida partida, BindingResult result) {
 		if (result.hasErrors()) {
 			return PARTIDA_CREATE;
 		}
 		else {
			Optional<Usuario> user = usuarioService.findUsuario(principal.getName());
			Jugador jug = jugadorService.findByUsuario(user.get());
            partida.setFecha(LocalDate.now());
            partida.setEstado(EstadoPartida.EN_COLA);
            partida.setHoraInicio(LocalTime.now());
			partida.setCreador(jug);
			Set<Jugador> set = new HashSet<>();
			set.add(jug);
			partida.setJugadores(set);

            this.partidaService.save(partida);
			
			return "redirect:/partidas/" + partida.getId();
 		}
 	}

    @GetMapping("/{partidaId}")
 	public ModelAndView showPartida(@PathVariable("partidaId") int partidaId) {
 		ModelAndView mav = new ModelAndView("partidas/showPartida");
			mav.addObject("partida",this.partidaService.findById(partidaId));
			return mav;
 	}
}
