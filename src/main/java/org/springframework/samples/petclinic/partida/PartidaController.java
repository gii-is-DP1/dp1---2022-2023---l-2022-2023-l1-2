package org.springframework.samples.petclinic.partida;

import java.util.Optional;
import java.util.Random;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaService;
import org.springframework.samples.petclinic.carta.EstadoCarta;
import org.springframework.samples.petclinic.carta.TipoCarta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;
	private final JugadorService jugadorService;
	private final UsuarioService usuarioService;
    private final TableroService tableroService;
    private final CartaService cartaService;
    private static final String PARTIDA_CREATE = "partidas/createPartidas";
    private static final String PARTIDA_JOIN = "partidas/joinPartidas";
	private static final String TABLERO = "partidas/Tablero";

    @Autowired
	public PartidaController(PartidaService partidaService, JugadorService jugadorService, UsuarioService usuarioService, TableroService tableroService, CartaService cartaService) {
		this.partidaService = partidaService;
		this.jugadorService = jugadorService;
		this.usuarioService = usuarioService;
        this.tableroService = tableroService;
        this.cartaService = cartaService;
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
    public String initCreationForm(Map<String, Object> model, Principal principal) {
        Partida partida = new Partida();
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
		Jugador jug = jugadorService.findByUsuario(user.get());
        List<Partida> partidasJugador = partidaService.partidasByPlayer(jug.getId());
        Partida partidaJugadorActual = partidasJugador.stream().filter(p->p.getEstado().equals(EstadoPartida.EN_COLA)|| p.getEstado().equals(EstadoPartida.EN_CURSO)).findFirst().orElse(null);
        if(partidaJugadorActual==null){
            model.put("partida", partida);
            return PARTIDA_CREATE;
        }else{
            if(partidaJugadorActual.getEstado().equals(EstadoPartida.EN_COLA)){
                return "redirect:/partidas/" + partidaJugadorActual.getId();
            }else{
                return "redirect:/partidas/" + partidaJugadorActual.getId() +"/tablero";
            }
            
        }
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
 	public @ResponseBody ModelAndView showPartida(@PathVariable("partidaId") int partidaId, Principal principal, HttpServletResponse response, HttpSession sesion) {
 		response.addHeader("Refresh", "3");
        ModelAndView mav = new ModelAndView("partidas/showPartida");
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
		Partida p = this.partidaService.findById(partidaId).get();
        List<Carta> cartas = cartasAleatorias(p);
        
        p.setCartas(cartas);
        partidaService.save(p);
        mav.addObject("partida",this.partidaService.findById(partidaId));
        mav.addObject("jugador", user);
        return mav;
 	}

    public List<Carta> cartasAleatorias(Partida p){
        List<Carta> cartas = p.getCartas();
        if(cartas.isEmpty() || cartas == null || cartas.size() !=6){
            List<Carta> todasCartas = cartaService.findAllCartas();
            Collections.shuffle(todasCartas);
            cartas = todasCartas.subList(0, 6);

            for(int i =0; i<6;i++){
                Carta carta = cartas.get(i);
                carta.setPosicion(i+1);
                cartaService.save(carta);
            }
        }
        return cartas;
    }

	@GetMapping("/join2")
	public ModelAndView listPartidas(){
		ModelAndView mav = new ModelAndView("partidas/listPartidas");
		List<Partida> partidas = partidaService.findAllPartidas();
		List<Partida> partidasEnCola =partidas.stream().filter(x->x.getEstado()==EstadoPartida.EN_COLA).collect(Collectors.toList());
		mav.addObject("partidas", partidasEnCola);
		return mav;
	}

    @GetMapping("/join")
    public String unirse(Principal principal){
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
		Jugador jug = jugadorService.findByUsuario(user.get());
        List<Partida> partidasJugador = partidaService.partidasByPlayer(jug.getId());
        Partida partidaJugadorActual = partidasJugador.stream().filter(p->p.getEstado().equals(EstadoPartida.EN_COLA)|| p.getEstado().equals(EstadoPartida.EN_CURSO)).findFirst().orElse(null);
        if(partidaJugadorActual==null){
            return "redirect:/partidas/join2";
        }else{
            if(partidaJugadorActual.getEstado().equals(EstadoPartida.EN_COLA)){
                return "redirect:/partidas/" + partidaJugadorActual.getId();
            }else{
                return "redirect:/partidas/" + partidaJugadorActual.getId() +"/tablero";
            }
        }
    
    }
	@GetMapping("/{partidaId}/tablero")
	public ModelAndView showTablero(@PathVariable("partidaId") int partidaId, HttpServletResponse response, Principal principal, HttpSession sesion) {
		response.addHeader("Refresh", "2");
        ModelAndView mav = new ModelAndView(TABLERO);
		
        Partida partida = partidaService.findById(partidaId).get();
           if (partida.getJugadores().size() < 2 || partida.getJugadores().size() > 4) {
                throw new IllegalArgumentException(
                    "La partida no puede comenzar con el número de jugadores presentes en la sala");
            } else {
                mav.addObject("partida",this.partidaService.findById(partidaId));
                mav.addObject("tablero", tableroService.findById(1).get());
                mav.addObject("cartasIniciales", this.partidaService.findById(partidaId).get().getCartas());
                /*
                Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
                Jugador jug = jugadorService.findByUsuario(user.get());
                List<Carta> cartasJug = cartaService.findByJugador(jug.getId());
                if(cartasJug!=null && cartasJug.size()>=0){
                    Map<TipoCarta, List<Carta>> cartasPorTipo = cartasJug.stream().collect(Collectors.groupingBy(Carta::getTipoCarta));
                    mav.addObject("cartasJugador", cartasPorTipo);
                    System.out.println(cartasPorTipo.get(TipoCarta.DOBLON).size());
                }
                */
                partida.setEstado(EstadoPartida.EN_CURSO);
                partidaService.save(partida);
                return mav;
            }
	}
}
