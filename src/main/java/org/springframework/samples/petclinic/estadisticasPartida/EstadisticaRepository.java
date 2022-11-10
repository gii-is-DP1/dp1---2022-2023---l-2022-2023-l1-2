package org.springframework.samples.petclinic.estadisticasPartida;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaRepository extends CrudRepository<EstadísticaJugadorEnPartida, Integer>{
    
}
