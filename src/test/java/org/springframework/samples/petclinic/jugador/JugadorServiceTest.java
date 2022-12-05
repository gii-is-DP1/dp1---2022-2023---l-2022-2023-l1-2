package org.springframework.samples.petclinic.jugador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.usuario.Usuario;

@ExtendWith(MockitoExtension.class)
public class JugadorServiceTest {

    @Mock
    JugadorRepository repo;

    Jugador jug;
    Optional<Jugador> OpJug;
    JugadorService jugadorService;
    Usuario us;

    @BeforeEach
    public void config(){
        us = new Usuario();
        jug = new Jugador();
        jug.setPartidasGanadas(3);
        jug.setId(2);
        jug.setUsuario(us);
        jugadorService = new JugadorService(repo);
    }

    @Test
    public void findAllTest(){
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jug);
        when(repo.findAll()).thenReturn(jugadores);
    
        List<Jugador> jugadoresNew = new ArrayList<>();
        jugadoresNew.add(jug);
        List<Jugador> jugadores2 = jugadorService.findAll();
        assertNotNull(jugadores2);
        assertEquals(jugadoresNew, jugadores2);
    }

    @Test
    public void findByUsuarioTest(){
        when(repo.findByUsuario(us)).thenReturn(jug);
        Jugador j = jugadorService.findByUsuario(us);
        assertEquals(jug, j);
    }

    @Test
    public void findByIdTest(){
        OpJug = Optional.of(jug);
        when(repo.findById(2)).thenReturn(OpJug);
        Optional<Jugador> j = jugadorService.findJugadorById(2);
        Jugador jugad = j.get();
        assertNotNull(j);
        assertEquals(jug, jugad);
    }
    
}
