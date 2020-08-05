package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.CodiceDTO;
import it.contrader.model.Codice;

@Component
public class CodiceConverter extends AbstractConverter<Codice, CodiceDTO>{
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public Codice toEntity(CodiceDTO codiceDTO) {
		Codice codice = null;
		if (codiceDTO != null) {
			codice = new Codice(codiceDTO.getId(), codiceDTO.getOtp(), userConverter.toEntity(codiceDTO.getUser()), codiceDTO.getStato(), codiceDTO.isCancellato());
		}
		return codice;
	}

	@Override
	public CodiceDTO toDTO(Codice codice) {
		CodiceDTO codiceDTO = null;
		if (codice != null) {
			codiceDTO = new CodiceDTO(codice.getId(), codice.getOtp(), userConverter.toDTO(codice.getUser()), codice.getStato(), codice.isCancellato());

		}
		return codiceDTO;
	}

}
