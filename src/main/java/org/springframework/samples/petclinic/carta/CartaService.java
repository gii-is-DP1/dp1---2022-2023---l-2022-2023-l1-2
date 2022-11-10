package org.springframework.samples.petclinic.carta;

public class CartaService {
    
    private CartaRepository cr;

    public Carta findByTipo(String tipo){
        return cr.findByTipo(tipo);
    }

}
