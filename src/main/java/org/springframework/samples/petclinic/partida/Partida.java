package org.springframework.samples.petclinic.partida;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.AuditableEntity;
import lombok.Getter;
import lombok.Setter; 

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name = "partidas")
public class Partida extends AuditableEntity {
    
    @Size(min = 3, max = 50)
	@Column(name = "name")
	private String name;

    @Column(name = "fecha")
    private LocalDate fecha;
  
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    
    @Column(name = "estado")
    private EstadoPartida estado;

    @Column(name="duracion")
    private Integer duracion = 0;

    
    @NotEmpty
    @Column(name = "codigo")
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    private Jugador creador;

    @ManyToOne
    @JoinColumn(name = "jugador_actual_id")   
    private Jugador jugadorActual;

    @Column(name = "valor_dado")   
    private Integer valorDado = null;

    @Column(name = "dado_tirado")   
    private Boolean dadoTirado = false; 

    @ManyToMany
	@JoinTable(name = "partida_jugador", joinColumns = @JoinColumn(name = "partida_id"),
			inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private List<Jugador> jugadores;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    
	private List<Carta> cartas;

    @ManyToOne
    @JoinColumn(name = "ganador_id")
    private Jugador ganador;

    @Override
	public String toString() {
		return this.getName();
	}

}
