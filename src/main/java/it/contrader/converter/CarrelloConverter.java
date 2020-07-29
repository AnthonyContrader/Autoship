package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.CarrelloDTO;

import it.contrader.model.Carrello;


@Component
public class CarrelloConverter extends AbstractConverter<Carrello, CarrelloDTO> {
	public Carrello toEntity(CarrelloDTO carrelloDTO) {
		Carrello carrello = null;
		if (carrelloDTO != null) {
			carrello = new Carrello(carrelloDTO.getId(), carrelloDTO.getUser(), carrelloDTO.getOggetto(), carrelloDTO.getCodice(), carrelloDTO.getStato());
		}
		return carrello ;
	}

	@Override
	public CarrelloDTO toDTO(Carrello carrello) {
		CarrelloDTO carrelloDTO = null;
		if (carrello != null) {
			carrelloDTO = new CarrelloDTO(carrello.getId(), carrello.getUser(), carrello.getOggetto(), carrello.getCodice(), carrello.getStato());
		}
		return carrelloDTO;
	}
}
	