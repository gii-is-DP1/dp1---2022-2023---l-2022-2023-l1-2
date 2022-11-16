package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partidas")
public class Partida extends BaseEntity {
    

    @Column(name = "fecha")
    private LocalDate fecha;
  
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    
    @Column(name = "estado")
    private EstadoPartida estado;

    
    @NotEmpty
    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Jugador creador;

    @ManyToMany
	@JoinTable(name = "partida_jugador", joinColumns = @JoinColumn(name = "partida_id"),
			inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private Set<Jugador> jugadores;



}
