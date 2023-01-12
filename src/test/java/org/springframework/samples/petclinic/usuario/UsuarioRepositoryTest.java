package org.springframework.samples.petclinic.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
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
        assertNotNull(usuarios, "El repositorio he devuelto una lista nula");
        assertEquals(6, usuarios.size(),"Faltan datos de inicialización");
    }

    @Test
    public void testFindByNombreUsuario(){
        Optional<Usuario> usuarioOp = usuarioRepository.findByNombreUsuario("Pepe");
        Usuario usuario = usuarioOp.get();
        LocalDate fechaNacimiento = LocalDate.of(2001, 1, 10);
        assertNotNull(usuarioOp);
        assertFalse(usuarioOp.isEmpty());
        assertEquals("$2a$10$QuFiLQHzfVwK0SUX3ZBGIOuln.wHqOXZv317SdBoIoft2E8YipLBa",usuario.getContrasena() );
        assertEquals(fechaNacimiento,usuario.getFechaNacimiento());
    }

    @Test
    public void testFindByNombreUsuarioFail(){
        Optional<Usuario> usuarioOp = usuarioRepository.findByNombreUsuario("Pepe");
        Usuario usuario = usuarioOp.get();
        LocalDate fechaNacimiento = LocalDate.of(2002, 1, 10);
        assertNotNull(usuarioOp);
        assertFalse(usuarioOp.isEmpty());
        assertNotEquals("constraseña",usuario.getContrasena() );
        assertNotEquals(fechaNacimiento,usuario.getFechaNacimiento());
    }

    @Test
    public void testFindByNombreUsuarioNotExists(){
        Optional<Usuario> usuarioOp = usuarioRepository.findByNombreUsuario("Antonio");
        assertTrue(usuarioOp.isEmpty());
    }
    
}
