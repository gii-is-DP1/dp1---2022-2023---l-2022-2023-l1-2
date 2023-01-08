package org.springframework.samples.petclinic.estadisticasPartida;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticaRepository extends CrudRepository<EstadísticaJugadorEnPartida, Integer>{
    EstadísticaJugadorEnPartida save(EstadísticaJugadorEnPartida e);

    @Query("SELECT est FROM EstadísticaJugadorEnPartida est WHERE est.jugador.id = ?1 and est.partida.id = ?2")
    EstadísticaJugadorEnPartida findByJugadorAndPartida(Integer j, Integer p);

    @Query("SELECT est FROM EstadísticaJugadorEnPartida est WHERE est.jugador.id = ?1 and est.partida.estado = 0")
    List<EstadísticaJugadorEnPartida> findByJugador(Integer j);

    @Query("SELECT sum(est.numBarcosUsados) FROM EstadísticaJugadorEnPartida est WHERE est.jugador.id = ?1 and est.partida.estado = 0")
    Integer findNumBarcosUsadosTotalesByJugador(Integer j);

    @Query("SELECT sum(est.numCartasObtenidas) FROM EstadísticaJugadorEnPartida est WHERE est.jugador.id = ?1 and est.partida.estado = 0")
    Integer findNumCartasObtenidasTotalesByJugador(Integer j);
}
