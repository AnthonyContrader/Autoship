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
<<<<<<< HEAD
		CodiceDTO codiceDTO = new CodiceDTO (codice.getCodice());
=======
		CodiceDTO codiceDTO = new CodiceDTO (codice.getId(), codice.getCodice(), 0);
>>>>>>> 0ace919cf34d1162062a0b0f11dc68a864900b94
		return codiceDTO;
	}

	/**
	 * Crea un oggetto di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	public Codice toEntity(CodiceDTO codiceDTO) {
<<<<<<< HEAD
		Codice codice = new Codice(codiceDTO.getId(), codiceDTO.getCodice());
		return codice ;
=======
		Codice codiceDTO1 = new Codice(codiceDTO1.getId(), codiceDTO1.getCodice());
		return codiceDTO1 ;
>>>>>>> 0ace919cf34d1162062a0b0f11dc68a864900b94
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	public List<CodiceDTO> toDTOList(List<Codice> codiceList) {
		//Crea una lista vuota.
<<<<<<< HEAD
		List<CodiceDTO> codiceDTOList = new ArrayList<CodiceDTO>();
=======
		List<CodiceDTO> codiceDTOList = newArrayList<codiceDTO>();
>>>>>>> 0ace919cf34d1162062a0b0f11dc68a864900b94
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Codice codice : codiceList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			codiceDTOList.add(toDTO(codice));
		}
		return codiceDTOList;
	}

}