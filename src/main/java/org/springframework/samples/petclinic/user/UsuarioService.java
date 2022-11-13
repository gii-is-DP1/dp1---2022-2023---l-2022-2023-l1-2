
package org.springframework.samples.petclinic.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository userRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	// @Transactional
	// public void saveUser(Usuario user) throws DataAccessException {
	// 	user.setEnabled(true);
	// 	userRepository.save(user);
	// }

	// public Optional<Usuario> findUser(String username) {
	// 	return userRepository.findById(username);
	// }
}
