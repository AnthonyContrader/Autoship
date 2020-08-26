package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Magazzino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Magazzino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MagazzinoRepository extends JpaRepository<Magazzino, Long> {

}
