package it.contrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Oggetto;


@Repository
@Transactional
public interface OggettoRepository extends CrudRepository<Oggetto, Long> {

	List<Oggetto> findByCancellatoFalse();
}
