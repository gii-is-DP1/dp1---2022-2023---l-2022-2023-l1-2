package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;
    private static final String PARTIDA_CREATE = "partidas/createPartidas";

    @Autowired
	public PartidaController(PartidaService partidaService) {
		this.partidaService = partidaService;
	}


    @GetMapping(value = "/create")
 	public String initCreationForm(Map<String, Object> model) {
 		Partida partida = new Partida();
        partida.setFecha(LocalDate.now());
        partida.setHoraInicio(LocalTime.now());
        partida.setEstado(EstadoPartida.EN_COLA);
 		model.put("partida", partida);
 		return PARTIDA_CREATE;
 	}

 	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Partida partida, BindingResult result) {
 		if (result.hasErrors()) {
 			return PARTIDA_CREATE;
 		}
 		else {
 			//creating owner, user and authorities
             this.partidaService.save(partida);
			
			return "redirect:/partidas/" + partida.getId();
 		}
 	}
/* 
    @PostMapping("/create")
    public String processCreationPartida(ModelMap model){
        Partida partida = new Partida();
        partida.setFecha(LocalDate.now());
        partida.setHoraInicio(LocalTime.now());
        partida.setEstado(EstadoPartida.EN_COLA);
        String cod = RandomString.make(6);
        partida.setCodigo(cod);
        model.put("partida", partida);
        this.partidaService.save(partida);
        return "redirect:/{partidaId}";
    
    }
    */

    @GetMapping("/{partidaId}")
 	public ModelAndView showOwner(@PathVariable("partidaId") int partidaId) {
 		ModelAndView mav = new ModelAndView("partidas/showPartida");
 		mav.addObject(this.partidaService.findById(partidaId));
 		return mav;
 	}
}
