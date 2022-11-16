package org.springframework.samples.petclinic.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios, "El repositorio he devuelto una lísta nula");
        assertEquals(3, usuarios.size(),"Faltan datos de inicialización");
    }

    @Test
    public void testFindByNombreUsuario(){
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario("Pepe");
        assertNotNull(usuario);
        assertFalse(usuario.isEmpty());
    }
    
}
