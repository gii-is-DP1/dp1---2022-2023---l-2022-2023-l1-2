package org.springframework.samples.petclinic.estadisticasPartida;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estadisticas")
public class EstadísticaJugadorEnPartida extends BaseEntity{
    
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
