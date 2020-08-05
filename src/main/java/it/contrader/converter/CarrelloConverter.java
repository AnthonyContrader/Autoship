package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.CarrelloDTO;

import it.contrader.model.Carrello;

@Component
public class CarrelloConverter extends AbstractConverter<Carrello, CarrelloDTO> {
	
	@Autowired
	private OggettoConverter oggettoConverter;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	public Carrello toEntity(CarrelloDTO carrelloDTO) {
		Carrello carrello = null;
		if (carrelloDTO != null) {
			carrello = new Carrello(carrelloDTO.getId(), oggettoConverter.toEntity(carrelloDTO.getOggetto()), codiceConverter.toEntity(carrelloDTO.getCodice()));
		}
		return carrello ;
	}

	@Override
	public CarrelloDTO toDTO(Carrello carrello) {
		CarrelloDTO carrelloDTO = null;
		if (carrello != null) {
			carrelloDTO = new CarrelloDTO(carrello.getId(), oggettoConverter.toDTO(carrello.getOggetto()), codiceConverter.toDTO(carrello.getCodice()));
		}
		return carrelloDTO;
	}
}
	