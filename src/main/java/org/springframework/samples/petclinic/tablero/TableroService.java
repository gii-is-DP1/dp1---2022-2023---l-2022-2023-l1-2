package org.springframework.samples.petclinic.tablero;

import java.util.Optional;

import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.partida.Partida;
import org.springframework.samples.petclinic.partida.PartidaService;
import org.springframework.stereotype.Service;

@Service
public class TableroService {
	private TableroRepository tableroRepository;

    @Autowired
    private PartidaService partidaService;


    private static final String REDIRECT_TABLERO = "redirect:/tableros/";

    @Autowired
	public TableroService(TableroRepository tableroRepository) {
		this.tableroRepository = tableroRepository;
	}
	
	public Optional<Tablero> findById(Integer id){
		return tableroRepository.findById(id);
	}

	@Transactional
    public String cambiarTurno(Partida p){

        Integer n = p.getJugadores().size();

        p.setJugadorActual((p.getJugadorActual()+1)%n);   
        p.setDadoTirado(false);
        partidaService.save(p);

        return "redirect:/partidas/"+p.getId()+"/tablero";
    }

}
