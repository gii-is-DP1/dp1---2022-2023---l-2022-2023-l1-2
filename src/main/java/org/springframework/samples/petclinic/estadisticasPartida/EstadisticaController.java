package org.springframework.samples.petclinic.estadisticasPartida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticaController {
    private final EstadisticaService estadisticaService;

    @Autowired
	public EstadisticaController(EstadisticaService estadisticaService) {
		this.estadisticaService = estadisticaService;
	}
}
