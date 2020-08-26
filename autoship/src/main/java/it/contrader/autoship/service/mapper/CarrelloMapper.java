package it.contrader.autoship.service.mapper;

import it.contrader.autoship.domain.*;
import it.contrader.autoship.service.dto.CarrelloDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Carrello and its DTO CarrelloDTO.
 */
@Mapper(componentModel = "spring", uses = {OggettoMapper.class, CodiceMapper.class})
public interface CarrelloMapper extends EntityMapper<CarrelloDTO, Carrello> {

    @Mapping(source = "oggetto.id", target = "oggettoId")
    @Mapping(source = "oggetto.nome", target = "oggettoNome")
    @Mapping(source = "codice.id", target = "codiceId")
    @Mapping(source = "codice.otp", target = "codiceOtp")
    CarrelloDTO toDto(Carrello carrello);

    @Mapping(source = "oggettoId", target = "oggetto")
    @Mapping(source = "codiceId", target = "codice")
    Carrello toEntity(CarrelloDTO carrelloDTO);

    default Carrello fromId(Long id) {
        if (id == null) {
            return null;
        }
        Carrello carrello = new Carrello();
        carrello.setId(id);
        return carrello;
    }
}
