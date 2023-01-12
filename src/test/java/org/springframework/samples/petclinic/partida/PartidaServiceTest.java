package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaRepository;
import org.springframework.samples.petclinic.carta.CartaService;
import org.springframework.samples.petclinic.carta.TipoCarta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.usuario.Usuario;

@ExtendWith(MockitoExtension.class)
public class PartidaServiceTest {
    @Mock
    PartidaRepository repo;

    @Mock
    CartaRepository repoCarta;

    Partida part;
    Partida part2;
    Optional<Partida> Opar;
    PartidaService partidaService;
    JugadorService jugadorService;

    @BeforeEach
    public void config(){
        part = new Partida();
        part.setCodigo("qwerty");
        List<Jugador> jugadores = new ArrayList<>();
        Jugador jug1 = new Jugador();
        Usuario user1 = new Usuario();
        user1.setNombreUsuario("Pepe");
        jug1.setUsuario(user1);
        jug1.setId(1);
        Jugador jug2 = new Jugador();
        Usuario user2 = new Usuario();
        user2.setNombreUsuario("Juan");
        jug2.setUsuario(user2);
        jug2.setId(2);
        jugadores.add(jug1);
        jugadores.add(jug2);
        List<Carta> cartas = new ArrayList<>();
        Carta carta = new Carta();
        carta.setTipoCarta(TipoCarta.BOTELLA);
        carta.setPosicion(0);
        carta.setId(1);
        cartas.add(carta);

        Carta carta2 = new Carta();
        carta2.setTipoCarta(TipoCarta.CORONA);
        carta2.setPosicion(0);
        carta2.setId(2);
        cartas.add(carta2);

        Carta carta3 = new Carta();
        carta3.setTipoCarta(TipoCarta.DOBLON);
        carta3.setPosicion(0);
        carta3.setId(3);
        cartas.add(carta3);

        part.setJugadores(jugadores);
        part.setCartas(cartas);

        part2 = new Partida();
        List<Jugador> jugadores2 = new ArrayList<>();
        Jugador jug3 = new Jugador();
        jugadores2.add(jug3);
        part2.setJugadores(jugadores2);
        List<Carta> cartas2 = new ArrayList<>();
        Carta carta4 = new Carta();
        carta4.setPosicion(1);
        cartas2.add(carta4);
        part2.setCartas(cartas2);


        partidaService = new PartidaService(repo, repoCarta);
    }

    @Test
    public void findAllTest(){
        List<Partida> partidas = new ArrayList<>();
        partidas.add(part);
        when(repo.findAll()).thenReturn(partidas);
    
        List<Partida> partidasNew = new ArrayList<>();
        partidasNew.add(part);
        List<Partida> partidas2 = partidaService.findAllPartidas();
        assertNotNull(partidas2);
        assertEquals(partidasNew, partidas2);
    }

    @Test
    public void findByIdTest(){
        Opar = Optional.of(part);
        when(repo.findById(1)).thenReturn(Opar);
        Partida p = partidaService.findById(1).get();

        assertNotNull(p);
        assertEquals(part, p);
        
    }

    @Test
    public void partidasByPlayerTest(){
        List<Partida> partidas = new ArrayList<>();
        partidas.add(part);
        when(repo.findByPlayer(any(Integer.class))).thenReturn(partidas);
        List<Partida> partidasNew = partidaService.partidasByPlayer(1);

        assertNotNull(partidasNew);
        assertEquals(partidasNew, partidas);
        
    }

    @Test
    public void numeroJugadoresCorrectoTestCorrecto(){
        Boolean numCorrecto = partidaService.numeroJugadoresCorrecto(part);
        assertTrue(numCorrecto);
    }

    @Test
    public void numeroJugadoresCorrectoTestIncorrecto(){
        Boolean numCorrecto = partidaService.numeroJugadoresCorrecto(part2);
        assertFalse(numCorrecto);
    }

    @Test
    public void partidaFinalizadaTestCorrecto(){
        Optional oPart = Optional.of(part);
        when(repo.findById(any(Integer.class))).thenReturn(oPart);
        Boolean partidaFinalizada = partidaService.partidaFinalizada(1);
        assertTrue(partidaFinalizada);
    }

    @Test
    public void partidaFinalizadaTestIncorrecto(){
        Optional oPart = Optional.of(part2);
        when(repo.findById(any(Integer.class))).thenReturn(oPart);
        Boolean partidaFinalizada = partidaService.partidaFinalizada(1);
        assertFalse(partidaFinalizada);
    }

    @Test
    public void doblonesSuficientesTestCorrecto(){
        Boolean doblonesSuficientes = partidaService.doblonesSuficientes(4, 2, 4);
        assertTrue(doblonesSuficientes);
    }

    @Test
    public void doblonesSuficientesTestIncorrecto(){
        Boolean doblonesSuficientes = partidaService.doblonesSuficientes(4, 2, 1);
        assertFalse(doblonesSuficientes);
    }

    @Test
    public void numPartidasTest(){
        when(repo.numPartidas()).thenReturn(4);
        Integer numPartidas = partidaService.numPartidas();
        assertEquals(4, numPartidas);
    }

    @Test
    public void duracionMaximaTest(){
        when(repo.duracionMaxima()).thenReturn(36);
        Integer durMax = partidaService.duracionMaxima();
        assertEquals(36,durMax);
    }
    @Test
    public void duracionMinimaTest(){
        when(repo.duracionMinima()).thenReturn(10);
        Integer durMin = partidaService.duracionMinima();
        assertEquals(10,durMin);
    }

    @Test
    public void duracionMediaTest(){
        when(repo.duracionMedia()).thenReturn(12.5);
        Double durMedia = partidaService.duracionMedia();
        assertEquals(12.5,durMedia);
    }

    @Test
    public void mediaJugadoresTest(){
        when(repo.mediaJugadores()).thenReturn(2.5);
        Double jugadoresMedia = partidaService.mediaJugadores();
        assertEquals(2.5,jugadoresMedia);
    }

    @Test
    public void contarPuntosTest(){
        Optional<Partida> oPart = Optional.of(part);
        List<Carta> listaCartas = part.getCartas();
        when(repoCarta.findByJugador(any(Integer.class))).thenReturn(listaCartas);
        when(repo.findById(any(Integer.class))).thenReturn(oPart);
        Map<String, Integer> mapPuntos = partidaService.contarPuntos(1);
        assertEquals(4, mapPuntos.get("Pepe"));
        assertEquals(4, mapPuntos.get("Juan"));
    }


}
