package org.springframework.samples.petclinic.orden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {
    private OrdenRepository ordenRepository;

    @Autowired
	public OrdenService(OrdenRepository ordenRepository) {
		this.ordenRepository = ordenRepository;
	}
}
