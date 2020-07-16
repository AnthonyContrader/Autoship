package it.contrader.service;

import java.util.List;

import it.contrader.converter.CodiceConverter;
import it.contrader.dao.CodiceDAO;
import it.contrader.dto.CodiceDTO;

public class CodiceService {
	private OggettoDAO oggettoDAO;
	private OggettoConverter oggettoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public CodiceService(){
		this.CodiceDAO = new CodiceDAO();
		this.CodiceConverter = new CodiceConverter();
	}
	

	public List<CodiceDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return CodiceConverter.toDTOList(CodiceDAO.getAll());
	}


	public CodiceDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return CodiceConverter.toDTO(Codice.read(id));
	}


	public boolean insert(CodiceDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return CodiceDAO.insert(CodiceConverter.toEntity(dto));
	}


	public boolean update(CodiceDTO dto) {
		// Converte un magazzinoDTO in entit� e lo passa allo magazzinoDAO per la modifica
		return CodiceDAO.update(CodiceConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return CodiceDAO.delete(id);
	}
	
	public int id(int id) {
		return CodiceDAO.id(id);
	}
	
	public int dimensione(int id) {
		return CodiceDAO.dimensione(id);
	}
	
	public List<CodiceDTO> getAllInCell() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return CodiceConverter.toDTOList(CodiceDAO.getAllInCell());
	}
}
