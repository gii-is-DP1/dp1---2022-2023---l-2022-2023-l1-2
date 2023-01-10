package org.springframework.samples.petclinic.usuario;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table(name="usuarios")
public class Usuario{
    
	@Id
	@NotBlank
	@Column(name = "nombre_usuario")
	String nombreUsuario;
	@NotBlank
	@Column(name = "nombre")
	String nombre;
	@NotBlank
	@Column(name = "apellidos")
	String apellidos;
	@NotBlank
	@Column(name = "contrasena")
	String contrasena;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "fecha_nacimiento")
	@NotNull
	LocalDate fechaNacimiento;

	boolean enabled;

	@CreatedBy
    private String creator;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private LocalDate lastModifiedDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
	private Set<Autoridad> administrador;

}
