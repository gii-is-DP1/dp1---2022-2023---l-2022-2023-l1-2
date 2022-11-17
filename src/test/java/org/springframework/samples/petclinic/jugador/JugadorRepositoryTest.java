package org.springframework.samples.petclinic.jugador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;


@DataJpaTest
public class JugadorRepositoryTest {
    

    @Autowired 
    JugadorRepository jugadorRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll(){
        List<Jugador> jugadores = jugadorRepository.findAll();
        assertNotNull(jugadores);
        assertFalse(jugadores.isEmpty());
        assertEquals(3, jugadores.size(),"Faltan datos de inicialización");
    }

    @Test
    public void testFindByUsuario(){
        Usuario usu = usuarioRepository.findById("Pepe").get();
        Jugador jug = jugadorRepository.findByUsuario(usu);
        assertNotNull(jug);
        assertEquals(10,jug.getPartidasJugadas() );
        assertEquals(2,jug.getPartidasGanadas() );
        assertEquals(21,jug.getTotalPuntos() );
        assertEquals(12,jug.getRecordPuntos() );

    }
}
