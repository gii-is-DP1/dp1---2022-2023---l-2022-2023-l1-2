package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    private PartidaRepository partidaRepository;

    @Autowired
	public PartidaService(PartidaRepository partidaRepository) {
		this.partidaRepository = partidaRepository;
	}

	public List<Partida> findAllPartidas(){
		return partidaRepository.findAll();
	}

	public Optional<Partida> findById(Integer id){
		return partidaRepository.findById(id);
	} 

	public Partida save(Partida p){
		return partidaRepository.save(p);
	}
}
