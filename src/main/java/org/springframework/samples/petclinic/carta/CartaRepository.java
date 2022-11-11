package org.springframework.samples.petclinic.carta;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends CrudRepository<Carta, Integer>{
    
   
}
