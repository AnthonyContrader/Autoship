package it.contrader.dto;

import it.contrader.model.Codice;
import it.contrader.model.Oggetto;
import it.contrader.model.User;
import it.contrader.model.Carrello.CarrelloStato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarrelloDTO {
	
	private Long id;
	
	private User user;

	private Oggetto oggetto;
		
	private Codice codice;
		
	private CarrelloStato stato;
}

