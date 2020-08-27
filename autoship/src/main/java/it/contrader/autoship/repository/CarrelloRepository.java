package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Carrello;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Carrello entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

	Page<Carrello> findByCodiceId(Pageable pageable, Long codiceId);
	
}
