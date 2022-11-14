package org.springframework.samples.petclinic.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoridadRepository extends CrudRepository<Autoridad,Integer>{
    
    
}
