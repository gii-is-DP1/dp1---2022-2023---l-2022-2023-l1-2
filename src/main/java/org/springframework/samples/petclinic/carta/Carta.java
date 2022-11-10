package org.springframework.samples.petclinic.carta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="cartas")
public class Carta {

    @Column(name = "tipoCarta")
    @NotEmpty
    private String tipoCarta;

    @Column(name = "estadoCarta")
    @NotEmpty
    private String estadoCarta;

    @Column(name = "posicion")
    @NotEmpty
    private Integer posicion;

    @ManyToOne
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    
    public String getTipo(){
        return this.tipoCarta;
    }

    public void setTipo(String tipo){
        this.tipoCarta = tipo;
    }

    public String getEstado(){
        return this.estadoCarta;
    }

    public void setEstado(String estado){
        this.estadoCarta = estado;
    }

    public Integer getPosicion(){
        return this.posicion;
    }

    public void setPosicion(Integer posicion){
        this.posicion = posicion;
    }

    public Jugador getJugador(){
        return this.jugadorId;
    }

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }

}
