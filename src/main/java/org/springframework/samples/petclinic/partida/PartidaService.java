package org.springframework.samples.petclinic.partida;

import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaRepository;
import org.springframework.samples.petclinic.carta.CartaService;
import org.springframework.samples.petclinic.carta.TipoCarta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.logro.LogroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaService {
	private PartidaRepository partidaRepository;
	private CartaRepository cartaRepository;
	private LogroRepository logroRepository;

	@Autowired
	public PartidaService(PartidaRepository partidaRepository, CartaRepository cartaRepository,
			LogroRepository logroRepository) {
		this.partidaRepository = partidaRepository;
		this.cartaRepository = cartaRepository;
		this.logroRepository = logroRepository;
	}

	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}

	public List<Partida> findAllPartidas() {
		return partidaRepository.findAll();
	}

	public Optional<Partida> findById(Integer id) {
		return partidaRepository.findById(id);
	}

	@Transactional
	public Partida save(Partida p) {
		return partidaRepository.save(p);
	}

	public List<Partida> partidasByPlayer(Integer id) {
		return partidaRepository.findByPlayer(id);
	}

	public Map<String, Integer> contarPuntos(Integer idPartida) {
		Map<String, Integer> map = new HashMap<>();
		Partida p = partidaRepository.findById(idPartida).get();
		for (Jugador j : p.getJugadores()) {
			Integer puntos = 0;
			List<Set<TipoCarta>> setsCartasJugador = new ArrayList<>();
			List<TipoCarta> cartasJugador = cartaRepository.findByJugador(j.getId()).stream().map(Carta::getTipoCarta)
					.collect(Collectors.toList());
			List<TipoCarta> doblones = cartasJugador.stream().filter(x -> x.equals(TipoCarta.DOBLON))
					.collect(Collectors.toList());
			cartasJugador.removeAll(doblones);
			puntos += doblones.size();
			while (!cartasJugador.isEmpty()) {
				Set<TipoCarta> set = new HashSet<>(cartasJugador);
				setsCartasJugador.add(set);
				for (TipoCarta t : set) {
					TipoCarta tipo = cartasJugador.stream().filter(x -> x.equals(t)).findFirst().get();
					cartasJugador.remove(tipo);
				}
			}
			for (Set<TipoCarta> s : setsCartasJugador) {
				switch (s.size()) {
					case 1:
						puntos += 1;
						break;
					case 2:
						puntos += 3;
						break;
					case 3:
						puntos += 7;
						break;
					case 4:
						puntos += 13;
						break;
					case 5:
						puntos += 21;
						break;
					case 6:
						puntos += 30;
						break;
					case 7:
						puntos += 40;
						break;
					case 8:
						puntos += 50;
						break;
					case 9:
						puntos += 60;
						break;
				}
			}
			map.put(j.getUsuario().getNombreUsuario(), puntos);
		}
		return map;
	}

	public Boolean numeroCorrecto(Partida p) {
		if (p.getJugadores().size() >= 2 && p.getJugadores().size() <= 4) {
			return true;
		} else {
			return false;
		}
	}

	public void comprobarLogrosPartidaFinalizada(Integer idPartida) {
		Partida p = partidaRepository.findById(idPartida).get();
		List<Logro> logros = logroRepository.findAll();
		for (Jugador j : p.getJugadores()) {
			for (Logro l : logros) {
				switch (l.getTipoLogro()) {
					case PARTIDAS:
						if (j.getPartidasJugadas() >= l.getObjetivo()) {
							l.getJugadores().add(j);
							logroRepository.save(l);
						}
						break;
					case PUNTOSPARTIDA:
						if (j.getRecordPuntos() >= l.getObjetivo()) {
							l.getJugadores().add(j);
							logroRepository.save(l);
						}
						break;
					case PUNTOSTOTALES:
						if (j.getTotalPuntos() >= l.getObjetivo()) {
							l.getJugadores().add(j);
							logroRepository.save(l);
						}
						break;
					case TIEMPO:
						if (Duration.between(p.getHoraInicio(), p.getHoraFin()).toMinutes() <= l.getObjetivo()) {
							l.getJugadores().add(j);
							logroRepository.save(l);
						}
						break;
					case VICTORIAS:
						if (j.getPartidasGanadas() >= l.getObjetivo()) {
							l.getJugadores().add(j);
							logroRepository.save(l);
						}
						break;
				}
			}
		}
	}

	public Boolean partidaFinalizada(Integer id) {
		Partida partida = partidaRepository.findById(id).get();
		return partida.getCartas().stream().allMatch(p -> p.getPosicion() == 0);
	}

	public void cambiarTurno(Partida p) {
		Integer n = p.getJugadores().size();
		List<Jugador> jugadores = p.getJugadores();
		Integer poscionActual = jugadores.indexOf(p.getJugadorActual());
		Integer posicionJugadorProximo = (poscionActual + 1) % n;

		Jugador jugadorProximo = jugadores.get(posicionJugadorProximo);
		p.setJugadorActual(jugadorProximo);
		p.setDadoTirado(false);
		save(p);
	}

	public Boolean doblonesSuficientes(Integer islaDestino, Integer islaActual, Integer doblones) {
		if (Math.abs(islaDestino - islaActual) <= doblones) {
			return true;
		} else {
			return false;
		}
	}

}