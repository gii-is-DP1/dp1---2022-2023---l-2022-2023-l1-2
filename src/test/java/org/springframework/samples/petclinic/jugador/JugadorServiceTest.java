package org.springframework.samples.petclinic.jugador;

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
import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.logro.LogroRepository;
import org.springframework.samples.petclinic.logro.LogroService;
import org.springframework.samples.petclinic.partida.EstadoPartida;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaRepository;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.usuario.AutoridadRepository;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioRepository;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class JugadorServiceTest {

    @Mock
    JugadorRepository repo;

    @Mock
    UsuarioRepository repoUsuario;

    @Mock
    PartidaRepository repoPartida;

    @Mock
    LogroRepository repoLogro;

    @Mock
    AutoridadRepository repoAut;

    @Mock
    EstadisticaRepository repoEstadistica;


    Jugador jug;
    Jugador jug2;
    Optional<Jugador> OpJug;

    JugadorService jugadorService;
    UsuarioService usuarioService;
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
        us = new Usuario();
        us.setNombreUsuario("Pepe");
        us.setApellidos("Apellido");
        us.setNombre("Nombre");
        us.setContrasena("password");
        LocalDate fecha = LocalDate.of(2002,12,20);
        us.setFechaNacimiento(fecha);
        jug = new Jugador();
        jug.setPartidasGanadas(3);
        jug.setPartidasJugadas(5);
        jug.setId(2);
        jug.setUsuario(us);
        jug.setTotalPuntos(10);
        jug.setRecordPuntos(7);
        jug2 = new Jugador();
        jug2.setTotalPuntos(11);
        jug2.setPartidasGanadas(2);

        partidaService = new PartidaService(repoPartida);
        logroService = new LogroService(repoLogro);
        estadisticaService = new EstadisticaService(repoEstadistica);
        usuarioService = new UsuarioService(repoUsuario, passwordEncoder);

        jugadorService = new JugadorService(repo, partidaService, logroService, estadisticaService, usuarioService);

        
        page = Pageable.unpaged();

        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
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

    @Test
    public void findAllJugadoresPageTest(){
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jug);
        Page<Jugador> jugadoresPage = new PageImpl<>(jugadores,page,jugadores.size());
        when(repo.findAllJugadoresPage(any(Pageable.class))).thenReturn(jugadoresPage);
    
        List<Jugador> jugadoresNew = new ArrayList<>();
        jugadoresNew.add(jug);
        Page<Jugador> jugadores2 = jugadorService.findAllJugadoresPage(page);
        assertNotNull(jugadores2);
        assertEquals(jugadoresNew, jugadores2.getContent());
    }

    @Test
    public void jugadoresOrderedByPuntosTest(){
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jug);
        jugadores.add(jug2);
        Page<Jugador> jugadoresPage = new PageImpl<>(jugadores,page,jugadores.size());
        when(repo.findAllOrderedByPuntos(any(Pageable.class))).thenReturn(jugadoresPage);
        List<Jugador> jugadoresNew = new ArrayList<>();
        jugadoresNew.add(jug);
        jugadoresNew.add(jug2);
        Page<Jugador> jugadores2 = jugadorService.jugadoresOrderedByPuntos(page);
        assertNotNull(jugadores2);
        assertEquals(jugadoresNew, jugadores2.getContent());

    }

    @Test
    public void jugadoresOrderedByPartidasGanadasTest(){
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jug);
        jugadores.add(jug2);
        Page<Jugador> jugadoresPage = new PageImpl<>(jugadores,page,jugadores.size());
        when(repo.findAllOrderedByPartidasGanadas(any(Pageable.class))).thenReturn(jugadoresPage);
        List<Jugador> jugadoresNew = new ArrayList<>();
        jugadoresNew.add(jug);
        jugadoresNew.add(jug2);
        Page<Jugador> jugadores2 = jugadorService.jugadoresOrderedByPartidasGanadas(page);
        assertNotNull(jugadores2);
        assertEquals(jugadoresNew, jugadores2.getContent());

    }

    @Test
    public void deleteJugadorTestCorrecto(){
        Partida partida = new Partida();
        partida.setId(1);
        partida.setCreador(jug);
        partida.setEstado(EstadoPartida.FINALIZADA);
        partida.setJugadorActual(jug);
        partida.setGanador(jug);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jug);
        partida.setJugadores(jugadores);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);

        List<Logro> listaLogros = new ArrayList<>();
        Logro logro = new Logro();

        Set<Jugador> jugadoresConLogro = new HashSet<>();
        jugadoresConLogro.add(jug);
        logro.setJugadores(jugadoresConLogro);
        listaLogros.add(logro);
        Optional<List<Logro>> optListaLogros = Optional.of(listaLogros);

        EstadísticaJugadorEnPartida estadistica = new EstadísticaJugadorEnPartida();
        estadistica.setJugador(jug);
        List<EstadísticaJugadorEnPartida> listaEstadisticas = new ArrayList<>();
        listaEstadisticas.add(estadistica);


        when(repoPartida.findByPlayer(any(Integer.class))).thenReturn(partidas);
        when(repoLogro.findLogrosByPlayer(any(Integer.class))).thenReturn(optListaLogros);
        when(repoEstadistica.findByJugador(any(Integer.class))).thenReturn(listaEstadisticas);

        jugadorService.deleteJugador(jug, any(HttpSession.class));
        Mockito.verify(repo).delete(jug);
        
    }

    @Test
    public void deleteJugadorTestPartidaEnCurso(){
        Partida partida = new Partida();
        partida.setEstado(EstadoPartida.EN_CURSO);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);

        when(repoPartida.findByPlayer(any(Integer.class))).thenReturn(partidas);
        HttpSession sesionMock = new MockHttpSession();
        jugadorService.deleteJugador(jug, sesionMock);
        Mockito.verifyNoInteractions(repo);
        
    }

    @Test
    public void actualizarEstadisticasJugadorTest(){
        Optional<Usuario> optUsuario = Optional.of(us);
        when(repoUsuario.findByNombreUsuario(any(String.class))).thenReturn(optUsuario);
        when(repo.findByUsuario(any(Usuario.class))).thenReturn(jug);
        Map<String, Integer> puntuaciones = new HashMap<>();
        puntuaciones.put("Pepe", 10);
        Principal principal = new Principal(){
            public String getName(){
                return "Pepe";
            }
        };
        partida = new Partida();
        partida.setGanador(jug);
        jugadorService.actualizarEstadisticasJugador(principal, puntuaciones, partida);
        Mockito.verify(repo).save(jug);
    }



}
