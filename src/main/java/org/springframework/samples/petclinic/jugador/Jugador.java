package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.samples.petclinic.logro.Logro;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.usuario.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugadores")
public class Jugador extends BaseEntity {
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

    //Relación con usuario
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario", referencedColumnName = "nombre_usuario")
    private Usuario usuario;


    //Relación con logros
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "logro_jugador", joinColumns = @JoinColumn(name = "jugador_id"),
			inverseJoinColumns = @JoinColumn(name = "logro_id"))
	private Set<Logro> logros;

    //Relación con Partida
    @ManyToMany(mappedBy = "jugadores",cascade = CascadeType.ALL)
    private Collection<Partida> partidas;

}

