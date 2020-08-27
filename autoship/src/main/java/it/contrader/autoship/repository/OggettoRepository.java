package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Oggetto;
import it.contrader.autoship.service.dto.OggettoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Oggetto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OggettoRepository extends JpaRepository<Oggetto, Long> {

	Page<Oggetto> findByCellaFalseAndCancellatoFalse(Pageable pageable);
	
	Page<Oggetto> findByCellaTrueAndCancellatoFalse(Pageable pageable);
	
}
