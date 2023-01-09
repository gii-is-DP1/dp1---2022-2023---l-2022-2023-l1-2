package org.springframework.samples.petclinic.carta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
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

	public Optional<Carta> findByPosicion(Integer pos){
		return cartaRepository.findByPosicion(pos);
	}

	public void retirarDoblones(List<Carta> doblones, Integer numDoblones){
        Integer i=0;
        while(i<numDoblones){
            Carta c = doblones.get(i);
            c.setJugador(null);
            save(c);
            i++;
        }
    }

	public List<Carta> cartasAleatorias(Partida p){
        List<Carta> cartas = p.getCartas();
        if(cartas.isEmpty() || cartas == null){
            cartas = findAllCartas();
            cartas.stream().forEach(c->c.setPosicion(7));

            List<Carta> doblones = cartas.stream().filter(x->x.getTipoCarta().equals(TipoCarta.DOBLON)).collect(Collectors.toList());
            List<Carta> utilizadas = new ArrayList<>();
            Integer x =0;

            //Se le añaden 3 doblones a cada jugador
            for(int j=0;j<p.getJugadores().size();j++){
                Jugador jug = p.getJugadores().get(j);
                for(int d = x; d<x+3;d++){
                    Carta c = doblones.get(d);
                    utilizadas.add(c);
                    c.setJugador(jug);
                    c.setPosicion(0);
                    save(c);
                }
                x=x+3;
            }

            //Se retiran los doblones asignados de las cartas disponibles
            cartas.removeAll(utilizadas);
            Collections.shuffle(cartas);
            cartas.stream().forEach(c->save(c));

            //Se reparten una carta a cada una de las 6 posiciones iniciales
            for(int i =0; i<6;i++){
                Carta carta = cartas.get(i);
                carta.setPosicion(i+1);
                save(carta);
            }
        }
        return cartas;
    }

	
}