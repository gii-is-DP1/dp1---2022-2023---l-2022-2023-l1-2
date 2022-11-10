package org.springframework.samples.petclinic.administrador;

import javax.persistence.Entity;

import org.springframework.samples.petclinic.user.Usuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Administrador extends Usuario{
    
}
