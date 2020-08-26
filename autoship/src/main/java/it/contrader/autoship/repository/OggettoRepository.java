package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Oggetto;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Oggetto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OggettoRepository extends JpaRepository<Oggetto, Long> {

}
