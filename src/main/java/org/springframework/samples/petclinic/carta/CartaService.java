package org.springframework.samples.petclinic.carta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

	public Optional<Carta> findById(Integer id){
		return cartaRepository.findById(id);
	} 
}