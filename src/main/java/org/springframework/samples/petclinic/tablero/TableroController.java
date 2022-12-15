package org.springframework.samples.petclinic.tablero;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.JugadorService;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tableros")
public class TableroController {

    @Autowired
    private TableroService tableroService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private JugadorService jugadorService;


    private static final String ERROR = "/error";
    
    
  /*  @GetMapping(path = "/{partidaId}/cambiarTurno")
    public String cambiarTurno(@PathVariable("partidaId") int partidaId, ModelMap modelMap) {

        Optional<Partida> opt = partidaService.findById(partidaId).stream().findFirst();
        if(opt.isPresent()) {

            return tableroService.cambiarTurno(opt.get());
        
        } else {
            return ERROR;
        }

    } */
    
}
