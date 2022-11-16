package org.springframework.samples.petclinic.jugador;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.usuario.Usuario;

import java.time.LocalDate;
import java.util.List;


@DataJpaTest
public class JugadorRepositoryTest {
    

    @Autowired JugadorRepository jugadorRepository;

    @Test
    public void testFindAll(){
        List<Jugador> jugadores = jugadorRepository.findAll();
        assertNotNull(jugadorRepository);
        assertFalse(jugadores.isEmpty());
    }

    @Test
    public void testFindByUsuario(){
        LocalDate cumple = LocalDate.of(2000, 03, 10);
        Usuario pepe = new Usuario();
        pepe.setNombreUsuario("Pepe");
        pepe.setContrasena("pepe");
        pepe.setFechaNacimiento(cumple);
        pepe.setEnabled(true);
        Jugador jug = jugadorRepository.findByUsuario(pepe);
        assertNotNull(jug);
       

    }
}
