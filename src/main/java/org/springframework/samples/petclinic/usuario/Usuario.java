package org.springframework.samples.petclinic.usuario;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="usuarios")
public class Usuario extends BaseEntity{
    @NotBlank
	@Column(name = "nombre_usuario")
	String nombreUsuario;
	@NotBlank
	@Column(name = "contraseña")
	String contraseña;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "fecha_nacimiento")
	@NotNull
	LocalDate fechaNacimiento;
	@Column(name = "administrador")
	@NotNull
	Boolean esAdministrador;
	@Column(name = "jugador")
	@NotNull
	Boolean esJugador;
}
