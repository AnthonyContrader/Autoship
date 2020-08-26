package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Codice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Codice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodiceRepository extends JpaRepository<Codice, Long> {

}
