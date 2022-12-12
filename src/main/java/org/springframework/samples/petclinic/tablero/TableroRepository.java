package org.springframework.samples.petclinic.tablero;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableroRepository extends CrudRepository<Tablero, Integer>{
    
    Optional<Tablero> findById(Integer id);
}
