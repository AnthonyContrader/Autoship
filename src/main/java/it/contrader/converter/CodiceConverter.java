package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.CodiceDTO;
import it.contrader.model.Codice;


public class CodiceConverter {
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo User.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public CodiceDTO toDTO(Codice codice) {
		CodiceDTO codiceDTO = new CodiceDTO(codice.getid(), codice.getCodice());
		return codiceDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Codice toEntity(CodiceDTO codiceDTO) {
		Codice codice = new Codice(codiceDTO.getId(), codice.getCodice());
		return codice ;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<OggettoDTO> toDTOList(List<Oggetto> oggettoList) {
		//Crea una lista vuota.
		List<OggettoDTO> oggettoDTOList = new ArrayList<OggettoDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Oggetto oggetto : oggettoList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			oggettoDTOList.add(toDTO(oggetto));
		}
		return oggettoDTOList;
	}

}
