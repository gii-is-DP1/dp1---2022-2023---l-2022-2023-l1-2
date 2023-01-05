package org.springframework.samples.petclinic.logro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Integer>{
    
    List<Logro> findAll();

    @Query("SELECT p from Logro p INNER JOIN FETCH p.jugadores as j WHERE j.id = ?1")
    List<Logro> findByPlayer(Integer id);

}
