package org.springframework.samples.petclinic.partida;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaService;
import org.springframework.samples.petclinic.carta.TipoCarta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private static final String FINPARTIDA = "partidas/fin";

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
            @PathVariable("partidaId") int partidaId, HttpSession sesion) {
        if (result.hasErrors()) {
            return PARTIDA_JOIN;
        } else {
            Partida p = partidaService.findById(partidaId).get();
            if (!(p.getCodigo().equals(partida.getCodigo()))) {
                String message = "El código no es correcto";
                sesion.setAttribute("message", message);
                return "redirect:/partidas/join";
            } else {
                Usuario u = usuarioService.findUserByNombreUsuario(principal.getName()).get();
                Jugador j = jugadorService.findByUsuario(u);
                List<Jugador> set = p.getJugadores();
                sesion.removeAttribute("message");
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
            partida.setJugadorActual(jug);
			List<Jugador> set = new ArrayList<>();
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
        mav.addObject("partida",this.partidaService.findById(partidaId));
        mav.addObject("jugador", user);
        mav.addObject("message", sesion.getAttribute("messageNumJugadores"));
        mav.addObject("messageType", "info");
        return mav;
 	}



	@GetMapping("/join2")
	public ModelAndView listPartidas(HttpSession sesion){
		ModelAndView mav = new ModelAndView("partidas/listPartidas");
		List<Partida> partidas = partidaService.findAllPartidas();
		List<Partida> partidasEnCola =partidas.stream().filter(x->x.getEstado()==EstadoPartida.EN_COLA).collect(Collectors.toList());
		mav.addObject("message", sesion.getAttribute("message"));
        mav.addObject("messageType", "info");
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

    @GetMapping("/{partidaId}/inicio")
    public String iniciarPartida(@PathVariable("partidaId") int partidaId, Principal principal, HttpSession sesion){
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
        Jugador jug = jugadorService.findByUsuario(user.get());
        Partida p = this.partidaService.findById(partidaId).get();
        if(p.getCreador().equals(jug) && p.getEstado().equals(EstadoPartida.EN_COLA) && partidaService.numeroCorrecto(p)){
            p.setEstado(EstadoPartida.EN_CURSO);
            List<Carta> cartas = cartasAleatorias(p);
            p.setCartas(cartas);    
            partidaService.save(p);
            return "redirect:/partidas/"+partidaId+"/tablero";
        }else{
            if(p.getJugadores().contains(jug)){
                if(!partidaService.numeroCorrecto(p)){
                    String message = "El número de jugadores debe estar entre 2 y 4";
                    sesion.setAttribute("messageNumJugadores", message);
                    return "redirect:/partidas/"+partidaId;
                }else{
                    sesion.removeAttribute("messageNumJugadores");
                    return "redirect:/partidas/"+partidaId;
                }
            }else{
                return "redirect:/";
            }
            
        }
    }

    public List<Carta> cartasAleatorias(Partida p){
        List<Carta> cartas = p.getCartas();
        if(cartas.isEmpty() || cartas == null){
            cartas = cartaService.findAllCartas();
            cartas.stream().forEach(c->c.setPosicion(7));
            List<Carta> doblones = cartas.stream().filter(x->x.getTipoCarta().equals(TipoCarta.DOBLON)).collect(Collectors.toList());
            List<Carta> utilizadas = new ArrayList<>();
            Integer x =0;
            for(int j=0;j<p.getJugadores().size();j++){
                Jugador jug = p.getJugadores().get(j);
                for(int d = x; d<x+3;d++){
                    Carta c = doblones.get(d);
                    utilizadas.add(c);
                    c.setJugador(jug);
                    c.setPosicion(0);
                    cartaService.save(c);
                }
                x=x+3;
            }
            cartas.removeAll(utilizadas);
            Collections.shuffle(cartas);
            cartas.stream().forEach(c->cartaService.save(c));
            for(int i =0; i<6;i++){
                Carta carta = cartas.get(i);
                carta.setPosicion(i+1);
                cartaService.save(carta);
            }
        }
        return cartas;
    }
	@GetMapping("/{partidaId}/tablero")
	public ModelAndView showTablero(@PathVariable("partidaId") int partidaId, HttpServletResponse response, Principal principal, HttpSession sesion) {
		response.addHeader("Refresh", "10");
        ModelAndView mav = new ModelAndView(TABLERO);
        Integer valorDado = (Integer) sesion.getAttribute("valordado");
		
        List<Integer> islas = List.of(1,2,3,4,5,6);
        mav.addObject("partida",this.partidaService.findById(partidaId));
        mav.addObject("tablero", tableroService.findById(1).get());
        mav.addObject("cartasIniciales", this.partidaService.findById(partidaId).get().getCartas());
        mav.addObject("message", sesion.getAttribute("doblonesInsuficientes"));
        mav.addObject("message2", sesion.getAttribute("turnoIncorrecto"));
        mav.addObject("message3", sesion.getAttribute("dadoNoTirado"));
        mav.addObject("message4", sesion.getAttribute("dadoYaTirado"));
        mav.addObject("messageType", "info");
        mav.addObject("islas", islas);
        mav.addObject("dado", valorDado);
        
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
        Jugador jug = jugadorService.findByUsuario(user.get());
        List<Carta> cartasJug = cartaService.findByJugador(jug.getId());
        if(cartasJug!=null && cartasJug.size()>=0){
            Map<TipoCarta, Integer> cartasPorTipo = new HashMap<>();
            for (Carta c : cartasJug) {
                if(cartasPorTipo.containsKey(c.getTipoCarta())){
                    Integer cont = cartasPorTipo.get(c.getTipoCarta());
                    cartasPorTipo.put(c.getTipoCarta(), cont +1);
                }else{
                    cartasPorTipo.put(c.getTipoCarta(),1);
                }
            }
            mav.addObject("cartasJugador", cartasPorTipo);
        }
            
        return mav;
	}

    @GetMapping("/{partidaId}/tablero/{cartaId}")
    public String cogerCarta(@PathVariable("partidaId") int partidaId, @PathVariable("cartaId") int cartaId, Principal principal){
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
        Jugador jug = jugadorService.findByUsuario(user.get());
        Partida p = this.partidaService.findById(partidaId).get();
        Carta c = cartaService.findById(cartaId).get();

        if(p.getJugadores().contains(jug) && c.getPosicion()!=0 && c.getPosicion()!=7){
            Integer pos = c.getPosicion();
            c.setPosicion(0);
            c.setJugador(jug);
            cartaService.save(c);

            for(int i=7; i<p.getCartas().size();i++){
                if(p.getCartas().get(i).getPosicion()==7){
                    Carta nuevaCarta = p.getCartas().get(i);
                    nuevaCarta.setPosicion(pos);
                    cartaService.save(nuevaCarta);
                    break;
                }
            }

        }
        partidaService.cambiarTurno(p);
        if (partidaService.partidaFinalizada(partidaId)){
            return "redirect:/partidas/"+partidaId+"/fin";
        } else {
            return "redirect:/partidas/"+partidaId+"/tablero";
        }
    }



    @GetMapping("/{partidaId}/tablero/viajar")
    public String viajarAIsla(@PathVariable("partidaId") int partidaId, @RequestParam("isla") Integer isla, Principal principal, HttpSession session){
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
        Jugador jug = jugadorService.findByUsuario(user.get());
        Partida p = partidaService.findById(partidaId).get();

        List<Carta> cartasJugador = cartaService.findByJugador(jug.getId());
        List<Carta> doblones = cartasJugador.stream().filter(x->x.getTipoCarta().equals(TipoCarta.DOBLON)).collect(Collectors.toList());
        Integer numDoblones = doblones.size();
        System.out.println(isla);
        
        Integer posicionCartaActual = (Integer)  session.getAttribute("valordado");
        if(p.getJugadorActual().equals(jug)){
            if(p.getDadoTirado()){
                session.removeAttribute("dadoNoTirado");
                if(partidaService.doblonesSuficientes(isla,posicionCartaActual , numDoblones)){
                    Carta cartaDestino = cartaService.findByPosicion(isla);
                    Integer numDoblonesARetirar = Math.abs(isla-posicionCartaActual);
                    cartaService.retirarDoblones(doblones, numDoblonesARetirar);
                    session.removeAttribute("doblonesInsuficientes");
                    return "redirect:/partidas/{partidaId}/tablero/"+cartaDestino.getId();
                }else{
                    String mensaje = "No tienes doblones suficientes";
                    session.setAttribute("doblonesInsuficientes", mensaje);
                    return "redirect:/partidas/{partidaId}/tablero";
                }
            }else{
                String mensaje = "No has tirado el dado";
                session.setAttribute("dadoNoTirado", mensaje);
                return "redirect:/partidas/{partidaId}/tablero";
            }
            
        }else{
            String mensaje = "No es tu turno";
            session.setAttribute("turnoIncorrecto", mensaje);
            return "redirect:/partidas/{partidaId}/tablero";
        }
        
    }

    @GetMapping("/{partidaId}/tablero/cogerCarta")
    public String cogerCartaDado(@PathVariable("partidaId") int partidaId, HttpSession sesion, Principal principal){
        Partida p = partidaService.findById(partidaId).get();
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
        Jugador jug = jugadorService.findByUsuario(user.get());

        if(p.getJugadorActual().equals(jug)){
            if(p.getDadoTirado().equals(true)){
                sesion.removeAttribute("dadoNoTirado");
                Integer dado = (Integer) sesion.getAttribute("valordado");
                Carta c = cartaService.findByPosicion(dado);
                return "redirect:/partidas/{partidaId}/tablero/"+c.getId();
            }else{
                String mensaje = "No has tirado el dado";
                sesion.setAttribute("dadoNoTirado", mensaje);
                return "redirect:/partidas/{partidaId}/tablero";
            }
            
        }else{
            System.out.println("Opción 2");
            String mensaje = "No es tu turno";
            sesion.setAttribute("turnoIncorrecto", mensaje);
            return "redirect:/partidas/{partidaId}/tablero";
        }
        
    }

   

    @GetMapping("/{partidaId}/fin")
    public ModelAndView finishPartida(@PathVariable("partidaId") int partidaId) {
        ModelAndView mav = new ModelAndView(FINPARTIDA);
        Partida partida = partidaService.findById(partidaId).get();
        partida.setEstado(EstadoPartida.FINALIZADA);
        Map<String, Integer> map = partidaService.contarPuntos(partidaId);
        partidaService.comprobarLogrosPartidaFinalizada(partidaId);
        partida.setHoraFin(LocalTime.now());
        String nameGanador = map.keySet().stream().max(Comparator.comparing(k->map.get(k))).get();
        Usuario usuarioGanador = usuarioService.findUserByNombreUsuario(nameGanador).get();
        Jugador jugadorGanJugador = jugadorService.findByUsuario(usuarioGanador);
        partida.setGanador(jugadorGanJugador);
        partidaService.save(partida);
        mav.addObject("partida",this.partidaService.findById(partidaId).get());
        mav.addObject("ganador", nameGanador);
        mav.addObject("duracion", Duration.between(partida.getHoraInicio(), partida.getHoraFin()).toMinutes());
        mav.addObject("puntuacion",map);
        return mav;
    }


    
}
