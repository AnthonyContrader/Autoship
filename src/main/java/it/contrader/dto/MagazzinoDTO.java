package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MagazzinoDTO {
	
	private Long id;

	private OggettoDTO oggetto;
		
	private int capienza;
		
	private CodiceDTO codice;
		
	private boolean cancellato;
}
