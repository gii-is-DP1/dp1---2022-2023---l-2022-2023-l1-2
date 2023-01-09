package org.springframework.samples.petclinic.estadisticasPartida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticaController {
    private final EstadisticaService estadisticaService;
	private final PartidaService partidaService;
	private static final String GENERALES = "estadisticas/generales";

    @Autowired
	public EstadisticaController(EstadisticaService estadisticaService, PartidaService partidaService) {
		this.estadisticaService = estadisticaService;
		this.partidaService = partidaService;
	}


	@GetMapping("/generales")
	public ModelAndView estadisticasGenerales(){
		ModelAndView mav = new ModelAndView(GENERALES);
		Integer numPartidas = partidaService.numPartidas();
		Integer duracionMaxima = partidaService.duracionMaxima();
		Integer duracionMinima = partidaService.duracionMinima();
		Double duracionMedia = partidaService.duracionMedia();
		Double mediaJugadores = partidaService.mediaJugadores();
		mav.addObject("numPartidas", numPartidas);
		mav.addObject("duracionMaxima", duracionMaxima);
		mav.addObject("duracionMinima", duracionMinima);
		mav.addObject("duracionMedia", duracionMedia);
		mav.addObject("mediaJugadores", mediaJugadores);

		return mav;

	}

}
