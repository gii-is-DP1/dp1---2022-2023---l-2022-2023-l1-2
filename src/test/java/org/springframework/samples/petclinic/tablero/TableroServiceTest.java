package org.springframework.samples.petclinic.tablero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.samples.petclinic.estadisticasPartida.EstadisticaRepository;
import org.springframework.samples.petclinic.estadisticasPartida.EstadisticaService;
import org.springframework.samples.petclinic.estadisticasPartida.EstadísticaJugadorEnPartida;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorRepository;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.logro.LogroRepository;
import org.springframework.samples.petclinic.logro.LogroService;
import org.springframework.samples.petclinic.partida.EstadoPartida;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaRepository;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroRepository;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.usuario.AutoridadRepository;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioRepository;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class TableroServiceTest {

    @Mock
    TableroRepository repo;

    @Mock
    JugadorRepository repoJugador;

    @Mock
    PartidaRepository repoPartida;

    @Mock
    LogroRepository repoLogro;

    @Mock
    AutoridadRepository repoAut;

    @Mock
    EstadisticaRepository repoEstadistica;


    Tablero tab;
    Tablero tab2;
    Optional<Tablero> OpTab;

    TableroService tableroService;
    JugadorService jugadorService;
    PartidaService partidaService;
    LogroService logroService;
    AutoridadService administradorService;
    EstadisticaService estadisticaService;

    PasswordEncoder passwordEncoder;

    Usuario us;
    Pageable page;
    Partida partida;
    

    @BeforeEach
    public void config(){
        tab = new Tablero();
        tab.setId(1);
        tab.setBackground("fondo");
        tab.setHeight(200);
        tab.setWidth(200);
        

        partidaService = new PartidaService(repoPartida);
        jugadorService = new JugadorService(repoJugador);


        tableroService = new TableroService(repo,jugadorService, partidaService);

    }

    @Test
    public void findByIdTest(){
        OpTab = Optional.of(tab);
        when(repo.findById(1)).thenReturn(OpTab);
        Optional<Tablero> t = tableroService.findById(1);
        Tablero tablero = t.get();
        assertNotNull(t);
        assertEquals(tab, tablero);
    }

    @Test
    public void cambiarTurnoTest(){
        partida = new Partida(); 
        partida.setCodigo("hola");
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jug = new Jugador(); 
        
        Usuario us = new Usuario();
        us.setNombreUsuario("Pepe");
        us.setApellidos("Apellido");
        us.setNombre("Nombre");
        us.setContrasena("password");
        LocalDate fecha = LocalDate.of(2002,12,20);
        us.setFechaNacimiento(fecha);
        
        jug.setPartidasGanadas(3);
        jug.setPartidasJugadas(5);
        jug.setId(2);
        jug.setUsuario(us);
        jug.setTotalPuntos(10);
        jug.setRecordPuntos(7);

        Jugador jug2 = new Jugador();
        Usuario us2 = new Usuario();
        us2.setNombreUsuario("Pepe2");
        us2.setApellidos("Apellido");
        us2.setNombre("Nombre");
        us2.setContrasena("password");
        LocalDate fecha2 = LocalDate.of(2002,12,20);
        us.setFechaNacimiento(fecha2);

        jug2.setPartidasGanadas(3);
        jug2.setPartidasJugadas(5);
        jug2.setId(2);
        jug2.setUsuario(us);
        jug2.setTotalPuntos(10);
        jug2.setRecordPuntos(7);

        jugadores.add(jug);
        jugadores.add(jug2);

        partida.setJugadores(jugadores);
        partida.setJugadorActual(jug);

        tableroService.cambiarTurno(partida);
        Mockito.verify(repoPartida).save(partida);
       


    }

    



}
