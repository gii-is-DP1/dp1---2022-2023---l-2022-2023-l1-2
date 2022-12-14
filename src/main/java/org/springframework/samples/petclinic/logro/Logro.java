package org.springframework.samples.petclinic.logro;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "logros")
public class Logro extends BaseEntity {
	
	@NotBlank
	@Column(name = "nombre_logro")
	private String nombreLogro;

	@NotBlank
	@Column(name = "descripcion")
	private String descripcion;

	@Min(0)
	@Column(name = "objetivo")
	private Integer objetivo;
	
	@NotEmpty
	@Column(name = "tipo_logro")
	private TipoLogro tipoLogro;

	@ManyToMany
	@JoinTable(name = "logro_jugador", joinColumns = @JoinColumn(name = "logro_id"),
			inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private Set<Jugador> jugadores;

}
