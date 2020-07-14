package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Oggetto;


public class OggettoConverter {
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo User.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public oggettoDTO toDTO(Oggetto oggetto) {
		OggettoDTO oggettoDTO = new OggettoDTO(oggetto.getNome(), oggetto.getDimensione());
		return oggettoDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Oggetto toEntity(OggettoDTO oggettoDTO) {
		Oggetto oggetto = new Oggetto(oggetto.getNome(), oggetto.getDimensione());
		return oggetto;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<OggettoDTO> toDTOList(List<OggettoDTO> oggettoList) {
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
