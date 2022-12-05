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

    @Query("SELECT p from Partida p INNER JOIN FETCH p.jugadores as j WHERE j.id = ?1")
    List<Partida> findByPlayer(Integer id);


}
