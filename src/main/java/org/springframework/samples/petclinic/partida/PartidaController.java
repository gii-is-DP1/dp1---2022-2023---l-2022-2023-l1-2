package org.springframework.samples.petclinic.partida;

import java.util.Optional;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.NoSuchElementException;

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
    private final UsuarioService usuarioService;
    private final JugadorService jugadorService;
    private static final String PARTIDA_CREATE = "partidas/createPartidas";
    private static final String PARTIDA_JOIN = "partidas/joinPartidas";

    @Autowired
    public PartidaController(PartidaService partidaService, UsuarioService usuarioService, JugadorService jugadorService) {
        this.partidaService = partidaService;
        this.usuarioService = usuarioService;
        this.jugadorService = jugadorService;
    }

    @GetMapping("/join")
    public String initUnirPartida(ModelMap model) {
        String codigo = null;
        model.put("codigo", codigo);
        return PARTIDA_JOIN;
    }

    @PostMapping("/join")
    public String processUnirPartida(@Valid String codigo, BindingResult result) {
        String nombreUsuario = null;
        Optional<Usuario> optUsuario = usuarioService.findUserByNombreUsuario(nombreUsuario);
        Usuario u = optUsuario.get();
        if (optUsuario.isPresent() == false) {
            throw new IllegalArgumentException("No se ha encontrado el nombre de usuario");
        }
        if (result.hasErrors()) {
            return PARTIDA_JOIN;
        } else {
            if (u.getEsJugador() == false) {
                u.setEsJugador(true);
            }
            Jugador j;
            j.setId(u.getId());
            j.setNombreUsuario(u.getNombreUsuario());
            j.setContraseña(u.getContraseña());
            j.setFechaNacimiento(u.getFechaNacimiento());
            j.setEsAdministrador(u.getEsAdministrador());
            j.setEsJugador(u.getEsJugador());
            j.setPartidasJugadas(0);
            j.setPartidasGanadas(0);
            j.setRecordPuntos(0);
            j.setTotalPuntos(0);
            jugadorService.save(j);
            Optional<Partida> OptP = partidaService.findPartidaByCodigo(codigo);
            Partida p = OptP.get();
            if (OptP.isPresent() == false) {
                throw new IllegalArgumentException("No se ha encontrado la partida");
            }
            p.setJugador(p.getJugador().add(j));
            return "redirect:/partidas/{partidaId}";
        }
    }

    @GetMapping(value = "/create")
 	public String initCreationForm(Map<String, Object> model) {
 		Partida partida = new Partida();
 		model.put("partida", partida);
 		return PARTIDA_CREATE;
 	}

 	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Partida partida, BindingResult result) {
 		if (result.hasErrors()) {
 			return PARTIDA_CREATE;
 		}
 		else {
            partida.setFecha(LocalDate.now());
            partida.setEstado(EstadoPartida.EN_COLA);
            partida.setHoraInicio(LocalTime.now());
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
