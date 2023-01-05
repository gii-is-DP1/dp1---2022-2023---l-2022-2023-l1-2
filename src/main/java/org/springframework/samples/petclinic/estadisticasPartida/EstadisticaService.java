package org.springframework.samples.petclinic.estadisticasPartida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadisticaService {
    private EstadisticaRepository estadisticaRepository;

    @Autowired
	public EstadisticaService(EstadisticaRepository estadisticaRepository) {
		this.estadisticaRepository = estadisticaRepository;
	}

	@Transactional
	public EstadísticaJugadorEnPartida save(EstadísticaJugadorEnPartida e) {
		return estadisticaRepository.save(e);
	}

	@Transactional
	public void aumentarBarcosUsados(Jugador j, Partida p){
		
	}
}
