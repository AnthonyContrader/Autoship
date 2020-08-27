package it.contrader.autoship.repository;

import it.contrader.autoship.domain.Codice;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Codice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CodiceRepository extends JpaRepository<Codice, Long> {

	Optional<Codice> findByOtp(String otp);
	
	Page<Codice> findByUserid(Pageable pageable, Long user_id);
	
}
