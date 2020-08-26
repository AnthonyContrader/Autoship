package it.contrader.autoship.service.mapper;

import it.contrader.autoship.domain.*;
import it.contrader.autoship.service.dto.MagazzinoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Magazzino and its DTO MagazzinoDTO.
 */
@Mapper(componentModel = "spring", uses = {OggettoMapper.class, CodiceMapper.class})
public interface MagazzinoMapper extends EntityMapper<MagazzinoDTO, Magazzino> {

    @Mapping(source = "oggetto.id", target = "oggettoId")
    @Mapping(source = "oggetto.nome", target = "oggettoNome")
    @Mapping(source = "codice.id", target = "codiceId")
    @Mapping(source = "codice.otp", target = "codiceOtp")
    MagazzinoDTO toDto(Magazzino magazzino);

    @Mapping(source = "oggettoId", target = "oggetto")
    @Mapping(source = "codiceId", target = "codice")
    Magazzino toEntity(MagazzinoDTO magazzinoDTO);

    default Magazzino fromId(Long id) {
        if (id == null) {
            return null;
        }
        Magazzino magazzino = new Magazzino();
        magazzino.setId(id);
        return magazzino;
    }
}
