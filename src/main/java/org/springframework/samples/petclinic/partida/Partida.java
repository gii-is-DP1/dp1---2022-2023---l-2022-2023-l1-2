package org.springframework.samples.petclinic.partida;

import java.sql.Date;
import java.sql.Time;

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
@Table(name = "partidas")
public class Partida extends BaseEntity {
    
    @NotNull
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;

    @NotNull
    @Column(name = "estado")
    private EstadoPartida estado;
}
