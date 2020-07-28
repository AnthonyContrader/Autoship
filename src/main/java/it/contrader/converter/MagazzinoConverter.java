package it.contrader.converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Magazzino;

@Component
public class MagazzinoConverter extends AbstractConverter<Magazzino, MagazzinoDTO> {
	public Magazzino toEntity(MagazzinoDTO magazzinoDTO) {
		Magazzino magazzino = null;
		if (magazzinoDTO != null) {
			magazzino = new Magazzino(magazzinoDTO.getId(), magazzinoDTO.getOggetto(), magazzinoDTO.getCapienza(), magazzinoDTO.getOtp(),magazzinoDTO.isCancellato());
		}
		return magazzino ;
	}

	@Override
	public MagazzinoDTO toDTO(Magazzino magazzino ) {
		MagazzinoDTO magazzinoDTO = null;
		if (magazzino != null) {
			magazzinoDTO = new MagazzinoDTO(magazzino.getId(), magazzino.getOggetto(), magazzino.getCapienza(), magazzino.getOtp(), magazzino.isCancellato());

		}
		return magazzinoDTO;
	}
}
	