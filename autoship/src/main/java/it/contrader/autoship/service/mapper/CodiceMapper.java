package it.contrader.autoship.service.mapper;

import it.contrader.autoship.domain.*;
import it.contrader.autoship.service.dto.CodiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Codice and its DTO CodiceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CodiceMapper extends EntityMapper<CodiceDTO, Codice> {



    default Codice fromId(Long id) {
        if (id == null) {
            return null;
        }
        Codice codice = new Codice();
        codice.setId(id);
        return codice;
    }
}
