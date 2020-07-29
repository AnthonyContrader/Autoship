package it.contrader.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.contrader.model.Carrello;
import it.contrader.model.Codice;
import it.contrader.model.Oggetto;
import it.contrader.model.User;
import java.util.List;


@Repository
@Transactional
public interface CarrelloRepository extends CrudRepository<Carrello, Long>{
List <Carrello> findByOggetto(Oggetto oggetto);
List <Carrello> findByUser(User user);
List <Carrello> findByCodice(Codice codice);

}
