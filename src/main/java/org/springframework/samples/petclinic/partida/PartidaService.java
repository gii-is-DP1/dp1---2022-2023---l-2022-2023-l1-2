package org.springframework.samples.petclinic.partida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.carta.CartaRepository;
import org.springframework.samples.petclinic.carta.TipoCarta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaService {
	private PartidaRepository partidaRepository;
	private CartaRepository cartaRepository;

	@Autowired
	public PartidaService(PartidaRepository partidaRepository, CartaRepository cartaRepository) {
		this.partidaRepository = partidaRepository;
		this.cartaRepository = cartaRepository;
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
					.toList();
			List<TipoCarta> doblones = cartasJugador.stream().filter(x -> x.equals(TipoCarta.DOBLON))
					.toList();
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
}