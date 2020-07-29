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
	
	public List<CarrelloDTO> findCarrellosByOggetto(Oggetto oggetto) {
		return converter.toDTOList(repository.findCarrellosByOggetto(oggetto));
	}
	
	public List<CarrelloDTO> findCarrellosByCodice(Codice codice) {
		return converter.toDTOList(repository.findCarrellosByCodice(codice));
	}
	public List<CarrelloDTO> findCarrellosByUser(User user) {
		return converter.toDTOList(repository.findCarrellosByUser(user));
	}
}
