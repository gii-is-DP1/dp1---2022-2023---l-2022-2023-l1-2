package org.springframework.samples.petclinic.estadisticasPartida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public EstadísticaJugadorEnPartida findByJugadorAndPartida(Integer j, Integer p){
		return estadisticaRepository.findByJugadorAndPartida(j,p);
	}
	
	@Transactional
	public void aumentarBarcosUsados(Integer j, Integer p){
		EstadísticaJugadorEnPartida est = findByJugadorAndPartida(j, p);
		Integer barcosOld = est.getNumBarcosUsados();
		est.setNumBarcosUsados(barcosOld+1);
		save(est);
	}

	@Transactional
	public void aumentarCartasObtenidas(Integer j, Integer p){
		EstadísticaJugadorEnPartida est = findByJugadorAndPartida(j, p);
		Integer cartasOld = est.getNumCartasObtenidas();
		est.setNumCartasObtenidas(cartasOld+1);
		save(est);
	}

	@Transactional
	public void crearEstadisticas(Jugador j, Partida p){
		EstadísticaJugadorEnPartida est = new EstadísticaJugadorEnPartida();
		est.setJugador(j);
		est.setPartida(p);
		est.setNumBarcosUsados(0);
		est.setNumCartasObtenidas(0);
		est.setPuntosObtenidos(0);
		est.setPosicion(0);
		save(est);
	}

	@Transactional
	public void establecerPosicionYPuntos(Integer j, Integer p, Integer posicion, Integer puntos){
		EstadísticaJugadorEnPartida est = findByJugadorAndPartida(j, p);
		est.setPosicion(posicion);
		est.setPuntosObtenidos(puntos);
		save(est);
	}

	public List<EstadísticaJugadorEnPartida> findByJugador(Integer j){
		return estadisticaRepository.findByJugador(j);
	}

	public Integer numBarcosTotalesByJugador(Integer j){
		return estadisticaRepository.findNumBarcosUsadosTotalesByJugador(j);
	}

	public Integer numCartasObtenidasByJugador(Integer j){
		return estadisticaRepository.findNumCartasObtenidasTotalesByJugador(j);
	}

	@Transactional
	public void delete(EstadísticaJugadorEnPartida est)throws DataAccessException{
		estadisticaRepository.delete(est);
	}
}
