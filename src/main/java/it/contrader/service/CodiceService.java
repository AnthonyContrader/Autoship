package it.contrader.service;

import java.util.List;

import it.contrader.converter.CodiceConverter;
import it.contrader.dao.CodiceDAO;
import it.contrader.dto.CodiceDTO;
import it.contrader.model.Codice;

public class CodiceService extends AbstractService<Codice,CodiceDTO>{
	private CodiceDAO codiceDAO;
	private CodiceConverter codiceConverter;
	
	//Istanzio DAO  e Converter specifici.
	public CodiceService(){
		this.codiceDAO = new CodiceDAO();
		this.codiceConverter = new CodiceConverter();
	}
	

	public List<CodiceDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return codiceConverter.toDTOList(codiceDAO.getAll());
	}


	public CodiceDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return codiceConverter.toDTO(codiceDAO.read(id));
	}


	public boolean insert(CodiceDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return codiceDAO.insert(codiceConverter.toEntity(dto));
	}


	public boolean update(CodiceDTO dto) {
		// Converte un magazzinoDTO in entit� e lo passa allo magazzinoDAO per la modifica
		return codiceDAO.update(codiceConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return codiceDAO.delete(id);
	}
	
	public int id(int id) {
		return codiceDAO.id(id);
	}
	
	public String codice(int id) {
		return codiceDAO.codice(id);
	}

	
}
