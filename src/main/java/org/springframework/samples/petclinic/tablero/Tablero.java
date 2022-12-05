package org.springframework.samples.petclinic.tablero;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.carta.Carta;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="tablero")
public class Tablero extends BaseEntity{
    String background;
    @Positive
    int width;
    @Positive
    int height;

    public Tablero(){
        this.background="resources/images/tablero.png";
        this.width=700;
        this.height=850;
    }


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "tablero",fetch = FetchType.EAGER)
    List<Carta> cartas; 
}
