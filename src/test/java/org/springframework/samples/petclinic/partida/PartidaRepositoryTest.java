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

}
