package org.springframework.samples.petclinic.carta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CartaRepositoryTest {
    
    @Autowired
    CartaRepository cartaRepository;

    @Test
    public void testFindAll(){
        List<Carta> cartas = cartaRepository.findAll();
        assertNotNull(cartas);
        assertFalse(cartas.isEmpty());
    }

    @Test
    public void testFindByJugador(){
        List<Carta> cartas = cartaRepository.findByJugador(1);
        assertNotNull(cartas);
        assertEquals(0, cartas.size());
    }

    @Test
    public void testFindByPosucionCorrecto(){
        Optional<Carta> carta = cartaRepository.findByPosicion(1);
        assertTrue(carta.isPresent());
    }

    @Test
    public void testFindByPosucionIncorrecto(){
        Optional<Carta> carta = cartaRepository.findByPosicion(3);
        assertFalse(carta.isPresent());
    }
}