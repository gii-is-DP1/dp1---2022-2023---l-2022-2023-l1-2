package org.springframework.samples.petclinic.carta;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;


@Service
public class CartaService {
    private CartaRepository cartaRepository;

    @Autowired
	public CartaService(CartaRepository cartaRepository) {
		this.cartaRepository = cartaRepository;
	}

	public List<Carta> findAllCartas(){
		return cartaRepository.findAll();
	}

	public List<Carta> findByJugador(Integer jug){
		return cartaRepository.findByJugador(jug);
	}

	@Transactional
	public Carta save(Carta c){
		return cartaRepository.save(c);
	}
}