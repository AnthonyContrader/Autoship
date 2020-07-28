package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.MagazzinoConverter;
import it.contrader.dao.MagazzinoRepository;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Magazzino;
import it.contrader.model.Oggetto;


@Service
public class MagazzinoService extends AbstractService<Magazzino, MagazzinoDTO>{
	
	@Autowired
	private MagazzinoConverter converter;
	@Autowired
	private MagazzinoRepository repository;
	
	public MagazzinoDTO findByOggetto(Oggetto oggetto) {
		return converter.toDTO(repository.findByOggetto(oggetto));
	}

}
