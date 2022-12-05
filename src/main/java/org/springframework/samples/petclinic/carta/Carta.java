package org.springframework.samples.petclinic.carta;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.tablero.Tablero;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
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

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "jugador_id")
    private Jugador jugador;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "tablero_id")
    private Tablero tablero;

    public Integer getPositionXInPixels(Integer size) {
    	Integer pos=0;
        switch(posicion){
            case 0:
                pos = 0;
            break;
            case 1:
                pos = 100;
            break;
            case 2:
                pos = 270;
            break;
            case 3:
                pos = 450;
            break;
            case 4:
                pos = 560;
            break;
            case 5:
                pos= 407;
            break;
            case 6:
                pos = 245;
            break;
            case 7:
                pos = 65;
            break;
        }
        return pos;
    }

    public Integer getPositionYInPixels(Integer size) {
    	Integer pos=0;
        switch(posicion){
            case 0:
                pos = 0;
            break;
            case 1:
                pos = 50;
            break;
            case 2:
                pos = 50;
            break;
            case 3:
                pos = 50;
            break;
            case 4:
                pos = 290;
            break;
            case 5:
                pos = 450;
            break;
            case 6:
                pos = 570;
            break;
            case 7:
                pos = 585;
            break;
        }
        return pos;
    }



}
