package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.OggettoDTO;
import it.contrader.model.Oggetto;

@Component
public class OggettoConverter extends AbstractConverter<Oggetto, OggettoDTO>{
	
	public Oggetto toEntity(OggettoDTO oggettoDTO) {
		Oggetto oggetto = null;
		if (oggettoDTO != null) {
			oggetto = new Oggetto(oggettoDTO.getId(), oggettoDTO.getNome(), oggettoDTO.getDimensione(), oggettoDTO.isCancellato());
		}
		return oggetto;
	}

	@Override
	public OggettoDTO toDTO(Oggetto oggetto) {
		OggettoDTO oggettoDTO = null;
		if (oggetto != null) {
			oggettoDTO = new OggettoDTO(oggetto.getId(), oggetto.getNome(), oggetto.getDimensione(), oggetto.isCancellato(), false);

		}
		return oggettoDTO;
	}

}
