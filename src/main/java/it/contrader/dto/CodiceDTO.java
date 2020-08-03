package it.contrader.dto;

import it.contrader.model.User;
import it.contrader.model.Codice.CodiceStato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CodiceDTO {
	
	private Long id;

	private String otp;
	
	private User user;
	
	private CodiceStato stato;
	
	private boolean cancellato;
}