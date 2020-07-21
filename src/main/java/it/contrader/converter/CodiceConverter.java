package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.CodiceDTO;
import it.contrader.model.Codice;

public class CodiceConverter implements Converter<Codice, CodiceDTO> {
	
	@Override
	public CodiceDTO toDTO(Codice codice) {
		CodiceDTO codiceDTO = new CodiceDTO(codice.getId(), codice.getOtp());
		return codiceDTO;
	}

	/**
	 * Crea un codice di tipo User e lo riempie con i campi del parametro user di tipo UserDTO.
	 * Notare l'uso del metodo get() per ottenere il valore dell'attributo-
	 */
	@Override
	public Codice toEntity(CodiceDTO codiceDTO) {
		Codice codice = new Codice(codiceDTO.getId(), codiceDTO.getOtp());
		return codice;
	}
	
	/**
	 * Metodo per convertire le liste di User.
	 */
	@Override
	public List<CodiceDTO> toDTOList(List<Codice> codiceList) {
		//Crea una lista vuota.
		List<CodiceDTO> codiceDTOList = new ArrayList<CodiceDTO>();
		
		//Cicla tutti gli elementi della lista e li converte uno a uno
		for(Codice codice: codiceList) {
			//Utilizza il metodo toDTO per convertire ogni singolo elemento della lista
			//e lo aggiunge adda lista di DTO
			codiceDTOList.add(toDTO(codice));
		}
		return codiceDTOList;
	}

}
