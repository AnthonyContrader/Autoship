package it.contrader.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OggettoDTO {
	
	private Long id;
	
	private String nome;
	
	private int dimensione;
	
	private boolean cancellato;
}
