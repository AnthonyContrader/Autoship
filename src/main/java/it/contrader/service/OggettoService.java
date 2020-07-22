package it.contrader.service;

import java.util.List;

import it.contrader.converter.OggettoConverter;
import it.contrader.dao.OggettoDAO;
import it.contrader.dto.OggettoDTO;
import it.contrader.model.Oggetto;

public class OggettoService extends AbstractService<Oggetto,OggettoDTO>{
	private OggettoDAO oggettoDAO;
	private OggettoConverter oggettoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public OggettoService(){
		this.oggettoDAO = new OggettoDAO();
		this.oggettoConverter = new OggettoConverter();
	}
	

	public List<OggettoDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return oggettoConverter.toDTOList(oggettoDAO.getAll());
	}
	
	public List<OggettoDTO> getAllIn() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return oggettoConverter.toDTOList(oggettoDAO.getAllIn());
	}


	public OggettoDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return oggettoConverter.toDTO(oggettoDAO.read(id));
	}


	public boolean insert(OggettoDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return oggettoDAO.insert(oggettoConverter.toEntity(dto));
	}


	public boolean update(OggettoDTO dto) {
		// Converte un magazzinoDTO in entit� e lo passa allo magazzinoDAO per la modifica
		return oggettoDAO.update(oggettoConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return oggettoDAO.delete(id);
	}
	
	public int id(int id) {
		return oggettoDAO.id(id);
	}
	
	public String nome(int id) {
		return oggettoDAO.nome(id);
	}
	
	public int dimensione(int id) {
		return oggettoDAO.dimensione(id);
	}
	
	public List<OggettoDTO> getAllInCell() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return oggettoConverter.toDTOList(oggettoDAO.getAllInCell());
	}
	
	public List<OggettoDTO> getAllInCellOrdered() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return oggettoConverter.toDTOList(oggettoDAO.getAllInCellOrdered());
	}
	
	public List<OggettoDTO> getNotInCell() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return oggettoConverter.toDTOList(oggettoDAO.getNotInCell());
	}
	
	public List<OggettoDTO> getNotOrdered() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return oggettoConverter.toDTOList(oggettoDAO.getNotOrdered());
	}
}
