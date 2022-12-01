package org.springframework.samples.petclinic.partida;

import java.util.Optional;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private static final String PARTIDA_JOIN = "partidas/joinPartidas";
	private static final String TABLERO = "partidas/Tablero";

    @Autowired
	public PartidaController(PartidaService partidaService, JugadorService jugadorService, UsuarioService usuarioService) {
		this.partidaService = partidaService;
		this.jugadorService = jugadorService;
		this.usuarioService = usuarioService;
	}

    @GetMapping("/join/{partidaId}")
    public String initUnirPartida(ModelMap model) {
        Partida p = new Partida();
        model.put("partida", p);
        return PARTIDA_JOIN;
    }

    @PostMapping("/join/{partidaId}")
    public String processUnirPartida(Principal principal, @Valid Partida partida, BindingResult result,
            @PathVariable("partidaId") int partidaId) {
        if (result.hasErrors()) {
            return PARTIDA_JOIN;
        } else {
            Partida p = partidaService.findById(partidaId).get();
            if (!(p.getCodigo().equals(partida.getCodigo()))) {
                throw new IllegalArgumentException("El código no es correcto");
            } else {
                Usuario u = usuarioService.findUserByNombreUsuario(principal.getName()).get();
                Jugador j = jugadorService.findByUsuario(u);
                Set<Jugador> set = p.getJugadores();
                set.add(j);
                p.setJugadores(set);
                partidaService.save(p);
                return "redirect:/partidas/{partidaId}";
            }   
        }
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
			Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
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

	@GetMapping("/join")
	public ModelAndView listPartidas(){
		ModelAndView mav = new ModelAndView("partidas/listPartidas");
		List<Partida> partidas = partidaService.findAllPartidas();
		List<Partida> partidasEnCola =partidas.stream().filter(x->x.getEstado()==EstadoPartida.EN_COLA).collect(Collectors.toList());
		mav.addObject("partidas", partidasEnCola);
		return mav;
	}

	@GetMapping("/{partidaId}/tablero")
	public ModelAndView showTablero(@PathVariable("partidaId") int partidaId) {
		ModelAndView mav = new ModelAndView(TABLERO);
		mav.addObject("partida",this.partidaService.findById(partidaId));
        Partida partida = partidaService.findById(partidaId).get();
           if (partida.getJugadores().size() < 2 || partida.getJugadores().size() > 4) {
                throw new IllegalArgumentException(
                                "La partida no puede comenzar con el número de jugadores presentes en la sala");
            } else {
                partida.setEstado(EstadoPartida.EN_CURSO);
                return mav;
            }
	}
}
