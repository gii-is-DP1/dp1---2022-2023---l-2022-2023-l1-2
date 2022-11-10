package org.springframework.samples.petclinic.carta;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


public interface CartaRepository extends Repository<Carta, Integer>{

    List<Carta> findAll();

    @Query("SELECT carta FROM Cartas WHERE carta.tipo = :tipo%")
    Carta findByTipo(@Param("tipo")String tipo);


}
