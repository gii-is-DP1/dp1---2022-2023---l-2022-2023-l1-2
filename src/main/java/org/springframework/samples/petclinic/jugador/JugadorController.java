package org.springframework.samples.petclinic.jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.usuario.AutoridadService;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class JugadorController {

    private static final String VIEWS_PLAYER_CREATE_OR_UPDATE = "";

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService, UsuarioService uService, AutoridadService admnistradorService){
        this.jugadorService=jugadorService; 
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    
}
