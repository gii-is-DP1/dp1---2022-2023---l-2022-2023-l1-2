package org.springframework.samples.petclinic.carta;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends CrudRepository<Carta, Integer>{
    @Query("SELECT cart FROM Carta cart")
    List<Carta> findAll();

    @Query("SELECT cart FROM Carta cart WHERE cart.jugador.id = ?1")
    List<Carta> findByJugador(Integer jug);

    Carta save(Carta p);

    @Query("SELECT cart FROM Carta cart WHERE cart.posicion = ?1")
    Optional<Carta> findByPosicion(Integer pos);
   
}
