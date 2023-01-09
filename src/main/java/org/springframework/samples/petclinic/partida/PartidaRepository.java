package org.springframework.samples.petclinic.partida;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PartidaRepository extends CrudRepository<Partida, Integer>{
    
    @Query("SELECT part FROM Partida part")
    List<Partida> findAll();

    Partida save(Partida p);

    @Query("SELECT p FROM Partida p INNER JOIN FETCH p.jugadores as j WHERE j.id = ?1")
    List<Partida> findByPlayer(Integer id);

    @Query("SELECT count(p) FROM Partida p WHERE p.estado = 0")
    Integer numPartidas();

    @Query("SELECT max(p.duracion) FROM Partida p WHERE p.estado = 0")
    Integer duracionMaxima();

    @Query("SELECT min(p.duracion) FROM Partida p WHERE p.estado = 0")
    Integer duracionMinima();

    @Query("SELECT avg(p.duracion) FROM Partida p WHERE p.estado = 0")
    Double duracionMedia();

    @Query("SELECT avg(p.jugadores.size) FROM Partida p WHERE p.estado = 0")
    Double mediaJugadores();



}
