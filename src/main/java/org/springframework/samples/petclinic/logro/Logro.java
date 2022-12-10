package org.springframework.samples.petclinic.logro;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "logro")
public class Logro extends NamedEntity {
	
	

	@Column(name = "tipoLogro")
	private TipoLogro tipoLogro;

	@ManyToOne
	@JoinColumn(name = "jugador_id")
	private Jugador jugador;

}
