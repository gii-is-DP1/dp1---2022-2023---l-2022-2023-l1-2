package org.springframework.samples.petclinic.partida;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;
    private static final String PARTIDA_CREATE = "partidas/createPartidas";

    @Autowired
	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}

    @GetMapping("/create")
    public String initCreationForm(ModelMap model){
        Partida partida = new Partida();
        model.put("partida", partida);
        return PARTIDA_CREATE;
    }

    @PostMapping("/create")
    public String processCreationForm(@Valid Partida partida, BindingResult result){
        if(result.hasErrors()){
            return PARTIDA_CREATE;
        }else {
            this.partidaService.save(partida);
            return "redirect:/partidas/{partidaId}";
        }
    
    }
}
