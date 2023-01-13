package org.springframework.samples.petclinic.jugador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        assertEquals(6, jugadores.size(),"Faltan datos de inicialización");
    }

    @Test
    public void testFindByUsuario(){
        Usuario usu = usuarioRepository.findById("Pepe").get();
        Jugador jug = jugadorRepository.findByUsuario(usu);
        assertNotNull(jug);
        assertEquals(1,jug.getPartidasJugadas() );
        assertEquals(0,jug.getPartidasGanadas() );
        assertEquals(12,jug.getTotalPuntos() );
        assertEquals(12,jug.getRecordPuntos() );

    }

    @Test
    public void testFindAllOrderedByPuntos(){
        Pageable page = Pageable.unpaged();
        Page<Jugador> jugadoresOrderedByPuntos = jugadorRepository.findAllOrderedByPuntos(page);
        assertNotNull(jugadoresOrderedByPuntos);
        assertEquals(jugadoresOrderedByPuntos.getContent().get(0).getUsuario().getNombreUsuario(), "Pablo");
    }

    @Test
    public void testFindAllOrderedByPartidasGanadas(){
        Pageable page = Pageable.unpaged();
        Page<Jugador> jugadoresOrderedByPartidasGanadas = jugadorRepository.findAllOrderedByPartidasGanadas(page);
        assertNotNull(jugadoresOrderedByPartidasGanadas);
        assertEquals(jugadoresOrderedByPartidasGanadas.getContent().get(0).getUsuario().getNombreUsuario(), "Hola");
    }

    @Test
    public void testFindAllJugadoresPage(){
        Pageable page = Pageable.unpaged();
        Page<Jugador> jugadores = jugadorRepository.findAllJugadoresPage(page);
        assertNotNull(jugadores);
        assertFalse(jugadores.isEmpty());
        assertEquals(6, jugadores.getContent().size(),"Faltan datos de inicialización");
    }
}
