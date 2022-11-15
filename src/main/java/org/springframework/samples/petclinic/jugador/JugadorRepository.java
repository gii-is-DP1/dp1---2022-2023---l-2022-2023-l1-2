package org.springframework.samples.petclinic.jugador;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Integer>{
    
    List<Jugador> findAll();

    Jugador save (Jugador jugador)  throws DataAccessException;


    Jugador findByUsuario(Usuario usuario);

}
