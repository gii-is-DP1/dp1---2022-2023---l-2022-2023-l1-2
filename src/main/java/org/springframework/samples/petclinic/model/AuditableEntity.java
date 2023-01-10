package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditableEntity extends BaseEntity{
    @CreatedBy
    private String creator;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedBy
    private String modifier;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
}