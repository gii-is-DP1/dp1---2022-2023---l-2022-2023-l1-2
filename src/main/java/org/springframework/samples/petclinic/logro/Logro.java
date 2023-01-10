package org.springframework.samples.petclinic.logro;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.AuditableEntity;
import lombok.Getter;
import lombok.Setter;


@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "logros")
public class Logro extends AuditableEntity {
	
	@NotBlank
	@Column(name = "nombre_logro")
	private String nombreLogro;

	@NotBlank
	@Column(name = "descripcion")
	private String descripcion;

	@Min(0)
	@Column(name = "objetivo")
	private Integer objetivo;
	
	@Column(name = "tipo_logro")
	private TipoLogro tipoLogro;

	@ManyToMany
	@JoinTable(name = "logro_jugador", joinColumns = @JoinColumn(name = "logro_id"),
			inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private Set<Jugador> jugadores;

}
