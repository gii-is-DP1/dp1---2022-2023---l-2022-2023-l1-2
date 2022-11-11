package org.springframework.samples.petclinic.carta;

import org.springframework.stereotype.Service;

@Service
public class CartaService {
    
    private CartaRepository cr;

    public Carta findByTipo(String tipo){
        return cr.findByTipo(tipo);
    }

}
