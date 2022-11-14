package org.springframework.samples.petclinic.usuario;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }

    @Transactional
	public void saveUser(Usuario user) throws DataAccessException {
		user.setEnabled(true);
		usuarioRepository.save(user);
	}

	public Optional<Usuario> findUsuario(String username) {
		return usuarioRepository.findById(username);
	}
}
