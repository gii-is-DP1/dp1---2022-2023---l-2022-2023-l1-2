package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PartidaRepositoryTest {
    
    @Autowired
    PartidaRepository partidaRepository;


    @Test
    public void testFindAll(){
        List<Partida> partidas = partidaRepository.findAll();
        assertNotNull(partidas);
        assertFalse(partidas.isEmpty());
    }

    @Test
    public void testFindByPlayer(){
        List<Partida> partidas = partidaRepository.findByPlayer(1);
        assertNotNull(partidas);
        assertEquals(2, partidas.size());
    }

    @Test
    public void testNumPartidas(){
        Integer numPartidas = partidaRepository.numPartidas();
        assertNotNull(numPartidas);
        assertEquals(2, numPartidas);
        
    }

    @Test
    public void testDuracionMaxima(){
        Integer durMaxima = partidaRepository.duracionMaxima();
        assertNotNull(durMaxima);
        assertEquals(38, durMaxima);
        
    }

    @Test
    public void testDuracionMinima(){
        Integer durMinima= partidaRepository.duracionMinima();
        assertNotNull(durMinima);
        assertEquals(26, durMinima);
        
    }

    @Test
    public void testDuracionMedia(){
        Double durMedia= partidaRepository.duracionMedia();
        assertNotNull(durMedia);
        assertEquals(32, durMedia);
        
    }

    @Test
    public void testMediaJugadores(){
        Double jugMedia= partidaRepository.mediaJugadores();
        assertNotNull(jugMedia);
        assertEquals(2.5, jugMedia);
        
    }

}
