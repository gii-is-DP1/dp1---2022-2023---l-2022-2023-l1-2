package org.springframework.samples.petclinic.usuario;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    private AdministradorRepository administradoRepository;
	private UsuarioService userService;

    @Autowired
	public AdministradorService(AdministradorRepository administradorRepository,UsuarioService usuarioService) {
		this.administradoRepository = administradorRepository;
		this.userService= usuarioService;
	}

	@Transactional
	public void saveAdministradores(Administrador ad) throws DataAccessException{
		administradoRepository.save(ad);
	}

	@Transactional
	public void saveAdministradores(String nombreUsuario, String rol) throws DataAccessException{
		Administrador ad = new Administrador();
		Optional<Usuario> usuario = userService.findUsuario(nombreUsuario);
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
