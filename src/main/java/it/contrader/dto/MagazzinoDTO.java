package it.contrader.dto;

import it.contrader.model.Oggetto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MagazzinoDTO {
	
	private Long id;

	private Oggetto oggetto;
		
	private int capienza;
		
	private String otp;
		
	private boolean cancellato;
}
