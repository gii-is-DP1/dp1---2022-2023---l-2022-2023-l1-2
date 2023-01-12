package org.springframework.samples.petclinic.jugador;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.estadisticasPartida.EstadisticaService;
import org.springframework.samples.petclinic.estadisticasPartida.EstadísticaJugadorEnPartida;
import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.logro.LogroService;
import org.springframework.samples.petclinic.partida.EstadoPartida;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaRepository;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
public class JugadorService {
    private JugadorRepository jugadorRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PartidaService partidaService;

	@Autowired 
	private LogroService logroService;

	@Autowired
	private AutoridadService administradorService;

	@Autowired
	private EstadisticaService estadisticaService;

    @Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	public JugadorService(JugadorRepository jugadorRepository, PartidaService partidaService, LogroService logroService, EstadisticaService estadisticaService, UsuarioService usuarioService) {
		this.jugadorRepository = jugadorRepository;
		this.partidaService = partidaService;
		this.logroService=logroService;
		this.estadisticaService=estadisticaService;
		this.usuarioService=usuarioService;
	}

	public List<Jugador> findAll(){
		return jugadorRepository.findAll();
	}
	public Jugador findByUsuario(Usuario usuario){
		return jugadorRepository.findByUsuario(usuario);
	}

	public Page<Jugador> findAllJugadoresPage(Pageable page){
		return jugadorRepository.findAllJugadoresPage(page);
	}

	@Transactional
	public void saveJugador(Jugador jugador) throws DataAccessException {
		//creating jugador
		
		jugadorRepository.save(jugador);	
		
		//creating user
		usuarioService.saveUser(jugador.getUsuario());
		//creating authorities
		administradorService.saveAdministradores(jugador.getUsuario().getNombreUsuario(),"jugador");
	
	}	

	@Transactional(readOnly = true)
	public Optional<Jugador> findJugadorById(int id) throws DataAccessException {
		return jugadorRepository.findById(id);
	}

	@Transactional
	public void deleteJugador(Jugador jugador, HttpSession sesion) throws DataAccessException{
		List<Partida> partidas = partidaService.partidasByPlayer(jugador.getId());

		if(partidas.stream().allMatch(x->x.getEstado().equals(EstadoPartida.FINALIZADA))){

			Optional<List<Logro>> logrosOptional = logroService.LogroByPlayer(jugador.getId());
			List<Logro> logros = logrosOptional.isPresent()?logrosOptional.get(): new ArrayList<>();

			for (Logro l: logros){
				l.getJugadores().remove(jugador);
				logroService.save(l);
			}

			List<EstadísticaJugadorEnPartida> estadisticasJugador = estadisticaService.findByJugador(jugador.getId());
			estadisticasJugador.stream().forEach(e->estadisticaService.delete(e));
			for (Partida p: partidas){
				if (p.getCreador().equals(jugador)){
					p.setCreador(null);
				}
				if(p.getJugadorActual().equals(jugador)){
					p.setJugadorActual(null);
				}
				if(p.getGanador().equals(jugador)){
					p.setGanador(null);
				}
				p.getJugadores().remove(jugador);
				partidaService.save(p);
			
			}
		jugadorRepository.delete(jugador);
		}
		else{
			sesion.setAttribute("jugadorPartidaActiva", "No se puede eliminar un jugador que este en una partida activa");
		}
	}

	@Transactional
	public void actualizarEstadisticasJugador(Principal principal, Map<String, Integer> puntuaciones, Partida partida){

		String nombreUsuario = principal.getName();
		Usuario us = usuarioService.findUserByNombreUsuario(nombreUsuario).get();
		Jugador j = findByUsuario(us);
		j.setTotalPuntos(j.getTotalPuntos()+puntuaciones.get(nombreUsuario));
		if(puntuaciones.get(nombreUsuario)>j.getRecordPuntos()){
			j.setRecordPuntos(puntuaciones.get(nombreUsuario));
		}
		j.setPartidasJugadas(j.getPartidasJugadas()+1);
		if(partida.getGanador().equals(j)){
			j.setPartidasGanadas(j.getPartidasGanadas()+1);
		}
		jugadorRepository.save(j);
	}

	public Page<Jugador> jugadoresOrderedByPuntos(Pageable page){
		return jugadorRepository.findAllOrderedByPuntos(page);
	}

	public Page<Jugador> jugadoresOrderedByPartidasGanadas(Pageable page){
		return jugadorRepository.findAllOrderedByPartidasGanadas(page);
	}

	

	
}
