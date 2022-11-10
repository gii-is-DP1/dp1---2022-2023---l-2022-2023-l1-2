package org.springframework.samples.petclinic.orden;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


@Repository
public interface OrdenRepository extends CrudRepository<Orden, Integer>{
    List<Orden> findAll();
}
