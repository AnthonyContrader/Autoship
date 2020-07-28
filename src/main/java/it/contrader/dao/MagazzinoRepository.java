package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Magazzino;
import it.contrader.model.Oggetto;

@Repository
@Transactional
public interface MagazzinoRepository extends CrudRepository<Magazzino, Long> {
	
	Magazzino findByOggetto(Oggetto oggetto);
	
	Magazzino findByOtp(String otp);
	
	List<Magazzino> findMagazzinosByOtp(String otp);

}