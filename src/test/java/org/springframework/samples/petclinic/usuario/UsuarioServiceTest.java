package org.springframework.samples.petclinic.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
   
    @Mock
    UsuarioRepository repo;

    UsuarioService usuarioService;
    PasswordEncoder passwordEncoder;
    
    Usuario us;
    Optional<Usuario> opUser;

    @BeforeEach
    public void config(){
        us = new Usuario();
        us.setNombreUsuario("test");
        us.setContrasena("password");
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        usuarioService = new UsuarioService(repo, passwordEncoder);
    }


    @Test
    public void findAllTest(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(us);
        when(repo.findAll()).thenReturn(usuarios);
    
        List<Usuario> UsuarioNew = new ArrayList<>();
        UsuarioNew.add(us);
        List<Usuario> usuarios2 = usuarioService.findAllUsuarios();
        assertNotNull(usuarios2);
        assertEquals(UsuarioNew, usuarios2);
    }

    @Test
    public void findUserByNombreUsuarioTest(){
        opUser = Optional.of(us);
        when(repo.findByNombreUsuario(any(String.class))).thenReturn(opUser);
        Optional<Usuario> u = usuarioService.findUserByNombreUsuario("test");
        Usuario user = u.get();
        assertNotNull(u);
        assertEquals(us, user);
    }

    @Test
    public void deleteUsuarioTest(){
        usuarioService.deleteUsuario(us);
        Mockito.verify(repo).delete(us);
    }

    @Test
    public void saveUsuarioTest(){
        usuarioService.saveUser(us);
        Mockito.verify(repo).save(us);
    }
    
}
