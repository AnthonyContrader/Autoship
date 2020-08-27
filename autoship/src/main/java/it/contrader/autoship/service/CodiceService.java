package it.contrader.autoship.service;

import it.contrader.autoship.service.dto.CodiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Codice.
 */
public interface CodiceService {

    /**
     * Save a codice.
     *
     * @param codiceDTO the entity to save
     * @return the persisted entity
     */
    CodiceDTO save(CodiceDTO codiceDTO);

    /**
     * Get all the codices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CodiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" codice.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CodiceDTO> findOne(Long id);
    
    Optional<CodiceDTO> findByOtp(String otp);

    /**
     * Delete the "id" codice.
     *
     * @param id the id of the entity
     */
//    Page<CodiceDTO> findCarrellosByCodice(CodiceDTO codiceDTO);

	/**
	 * Delete the codice by id.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);
    
    
}
