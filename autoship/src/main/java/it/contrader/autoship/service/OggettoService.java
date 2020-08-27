package it.contrader.autoship.service;

import it.contrader.autoship.service.dto.OggettoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Oggetto.
 */
public interface OggettoService {

    /**
     * Save a oggetto.
     *
     * @param oggettoDTO the entity to save
     * @return the persisted entity
     */
    OggettoDTO save(OggettoDTO oggettoDTO);

    /**
     * Get all the oggettos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OggettoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" oggetto.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OggettoDTO> findOne(Long id);

    /**
     * Delete the "id" oggetto.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    Page<OggettoDTO> findByCellaFalseAndCancellatoFalse(Pageable pageable);
    
    Page<OggettoDTO> findByCellaTrueAndCancellatoFalse(Pageable pageable);
}
