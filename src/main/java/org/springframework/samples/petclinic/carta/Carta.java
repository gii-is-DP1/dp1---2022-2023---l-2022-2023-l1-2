package org.springframework.samples.petclinic.carta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cartas")
public class Carta extends BaseEntity{

    @Column(name = "tipoCarta")
    @NotEmpty
    private TipoCarta tipoCarta;

    @Column(name = "estadoCarta")
    @NotEmpty
    private EstadoCarta estadoCarta;

    @Column(name = "posicion")
    @NotEmpty
    private Integer posicion;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;
}
