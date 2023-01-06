package org.springframework.samples.petclinic.logro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogroService {
    private LogroRepository logroRepository;

    @Autowired
    public LogroService(LogroRepository logroRepository) {
        this.logroRepository = logroRepository;
    }

    public List<Logro> findAllLogros(){
		return logroRepository.findAll();
	}

    public Optional<Logro> findLogroById(Integer id){
        return logroRepository.findById(id);
    }

    @Transactional
	public Logro save(Logro l) throws DataAccessException {
		return logroRepository.save(l);
	}

    @Transactional
	public void delete(Logro logro) throws DataAccessException{
        
		logroRepository.delete(logro);
	}

    public List<Logro> LogroByPlayer(Integer id) {
		return logroRepository.findByPlayer(id);
	}

}
