package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Magazzino;

public class MagazzinoConverter {
	/**
	 * Crea un oggetto di tipo UserDTO e lo riempie con i campi del parametro user di tipo User.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public MagazzinoDTO toDTO(Magazzino magazzino) {
		MagazzinoDTO magazzinoDTO = new MagazzinoDTO(magazzino.getId_oggetto(), magazzino.getCapienza(), magazzino.getPosizione());
		return magazzinoDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Magazzino toEntity(MagazzinoDTO magazzinoDTO) {
		Magazzino magazzino = new Magazzino(magazzinoDTO.getId(), magazzinoDTO.getId_oggetto(), magazzinoDTO.getCapienza(), magazzinoDTO.getPosizione());
		return magazzino;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<MagazzinoDTO> toDTOList(List<Magazzino> magazzinoList) {
		//Crea una lista vuota.
		List<MagazzinoDTO> magazzinoDTOList = new ArrayList<MagazzinoDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Magazzino magazzino : magazzinoList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			magazzinoDTOList.add(toDTO(magazzino));
		}
		return magazzinoDTOList;
	}
}
