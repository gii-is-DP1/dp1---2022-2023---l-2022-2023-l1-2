package org.springframework.samples.petclinic.jugador;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.model.AuditableEntity;
import org.springframework.samples.petclinic.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "jugadores")
public class Jugador extends AuditableEntity {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombre_usuario")
    private Usuario usuario;
}
