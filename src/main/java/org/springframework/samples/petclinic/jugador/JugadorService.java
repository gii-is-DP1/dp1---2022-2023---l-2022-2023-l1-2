package org.springframework.samples.petclinic.jugador;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service
public class JugadorService {
    private JugadorRepository jugadorRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AutoridadService administradorService;

    @Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}

	public List<Jugador> findAll(){
		return jugadorRepository.findAll();
	}
	public Jugador findByUsuario(Usuario usuario){
		return jugadorRepository.findByUsuario(usuario);
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
	public void deleteJugador(Jugador jugador) throws DataAccessException{
		jugadorRepository.delete(jugador);
	}

	@Transactional
	public void actualizarEstadisticas(Principal principal, Map<String, Integer> puntuaciones, Partida partida){

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

	
}
