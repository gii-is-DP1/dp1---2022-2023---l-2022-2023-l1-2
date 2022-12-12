package org.springframework.samples.petclinic.tablero;

import java.util.Optional;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
	private TableroRepository tableroRepository;

    @Autowired
	public TableroService(TableroRepository tableroRepository) {
		this.tableroRepository = tableroRepository;
	}
	
	public Optional<Tablero> findById(Integer id){
		return tableroRepository.findById(id);
	}
}
