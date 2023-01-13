
package org.springframework.samples.petclinic.partida;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.carta.CartaService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.estadisticasPartida.EstadisticaService;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.JugadorService;

import org.springframework.samples.petclinic.tablero.TableroService;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Optional;


@WebMvcTest(controllers=PartidaController.class,
    excludeFilters=@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, classes=WebSecurityConfigurer.class),
    excludeAutoConfiguration=SecurityConfiguration.class)
public class PartidaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartidaService partidaService;

    @MockBean
    private JugadorService jugadorService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private TableroService tableroService;

    @MockBean
    private EstadisticaService estadisticaService;

    @MockBean
    private CartaService cartaService;

    

    /*
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
    */
    
    
}


