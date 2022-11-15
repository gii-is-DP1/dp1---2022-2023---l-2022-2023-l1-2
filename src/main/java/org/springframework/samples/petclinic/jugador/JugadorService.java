package org.springframework.samples.petclinic.jugador;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Service;




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

	public Jugador save(Jugador j){
		return jugadorRepository.save(j);
	}
	@Transactional
	public void saveJugador(Jugador jugador) throws DataAccessException {
		//creating owner
		jugadorRepository.save(jugador);		
		//creating user
		usuarioService.saveUser(jugador.getUsuario());
		//creating authorities
		administradorService.saveAdministradores(jugador.getUsuario().getNombreUsuario(),"jugador");
	
	}	
}
