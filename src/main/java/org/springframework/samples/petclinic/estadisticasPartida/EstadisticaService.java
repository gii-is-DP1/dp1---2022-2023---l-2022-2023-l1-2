package org.springframework.samples.petclinic.estadisticasPartida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadisticaService {
    private EstadisticaRepository estadisticaRepository;

    @Autowired
	public EstadisticaService(EstadisticaRepository estadisticaRepository) {
		this.estadisticaRepository = estadisticaRepository;
	}
}
