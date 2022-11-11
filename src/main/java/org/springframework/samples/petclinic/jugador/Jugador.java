package org.springframework.samples.petclinic.jugador;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.user.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugadores")
public class Jugador extends Usuario {
    @Column(name = "partidas_jugadas")
    @Min(0)
    Integer partidasJugadas;
    @Column(name = "partidas_ganadas")
    @Min(0)
    Integer partidasGanadas;
    @Column(name = "total_puntos")
    @Min(0)
    Integer totalPuntos;
    @Column(name = "record_puntos")
    @Min(0)
    Integer recordPuntos;
}
