package org.springframework.samples.petclinic.carta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;

@ExtendWith(MockitoExtension.class)
public class CartaServiceTest {
    
    @Mock
    CartaRepository repo;

    Carta carta;
    List<Carta> cartas;
    Jugador jug;
    Optional<Carta> optCarta;
    Partida partida;
    
    CartaService cartaService;
    @BeforeEach
    public void config(){
        jug = new Jugador();
        cartaService = new CartaService(repo);
        carta = new Carta();
        carta.setJugador(jug);
        carta.setTipoCarta(TipoCarta.DOBLON);
        cartas = new ArrayList<>();
        cartas.add(carta);

        optCarta = Optional.of(carta);
    }

    @Test
    public void findAllCartasTest(){
        when(repo.findAll()).thenReturn(cartas);
        List<Carta> cartasNew = cartaService.findAllCartas();
        assertNotNull(cartasNew);
        assertEquals(cartas, cartasNew);
    }

    @Test
    public void findByJugadorTest(){
        when(repo.findByJugador(any(Integer.class))).thenReturn(cartas);
        List<Carta> cartasNew = cartaService.findByJugador(1);
        assertNotNull(cartasNew);
        assertEquals(cartas, cartasNew);
    }

    @Test
    public void saveTest(){
        cartaService.save(carta);
        Mockito.verify(repo).save(carta);
    }

    @Test
    public void findByIdTest(){
        when(repo.findById(any(Integer.class))).thenReturn(optCarta);
        Optional<Carta> cartaNew = cartaService.findById(1);
        assertNotNull(cartaNew);
        assertEquals(optCarta, cartaNew);
    }

    @Test
    public void findByPosicionTest(){
        when(repo.findByPosicion(any(Integer.class))).thenReturn(optCarta);
        Optional<Carta> cartaNew = cartaService.findByPosicion(1);
        assertNotNull(cartaNew);
        assertEquals(optCarta, cartaNew);
    }

    @Test
    public void retirarDoblonesTest(){
        Carta carta2 = new Carta();
        Carta carta3 = new Carta();
        cartas.add(carta2);
        cartas.add(carta3);
        cartaService.retirarDoblones(cartas, 2);
        Mockito.verify(repo).save(carta);
    }

    public void configCartasAleatorias(){
        Carta carta2 = new Carta();
        carta2.setTipoCarta(TipoCarta.DOBLON);
        Carta carta3 = new Carta();
        carta3.setTipoCarta(TipoCarta.DOBLON); 
        Carta carta4 = new Carta();
        carta4.setTipoCarta(TipoCarta.COPA);
        Carta carta5 = new Carta();
        carta5.setTipoCarta(TipoCarta.BARRIL);
        Carta carta6 = new Carta();
        carta6.setTipoCarta(TipoCarta.ESPADA);
        Carta carta7 = new Carta();
        carta7.setTipoCarta(TipoCarta.RUBI);
        Carta carta8 = new Carta();
        carta8.setTipoCarta(TipoCarta.DIAMANTE);
        Carta carta9 = new Carta();
        carta9.setTipoCarta(TipoCarta.PISTOLA);

        cartas.add(carta2);
        cartas.add(carta3);
        cartas.add(carta4);
        cartas.add(carta5);
        cartas.add(carta6);
        cartas.add(carta7);
        cartas.add(carta8);
        cartas.add(carta9);

        partida = new Partida();
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jug);
        partida.setJugadores(jugadores);
        List<Carta> cartasVacia = new ArrayList<>();
        partida.setCartas(cartasVacia);

    }

    @Test
    public void cartasAleatoriasTestCartasNoRepartidas(){
        configCartasAleatorias();
        when(repo.findAll()).thenReturn(cartas);

        List<Carta> cartasAleatorias =cartaService.cartasAleatorias(partida);
        Mockito.verify(repo).save(carta);
        assertEquals(6, cartasAleatorias.size());

    }

    @Test
    public void cartasAleatoriasTestCartasYaRepartidas(){
        partida = new Partida();
        List<Carta> cartasPartida = new ArrayList<>();
        cartasPartida.add(carta);
        partida.setCartas(cartasPartida);
        List<Carta> cartasAleatorias =cartaService.cartasAleatorias(partida);
        Mockito.verifyNoMoreInteractions(repo);
        assertEquals(1, cartasAleatorias.size());

    }
}
