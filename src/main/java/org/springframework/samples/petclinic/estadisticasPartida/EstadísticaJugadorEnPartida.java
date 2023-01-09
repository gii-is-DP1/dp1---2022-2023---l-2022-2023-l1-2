package org.springframework.samples.petclinic.estadisticasPartida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estadisticas")
public class EstadísticaJugadorEnPartida extends BaseEntity{
    
    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;
    
    @NotNull
    @Column(name = "posicion")
    public Integer posicion;

    @NotNull
    @Column(name = "puntos_obtenidos")
    public Integer puntosObtenidos;

    @Column(name = "barcos_usados")
    public Integer numBarcosUsados;
    @Column(name = "cartas_obtenidas")
    public Integer numCartasObtenidas;
    
}
