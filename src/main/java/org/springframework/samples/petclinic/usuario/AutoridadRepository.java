package org.springframework.samples.petclinic.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoridadRepository extends CrudRepository<Autoridad,Integer>{
    
}
