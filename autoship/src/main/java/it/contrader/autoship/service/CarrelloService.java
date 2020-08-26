package it.contrader.autoship.service;

import it.contrader.autoship.service.dto.CarrelloDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Carrello.
 */
public interface CarrelloService {

    /**
     * Save a carrello.
     *
     * @param carrelloDTO the entity to save
     * @return the persisted entity
     */
    CarrelloDTO save(CarrelloDTO carrelloDTO);

    /**
     * Get all the carrellos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CarrelloDTO> findAll(Pageable pageable);


    /**
     * Get the "id" carrello.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CarrelloDTO> findOne(Long id);

    /**
     * Delete the "id" carrello.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
