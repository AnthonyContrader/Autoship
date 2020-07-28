package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Codice;

@Repository
@Transactional
public interface CodiceRepository extends CrudRepository<Codice, Long>{
	
	Codice findByOtp(String otp);
	
	List<Codice> findByCancellatoFalse();
}
