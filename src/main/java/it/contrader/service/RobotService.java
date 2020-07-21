package it.contrader.service;

import java.util.List;

import it.contrader.dao.RobotDAO;


public class RobotService extends AbstractService{
	private RobotDAO robotDAO;

	
	//Istanzio DAO  e Converter specifici.
	public RobotService(){
		this.robotDAO = new RobotDAO();
	}

	public boolean delete(int id) {
		// Questo mtodo chiama direttamente il DAO
		return robotDAO.delete(id);
	}
	
	public int id(int id) {
		return robotDAO.id(id);
	}

	public boolean createCode(int codice, int oggetto) {
		return robotDAO.createCode(codice, oggetto);
	}
	
	public boolean spedisci(int codice) {
		return robotDAO.spedizione(codice);
	}
	
}
