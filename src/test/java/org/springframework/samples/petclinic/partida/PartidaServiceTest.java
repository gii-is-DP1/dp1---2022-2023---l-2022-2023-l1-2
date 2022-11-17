package org.springframework.samples.petclinic.partida;

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

@ExtendWith(MockitoExtension.class)
public class PartidaServiceTest {
    @Mock
    PartidaRepository repo;

    Partida part;
    Optional<Partida> Opar;
    PartidaService partidaService;

    @BeforeEach
    public void config(){
        part = new Partida();
        part.setCodigo("qwerty");
        partidaService = new PartidaService(repo);
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

}
