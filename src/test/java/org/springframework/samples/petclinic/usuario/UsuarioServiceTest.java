package org.springframework.samples.petclinic.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    
    @Mock
    UsuarioRepository repo;

    UsuarioService usuarioService;
    Usuario us;
    Optional<Usuario> opUser;

    @BeforeEach
    public void config(){
        us = new Usuario();
        us.setNombreUsuario("test");
        usuarioService = new UsuarioService(repo);
    }

    @Test
    public void findByIdTest(){
        opUser = Optional.of(us);
        when(repo.findByNombreUsuario("test")).thenReturn(opUser);
        Optional<Usuario> u = usuarioService.findUserByNombreUsuario("test");
        Usuario user = u.get();
        assertNotNull(u);
        assertEquals(us, user);
    }
    
}
