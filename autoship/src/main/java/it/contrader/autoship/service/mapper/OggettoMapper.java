package it.contrader.autoship.service.mapper;

import it.contrader.autoship.domain.*;
import it.contrader.autoship.service.dto.OggettoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Oggetto and its DTO OggettoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OggettoMapper extends EntityMapper<OggettoDTO, Oggetto> {



    default Oggetto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Oggetto oggetto = new Oggetto();
        oggetto.setId(id);
        return oggetto;
    }
}
