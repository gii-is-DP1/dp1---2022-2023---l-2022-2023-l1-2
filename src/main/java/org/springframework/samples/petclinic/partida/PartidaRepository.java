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


}
