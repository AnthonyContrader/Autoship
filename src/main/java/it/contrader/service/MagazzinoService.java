package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.MagazzinoConverter;
import it.contrader.dao.MagazzinoRepository;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Codice;
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
	
	public MagazzinoDTO findByCodice(Codice codice) {
		return converter.toDTO(repository.findByCodice(codice));
	}
	
	public List<MagazzinoDTO> findMagazzinosByCodice(Codice codice){
		return converter.toDTOList(repository.findMagazzinosByCodice(codice));
	}

}
