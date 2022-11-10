package org.springframework.samples.petclinic.turno;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "turnos")
public class Turno extends BaseEntity{

    @NotEmpty
    @Column(name = "dado")
    private Integer dado; 

    @NotEmpty
    @Column(name = "puntero")
    private Integer puntero;

    public Integer getDado() {
        return dado;
    }

    public Integer getPuntero() {
        return puntero;
    }

    public void setDado(Integer dado) {
        this.dado = dado;
    }

    public void setPuntero(Integer puntero) {
        this.puntero = puntero;
    } 


    
    
}
