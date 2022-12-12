package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	@Transactional
	public Partida save(Partida p){
		return partidaRepository.save(p);
	}

	public List<Partida> partidasByPlayer(Integer id){
		return partidaRepository.findByPlayer(id);
	}

	public Boolean numeroCorrecto(Partida p){
		if(p.getJugadores().size()>=2 && p.getJugadores().size()<=4){
			return true;
		}else{
			return false;
		}
	}


}
