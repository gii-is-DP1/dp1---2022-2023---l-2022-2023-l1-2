package org.springframework.samples.petclinic.tablero;

import java.util.List;
import java.util.Optional;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
	private TableroRepository tableroRepository;

    
    private PartidaService partidaService;
    private JugadorService jugadorService;


    private static final String REDIRECT_TABLERO = "redirect:/tableros/";

    @Autowired
	public TableroService(TableroRepository tableroRepository, JugadorService jugadorService, PartidaService partidaService) {
		this.tableroRepository = tableroRepository;
        this.jugadorService = jugadorService;
        this.partidaService = partidaService;
	}
	
	public Optional<Tablero> findById(Integer id){
		return tableroRepository.findById(id);
	}


    @Transactional
	public void cambiarTurno(Partida p){
        Integer n = p.getJugadores().size();
        List<Jugador> jugadores = p.getJugadores();
        Integer poscionActual = jugadores.indexOf(p.getJugadorActual());
        Integer posicionJugadorProximo = (poscionActual+1)%n;

        Jugador jugadorProximo = jugadores.get(posicionJugadorProximo);
        p.setJugadorActual(jugadorProximo);  
        p.setDadoTirado(false);
        partidaService.save(p);
    }

}
