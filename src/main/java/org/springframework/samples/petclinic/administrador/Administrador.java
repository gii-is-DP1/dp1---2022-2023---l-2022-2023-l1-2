package org.springframework.samples.petclinic.administrador;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.user.Usuario;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "administradores")
public class Administrador extends Usuario{
    
}
