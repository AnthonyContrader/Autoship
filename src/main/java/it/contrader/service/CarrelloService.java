package it.contrader.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.CarrelloConverter;
import it.contrader.dao.CarrelloRepository;
import it.contrader.dto.CarrelloDTO;
import it.contrader.model.Carrello;
import it.contrader.model.Codice;
import it.contrader.model.Oggetto;
import it.contrader.model.User;

@Service
public class CarrelloService extends AbstractService<Carrello, CarrelloDTO>{
	@Autowired
	private CarrelloConverter converter;
	@Autowired
	private CarrelloRepository repository;
	
	public List<CarrelloDTO> findByOggetto(Oggetto oggetto) {
		return converter.toDTOList(repository.findByOggetto(oggetto));
	}
	
	public List<CarrelloDTO> findByCodice(Codice codice) {
		return converter.toDTOList(repository.findByCodice(codice));
	}
	public List<CarrelloDTO> findByUser(User user) {
		return converter.toDTOList(repository.findByUser(user));
	}
}
