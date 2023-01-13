package org.springframework.samples.petclinic.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository=usuarioRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public List<Usuario> findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findUserByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    @Transactional
	public void saveUser(Usuario user) throws DataAccessException {
		user.setEnabled(true);
        String password = user.getContrasena();
        user.setContrasena(passwordEncoder.encode(password));
		usuarioRepository.save(user);
	}

    @Transactional
	public void deleteUsuario(Usuario usuario) throws DataAccessException{
		usuarioRepository.delete(usuario);
	}

}
