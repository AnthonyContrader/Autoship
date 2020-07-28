package converter;

import org.springframework.stereotype.Component;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Magazzino;

@Component

public class MagazzinoConverter extends AbstractConverter<Magazzino, MagazzinoDTO> {
	public Magazzino toEntity(MagazzinoDTO magazzinoDTO) {
		Magazzino magazzino = null;
		if (magazzinoDTO != null) {
			magazzino = new Magazzino(magazzinoDTO.getId(), magazzinoDTO.getid_oggetto(), magazzinoDTO.getcapienza(), magazzinoDTO.getotp(),magazzinoDTO.cancellato());
		}
		return magazzino ;
	}

	@Override
	public MagazzinoDTO toDTO(Magazzino magazzino ) {
		MagazzinoDTO magazzinoDTO = null;
		if (magazzino != null) {
			magazzinoDTO = new Magazzino(magazzino.getId(), magazzino.getid_oggetto(), magazzino.getcapienza(), magazzino.getotp(),magazzino.cancellato());

		}
		return magazzinoDTO;
	}

	