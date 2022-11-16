package org.springframework.samples.petclinic.jugador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	
}
