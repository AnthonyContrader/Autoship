package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Carrello;
import it.contrader.autoship.domain.Magazzino;
import it.contrader.autoship.domain.Oggetto;
import it.contrader.autoship.service.dto.MagazzinoDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Magazzino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagazzinoRepository extends JpaRepository<Magazzino, Long> {

	Optional<Magazzino> findByOggetto(Oggetto oggetto);
	
	Page<Magazzino> findByOggettoIdNotNullAndCodiceNull(Pageable pageable);
	
	Page<Magazzino> findByCodiceId(Pageable pageable, Long codiceId);
}
