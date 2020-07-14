package it.contrader.service;
import java.util.List;

import it.contrader.dao.MagazzinoDAO;


public class MagazzinoService {
	private MagazzinoDAO magazzinoDAO;
	private magazzinoConverter magazzinoConverter;
	
	//Istanzio DAO  e Converter specifici.
	public magazzinoService(){
		this.magazzinoDAO = new magazzinoDAO();
		this.magazzinoConverter = new magazzinoConverter();
	}
	

	public List<magazzinoDTO> getAll() {
		// Ottiene una lista di entit� e le restituisce convertendole in DTO
		return magazzinoConverter.toDTOList(magazzinoDAO.getAll());
	}


	public magazzinoDTO read(int id) {
		// Ottiene un'entit� e la restituisce convertendola in DTO
		return magazzinoConverter.toDTO(magazzinoDAO.read(id));
	}


	public boolean insert(MagazzinoDTO dto) {
		// Converte un DTO in entit� e lo passa al DAO per l'inserimento
		return magazzinoDAO.insert(magazzinoConverter.toEntity(dto));
	}


	public boolean update(MagazzinoDTO dto) {
		// Converte un magazzinoDTO in entit� e lo passa allo magazzinoDAO per la modifica
		return magazzinoDAO.update(magazzinoConverter.toEntity(dto));
	}


	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return magazzinoDAO.delete(id);
	}

}
