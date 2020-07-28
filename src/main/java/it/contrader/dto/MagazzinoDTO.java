package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class MagazzinoDTO {
	
	private Long id;

	private int id_oggetto;
		
	private int capienza;
		
	private String otp;
		
	private boolean cancellato;
}
