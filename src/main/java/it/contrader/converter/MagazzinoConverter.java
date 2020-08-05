package it.contrader.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Magazzino;

@Component
public class MagazzinoConverter extends AbstractConverter<Magazzino, MagazzinoDTO> {
	
	@Autowired
	private OggettoConverter oggettoConverter;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	public Magazzino toEntity(MagazzinoDTO magazzinoDTO) {
		Magazzino magazzino = null;
		if (magazzinoDTO != null) {
			magazzino = new Magazzino(magazzinoDTO.getId(), oggettoConverter.toEntity(magazzinoDTO.getOggetto()), magazzinoDTO.getCapienza(), codiceConverter.toEntity(magazzinoDTO.getCodice()),magazzinoDTO.isCancellato());
		}
		return magazzino ;
	}

	@Override
	public MagazzinoDTO toDTO(Magazzino magazzino ) {
		MagazzinoDTO magazzinoDTO = null;
		if (magazzino != null) {
			magazzinoDTO = new MagazzinoDTO(magazzino.getId(), oggettoConverter.toDTO(magazzino.getOggetto()), magazzino.getCapienza(), codiceConverter.toDTO(magazzino.getCodice()), magazzino.isCancellato());

		}
		return magazzinoDTO;
	}
}
	