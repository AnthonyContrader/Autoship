package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Carrello;
import it.contrader.model.Codice;
import it.contrader.model.Codice.CodiceStato;
import it.contrader.model.User;

@Repository
@Transactional
public interface CodiceRepository extends CrudRepository<Codice, Long>{
	
	Codice findByOtp(String otp);
	
	List<Codice> findCodicesByUser(User user);
	
	List<Codice> findByCancellatoFalse();
	
	List<Codice> findByStato(CodiceStato stato);
}
