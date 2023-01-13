
package org.springframework.samples.petclinic.controladores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaService;
import org.springframework.samples.petclinic.carta.TipoCarta;
import org.springframework.samples.petclinic.estadisticasPartida.EstadisticaService;
import org.springframework.samples.petclinic.estadisticasPartida.EstadísticaJugadorEnPartida;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.EstadoPartida;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.samples.petclinic.tablero.Tablero;
import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.usuario.Autoridad;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {
 
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JugadorService jugadorService;
    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private PartidaService partidaService;
    @MockBean
    private EstadisticaService estadisticaService;
    @MockBean
    private AutoridadService autoridadService;
    @MockBean
    private CartaService cartaService;
    @MockBean
    private TableroService tableroService;

    private Usuario user;

    private Jugador jugador;


    @BeforeEach
    public void config(){
        user = new Usuario();
        user.setNombreUsuario("Pepe");
        jugador = new Jugador();
        jugador.setId(1);
    }
  
    //Tests de JugadorController 

    @WithMockUser
    @Test
    public void listJugadoresTest() throws Exception{
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        Pageable page= Pageable.unpaged();
        Page<Jugador> jugadoresPage = new PageImpl<>(jugadores,page,jugadores.size());
        when(jugadorService.findAllJugadoresPage(any(Pageable.class))).thenReturn(jugadoresPage);
        mockMvc.perform(get("/jugadores/find"))
            .andExpect(status().isOk())
            .andExpect(view().name("jugadores/listJugadores"))
            .andExpect(model().attributeExists("jugadores"));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void deleteTest() throws Exception{
        when(jugadorService.findJugadorById(1)).thenReturn(Optional.of(jugador));

        mockMvc.perform(get("/jugadores/delete/1"))
            .andExpect(redirectedUrl("/jugadores/find"));
    }

    @WithMockUser
    @Test
    public void initUpdateJugadorFormTestCorrecto() throws Exception{
        Set<Autoridad> autoridades = new HashSet<>();
        Autoridad autoridad = new Autoridad();
        autoridad.setAutoridad("admin");
        autoridades.add(autoridad);
        user.setAdministrador(autoridades);
        jugador.setUsuario(user);
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        when(jugadorService.findJugadorById(any(Integer.class))).thenReturn(Optional.of(jugador));

        mockMvc.perform(get("/jugadores/edit/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("jugadores/updateForm"))
            .andExpect(model().attributeExists("jugador"));
    } 

    @WithMockUser
    @Test
    public void initUpdateJugadorFormTestIncorrecto() throws Exception{
        Set<Autoridad> autoridades = new HashSet<>();
        user.setAdministrador(autoridades);
        jugador.setId(2);
        jugador.setUsuario(user);
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        when(jugadorService.findJugadorById(any(Integer.class))).thenReturn(Optional.of(jugador));

        mockMvc.perform(get("/jugadores/edit/1"))
            .andExpect(redirectedUrl("/"));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void processUpdateJugadorFormTest() throws Exception{
        jugador.setUsuario(user);
        when(jugadorService.findJugadorById(any(Integer.class))).thenReturn(Optional.of(jugador));

        mockMvc.perform(post("/jugadores/edit/{jugadorId}", 1)
                .with(csrf())
                .param("nombre", "Nombre")
                .param("apellidos", "Apellidos")
                .param("contrasena", "Password")
                .param("nombreUsuario", "Pepe"))
            .andExpect(redirectedUrl("/jugadores/1"));
    }

    @WithMockUser
    @Test
    public void showJugadorTest() throws Exception{
        List<EstadísticaJugadorEnPartida> estadisticas = new ArrayList<>();
        EstadísticaJugadorEnPartida est = new EstadísticaJugadorEnPartida();
        estadisticas.add(est);
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(estadisticaService.numBarcosTotalesByJugador(any(Integer.class))).thenReturn(1);
        when(estadisticaService.numCartasObtenidasByJugador(any(Integer.class))).thenReturn(2);
        when(estadisticaService.findByJugador(any(Integer.class))).thenReturn(estadisticas);
        when(jugadorService.findJugadorById(1)).thenReturn(Optional.of(jugador));

        mockMvc.perform(get("/jugadores/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("jugadores/jugadorDetails"))
                .andExpect(model().attributeExists("jugador"))
                .andExpect(model().attributeExists("jugadorActual"))
                .andExpect(model().attributeExists("estadisticas"))
                .andExpect(model().attributeExists("numBarcos"))
                .andExpect(model().attributeExists("numCartas"));

    }

    @WithMockUser
    @Test
    public void profileTest() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);

        mockMvc.perform(get("/jugadores/profile"))
            .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void rankingPuntosTest() throws Exception{
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        Pageable page= Pageable.unpaged();
        Page<Jugador> jugadoresPage = new PageImpl<>(jugadores,page,jugadores.size());

        when(jugadorService.jugadoresOrderedByPuntos(any(Pageable.class))).thenReturn(jugadoresPage);

        mockMvc.perform(get("/jugadores/rankingPuntos"))
                .andExpect(status().isOk())
                .andExpect(view().name("jugadores/rankingPuntos"))
                .andExpect(model().attributeExists("jugadores"));
    }

    @WithMockUser
    @Test
    public void rankingPartidasGanadasTest() throws Exception{
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        Pageable page= Pageable.unpaged();
        Page<Jugador> jugadoresPage = new PageImpl<>(jugadores,page,jugadores.size());

        when(jugadorService.jugadoresOrderedByPartidasGanadas(any(Pageable.class))).thenReturn(jugadoresPage);

        mockMvc.perform(get("/jugadores/rankingPartidasGanadas"))
                .andExpect(status().isOk())
                .andExpect(view().name("jugadores/rankingPartidasGanadas"))
                .andExpect(model().attributeExists("jugadores"));
    }

    //Test de PartidaController

    @WithMockUser
    @Test
    public void showPartidaTest() throws Exception{
        Usuario user = new Usuario();
        user.setNombreUsuario("Pepito");
        Jugador jugador = new Jugador();
        jugador.setId(9);
        jugador.setUsuario(user);

        Partida partida = new Partida();
        partida.setId(9);

        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(partidaService.findById(any(Integer.class))).thenReturn(Optional.of(partida));

        mockMvc.perform(get("/partidas/{partidaId}",9))
            .andExpect(status().isOk()) 
            .andExpect(view().name("partidas/showPartida"))
            .andExpect(model().attributeExists("partida"))
            .andExpect(model().attributeExists("jugador"));
    }


    @WithMockUser
    @Test
    public void initUnirPartidaTest() throws Exception{
        mockMvc.perform(get("/partidas/join/{partidaId}",1))
            .andExpect(status().isOk())
            .andExpect(view().name("partidas/joinPartidas"))
            .andExpect(model().attributeExists("partida"));
    }

    @WithMockUser(authorities = "admin")
    @Test
    public void initCreationFormTestSinPartidaActual() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.FINALIZADA);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);
        when(partidaService.partidasByPlayer(any(Integer.class))).thenReturn(partidas);

        mockMvc.perform(get("/partidas/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("partidas/createPartidas"))
                .andExpect(model().attributeExists("partida"));
    }

    @WithMockUser
    @Test
    public void initCreationFormTestConPartidaEnCola() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_COLA);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);
        when(partidaService.partidasByPlayer(any(Integer.class))).thenReturn(partidas);

        mockMvc.perform(get("/partidas/create"))
                .andExpect(redirectedUrl("/partidas/10"));
    }

    @WithMockUser
    @Test
    public void initCreationFormTestConPartidaActual() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_CURSO);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);
        when(partidaService.partidasByPlayer(any(Integer.class))).thenReturn(partidas);

        mockMvc.perform(get("/partidas/create"))
                .andExpect(redirectedUrl("/partidas/10/tablero"));
    }

    @WithMockUser
    @Test
    public void listPartidasTest() throws Exception{
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_COLA);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);

        when(partidaService.findAllPartidas()).thenReturn(partidas);

        mockMvc.perform(get("/partidas/join2"))
                .andExpect(status().isOk())
                .andExpect(view().name("partidas/listPartidas"))
                .andExpect(model().attributeExists("partidas"));
    }

    @WithMockUser
    @Test
    public void unirseTestEnCola() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_COLA);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);
        when(partidaService.partidasByPlayer(any(Integer.class))).thenReturn(partidas);

        mockMvc.perform(get("/partidas/join"))
                .andExpect(redirectedUrl("/partidas/10"));
    }

    @WithMockUser
    @Test
    public void unirseTestEnCurso() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_CURSO);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);
        when(partidaService.partidasByPlayer(any(Integer.class))).thenReturn(partidas);

        mockMvc.perform(get("/partidas/join"))
                .andExpect(redirectedUrl("/partidas/10/tablero"));
    }

    @WithMockUser
    @Test
    public void unirseTestEnFinalizada() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.FINALIZADA);
        List<Partida> partidas = new ArrayList<>();
        partidas.add(partida);
        when(partidaService.partidasByPlayer(any(Integer.class))).thenReturn(partidas);

        mockMvc.perform(get("/partidas/join"))
                .andExpect(redirectedUrl("/partidas/join2"));
    }

    @WithMockUser
    @Test
    public void iniciarPartidaTestCorrecto() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_COLA);
        partida.setCreador(jugador);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        partida.setJugadores(jugadores);
        when(partidaService.numeroJugadoresCorrecto(any(Partida.class))).thenReturn(true);
        when(partidaService.findById(any(Integer.class))).thenReturn(Optional.of(partida));

        List<Carta> cartas = new ArrayList<>();
        when(cartaService.cartasAleatorias(any(Partida.class))).thenReturn(cartas);

        mockMvc.perform(get("/partidas/10/inicio"))
                .andExpect(redirectedUrl("/partidas/10/tablero"));
    }

    @WithMockUser
    @Test
    public void iniciarPartidaTestIncorrecto() throws Exception{
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        Partida partida = new Partida();
        partida.setId(10);
        partida.setEstado(EstadoPartida.EN_COLA);
        partida.setCreador(jugador);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        partida.setJugadores(jugadores);
        when(partidaService.numeroJugadoresCorrecto(any(Partida.class))).thenReturn(false);
        when(partidaService.findById(any(Integer.class))).thenReturn(Optional.of(partida));

        List<Carta> cartas = new ArrayList<>();
        when(cartaService.cartasAleatorias(any(Partida.class))).thenReturn(cartas);

        mockMvc.perform(get("/partidas/10/inicio"))
                .andExpect(redirectedUrl("/partidas/10"));
    }

    @WithMockUser
    @Test
    public void showTableroTest() throws Exception{
        when(partidaService.partidaFinalizada(any(Integer.class))).thenReturn(true);
        mockMvc.perform(get("/partidas/10/tablero"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void showTableroTestTablero() throws Exception{
        when(partidaService.partidaFinalizada(any(Integer.class))).thenReturn(false);
        Partida partida = new Partida();
        Tablero tablero = new Tablero();
        Carta carta = new Carta();
        List<Carta> cartas = new ArrayList<>();
        cartas.add(carta);
        when(partidaService.findById(any(Integer.class))).thenReturn(Optional.of(partida));
        when(tableroService.findById(any(Integer.class))).thenReturn(Optional.of(tablero));
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        when(cartaService.findByJugador(any(Integer.class))).thenReturn(cartas);
        mockMvc.perform(get("/partidas/10/tablero"))
                .andExpect(status().isOk())
                .andExpect(view().name("partidas/Tablero"))
                .andExpect(model().attributeExists("partida"))
                .andExpect(model().attributeExists("cartasJugador"));
                
    }

    @WithMockUser
    @Test
    public void cogerCartaTest() throws Exception{
        Partida partida = new Partida();
        partida.setId(10);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugador);
        partida.setJugadores(jugadores);
        Carta carta = new Carta();
        carta.setId(100);
        carta.setPosicion(1);

        Carta carta1 = new Carta();
        carta1.setId(101);
        Carta carta2 = new Carta();
        carta2.setId(102);
        carta2.setPosicion(7);
        Carta carta3 = new Carta();
        carta3.setId(103);
        Carta carta4 = new Carta();
        carta4.setId(104);
        Carta carta5 = new Carta();
        carta5.setId(105);
        Carta carta6 = new Carta();
        carta6.setId(106);
        Carta carta7 = new Carta();
        carta7.setId(107);

        List<Carta> cartas = new ArrayList<>();
        cartas.add(carta1);
        cartas.add(carta2);
        cartas.add(carta3);
        cartas.add(carta4);
        cartas.add(carta5);
        cartas.add(carta6);
        cartas.add(carta7);
        partida.setCartas(cartas);


        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        when(partidaService.findById(any(Integer.class))).thenReturn(Optional.of(partida));
        when(cartaService.findById(any(Integer.class))).thenReturn(Optional.of(carta));
        when(partidaService.partidaFinalizada(any(Integer.class))).thenReturn(false);
        mockMvc.perform(get("/partidas/10/tablero/100"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void viajarAIslaTest() throws Exception{
        Partida partida = new Partida();
        partida.setId(10);
        partida.setDadoTirado(true);
        partida.setJugadorActual(jugador);

        Carta carta = new Carta();
        carta.setId(100);
        carta.setTipoCarta(TipoCarta.DOBLON);
        carta.setPosicion(1);
        List<Carta> cartas = new ArrayList<>();
        cartas.add(carta);

        when(partidaService.doblonesSuficientes(any(Integer.class) ,any(Integer.class),any(Integer.class))).thenReturn(true);
        when(usuarioService.findUserByNombreUsuario(any(String.class))).thenReturn(Optional.of(user));
        when(jugadorService.findByUsuario(any(Usuario.class))).thenReturn(jugador);
        when(partidaService.findById(any(Integer.class))).thenReturn(Optional.of(partida));
        when(cartaService.findByJugador(any(Integer.class))).thenReturn(cartas);
        when(cartaService.findByPosicion(any(Integer.class))).thenReturn(Optional.of(carta));
    
        mockMvc.perform(get("/partidas/10/tablero/viajar"))
                .andExpect(status().isOk());
    
    }
}

