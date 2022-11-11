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

@Setter
@Getter
@Entity
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

    
    public TipoCarta getTipo(){
        return this.tipoCarta;
    }

    public void setTipo(TipoCarta tipo){
        this.tipoCarta = tipo;
    }

    public EstadoCarta getEstado(){
        return this.estadoCarta;
    }

    public void setEstado(EstadoCarta estado){
        this.estadoCarta = estado;
    }

    public Integer getPosicion(){
        return this.posicion;
    }

    public void setPosicion(Integer posicion){
        this.posicion = posicion;
    }

    public Jugador getJugador(){
        return this.jugador;
    }

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }

}
