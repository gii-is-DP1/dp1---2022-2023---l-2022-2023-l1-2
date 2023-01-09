package org.springframework.samples.petclinic.logro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogroRepository extends CrudRepository<Logro, Integer>{
    
    List<Logro> findAll();

    @Query("SELECT l FROM Logro l INNER JOIN l.jugadores as j WHERE j.id = ?1")
    Optional<List<Logro>> findLogrosByPlayer(Integer id);

    @Query("SELECT l FROM Logro l INNER JOIN l.jugadores as j WHERE j.id = ?1")
    Optional<Page<Logro>> findLogrosByPlayerPage(Integer id, Pageable page);

    @Query("SELECT l FROM Logro l")
    Page<Logro> findAllLogrosPage(Pageable Page);
}
