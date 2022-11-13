
package org.springframework.samples.petclinic.user;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public  UserService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Autowired
	public UserService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional
	public void saveUser(User usuario) throws DataAccessException {
		usuario.setEnabled(true);
		usuarioRepository.save(usuario);
	}
	
	public Optional<User> findUser(String usuarioname) {
		return usuarioRepository.findById(usuarioname);
	}
}
