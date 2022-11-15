package org.springframework.samples.petclinic.usuario;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

@Service
public class AutoridadService {
    private AutoridadRepository administradoRepository;
	private UsuarioService userService;

    @Autowired
	public AutoridadService(AutoridadRepository administradorRepository,UsuarioService usuarioService) {
		this.administradoRepository = administradorRepository;
		this.userService= usuarioService;
	}

	@Transactional
	public void saveAdministradores(Autoridad ad) throws DataAccessException{
		administradoRepository.save(ad);
	}

	@Transactional
	public void saveAdministradores(String nombreUsuario, String rol) throws DataAccessException{
		Autoridad ad = new Autoridad();
		Optional<Usuario> usuario = userService.findUserByNombreUsuario(nombreUsuario);
		if(usuario.isPresent()){
			ad.setUsuario(usuario.get());
			ad.setAutoridad(rol);
			administradoRepository.save(ad);
		}
		else{
			throw new DataAccessException("Usuario '"+nombreUsuario+"' no encontrado!") {} ;

		}
	}
}
