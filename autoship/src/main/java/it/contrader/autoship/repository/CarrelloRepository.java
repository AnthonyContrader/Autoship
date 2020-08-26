package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Carrello;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Carrello entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

}
