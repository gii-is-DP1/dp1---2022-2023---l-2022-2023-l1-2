package org.springframework.samples.petclinic.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "administradores")
public class Administrador extends BaseEntity {

    @Size(min = 3, max = 50)
	String autoridad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nombre_usuario")
    Usuario usuario;
    
}
