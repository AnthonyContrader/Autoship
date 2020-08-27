package it.contrader.autoship.service;

import it.contrader.autoship.domain.Magazzino;
import it.contrader.autoship.domain.Oggetto;
import it.contrader.autoship.service.dto.MagazzinoDTO;
import it.contrader.autoship.service.dto.OggettoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Magazzino.
 */
public interface MagazzinoService {

    /**
     * Save a magazzino.
     *
     * @param magazzinoDTO the entity to save
     * @return the persisted entity
     */
    MagazzinoDTO save(MagazzinoDTO magazzinoDTO);

    /**
     * Get all the magazzinos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MagazzinoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" magazzino.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MagazzinoDTO> findOne(Long id);

    /**
     * Delete the "id" magazzino.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    Optional<MagazzinoDTO> findByOggetto(OggettoDTO oggetto);
    
    Page<MagazzinoDTO> findByOggettoIdNotNullAndCodiceNull(Pageable pageable);
}
