package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.samples.petclinic.estadisticasPartida.EstadisticaService;
import org.springframework.samples.petclinic.estadisticasPartida.EstadísticaJugadorEnPartida;
import org.springframework.samples.petclinic.partida.PartidaService;
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
	private static final String RANKINGPUNTOS = "jugadores/rankingPuntos";
	private static final String RANKINGPARTIDAS = "jugadores/rankingPartidasGanadas";

	

    private final JugadorService jugadorService;
    private final UsuarioService usuarioService;
	private final PartidaService partidaService;
	private final EstadisticaService estadisticaService;

    @Autowired
    public JugadorController(JugadorService jugadorService, UsuarioService uService, AutoridadService admnistradorService, PartidaService partidaService,EstadisticaService estadisticaService){
        this.jugadorService=jugadorService; 
        this.usuarioService=uService;
		this.partidaService=partidaService;
		this.estadisticaService=estadisticaService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/jugadores/find")
    public ModelAndView listJugadores(HttpSession session, @PageableDefault(page = 0, size = 5) Pageable page){
		ModelAndView mav = new ModelAndView("jugadores/listJugadores");
		Page<Jugador> jugadores = jugadorService.findAllJugadoresPage(page);
		mav.addObject("jugadores", jugadores);
		mav.addObject("mensaje", session.getAttribute("jugadorPartidaActiva"));
		mav.addObject("messageType", "info");
		return mav;
	}


    @GetMapping(value = "/jugadores/delete/{jugadorId}")
    public String deleteJugador(@PathVariable("jugadorId") int jugadorId, HttpSession session){
        Optional<Jugador> opt = jugadorService.findJugadorById(jugadorId);
        if(opt.isPresent()){
            var jugador = opt.get();
           	jugadorService.deleteJugador(jugador, session);

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
			Optional<Jugador> jug = this.jugadorService.findJugadorById(jugadorId);
			Jugador juga = jug.get();
			jugador.setId(jugadorId);
			Integer a = juga.getPartidasGanadas();
			Integer b= juga.getPartidasJugadas();
			Integer c = juga.getRecordPuntos();
			Integer e = juga.getTotalPuntos();
			jugador.setPartidasGanadas(a);	
			jugador.setPartidasJugadas(b);
			jugador.setRecordPuntos(c);
			jugador.setTotalPuntos(e);
			Usuario usuarioBorrar = juga.getUsuario();
			this.jugadorService.saveJugador(jugador);
			this.usuarioService.deleteUsuario(usuarioBorrar);
			return "redirect:/jugadores/{jugadorId}";
		}
	}

    @GetMapping("/jugadores/{jugadorId}")
	public ModelAndView showJugador(@PathVariable("jugadorId") int jugadorId) {
		ModelAndView mav = new ModelAndView("jugadores/jugadorDetails");
		Integer barcosTotales = estadisticaService.numBarcosTotalesByJugador(jugadorId);
		Integer cartasTotales = estadisticaService.numCartasObtenidasByJugador(jugadorId);
		List<EstadísticaJugadorEnPartida> estadisticasPartidasDeJugador = estadisticaService.findByJugador(jugadorId);
		mav.addObject("jugador", this.jugadorService.findJugadorById(jugadorId).get());
		mav.addObject("estadisticas",estadisticasPartidasDeJugador);
		mav.addObject("numBarcos", barcosTotales);
		mav.addObject("numCartas", cartasTotales);
		
		return mav;
	}

	@GetMapping("/jugadores/rankingPuntos")
	public ModelAndView rankingPuntos(){
		ModelAndView mav = new ModelAndView(RANKINGPUNTOS);
		List<Jugador> jugadores = jugadorService.jugadoresOrderedByPuntos();
		mav.addObject("jugadores", jugadores);
		return mav;
	}

	@GetMapping("/jugadores/rankingPartidasGanadas")
	public ModelAndView rankingPartidasGanadas(){
		ModelAndView mav = new ModelAndView(RANKINGPARTIDAS);
		List<Jugador> jugadores = jugadorService.jugadoresOrderedByPartidasGanadas();
		mav.addObject("jugadores", jugadores);
		return mav;
	}
    
}
