package org.springframework.samples.petclinic.web;

import java.lang.StackWalker.Option;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.EstadoPartida;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.val;

@Controller
public class DicesOnSessionController {

    private final PartidaService partidaService;
	private final JugadorService jugadorService;
	private final UsuarioService usuarioService;

    @Autowired
	public DicesOnSessionController(PartidaService partidaService, JugadorService jugadorService, UsuarioService usuarioService) {
		this.partidaService = partidaService;
		this.jugadorService = jugadorService;
		this.usuarioService = usuarioService;

	}

    public static int NUM_DICES=1;
    public static int NUM_DICES_SIDES=6;
     
    @GetMapping("/session/rolldices")
    public @ResponseBody Integer[] rollDices(HttpSession session){
        Integer[] dices=new Integer[NUM_DICES];
        for(int i=0;i<NUM_DICES;i++)
            dices[i]=1+(int)Math.floor(Math.random()*NUM_DICES_SIDES);
        session.setAttribute("dices", dices);
        return dices;
    }
  
    @GetMapping("/session/setdices/{valordado}")
    public String setDices(HttpSession session, @PathVariable("valordado") Integer valordado, Principal principal){
        Optional<Usuario> user = usuarioService.findUserByNombreUsuario(principal.getName());
		Jugador jug = jugadorService.findByUsuario(user.get());
        List<Partida> partidasJugador = partidaService.partidasByPlayer(jug.getId());
        Partida partidaJugadorActual = partidasJugador.stream().filter(p->p.getEstado().equals(EstadoPartida.EN_COLA)|| p.getEstado().equals(EstadoPartida.EN_CURSO)).findFirst().orElse(null);
        if(partidaJugadorActual.getJugadorActual().equals(jug)){
            if(partidaJugadorActual.getDadoTirado().equals(false)){
                session.setAttribute("valordado", valordado);
                partidaJugadorActual.setDadoTirado(true);
                partidaService.save(partidaJugadorActual);
            }else{
                String mensaje = "Ya has tirado el dado";
                session.setAttribute("dadoYaTirado", mensaje);
            }
            
        }else{
            String mensaje = "No es tu turno";
            session.setAttribute("turnoIncorrecto", mensaje);
        }
        return "redirect:/partidas/"+partidaJugadorActual.getId()+"/tablero";
    }



}
