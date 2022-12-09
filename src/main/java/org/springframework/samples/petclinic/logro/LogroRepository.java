package org.springframework.samples.petclinic.logro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Integer>{
    
    List<Logro> findAll();

    @Query("SELECT l from Logros l WHERE l.nombre = ?1")
    Optional<Logro> findByNombre(String nombreLogro);

    @Query("SELECT l from Logros l WHERE l.id = ?1")
    Optional<Logro> findById(Integer logroId);
}
