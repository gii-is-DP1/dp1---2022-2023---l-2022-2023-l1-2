package org.springframework.samples.petclinic.orden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordenes")
public class Orden extends BaseEntity{
    @Column(name = "orden")
    @Min(1)
    @Max(4)
    Integer orden; 
}
