package org.springframework.samples.petclinic.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {


    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }

    public void deleteUsuario(Integer id){
        usuarioRepository.deleteById(id);
    }


}
