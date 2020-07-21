package it.contrader.service;

import it.contrader.dao.RobotDAO;


public class RobotService{
	private RobotDAO robotDAO;

	
	//Istanzio DAO  e Converter specifici.
	public RobotService(){
		this.robotDAO = new RobotDAO();
	}

	public boolean createCode(String codice, int oggetto) {
		return robotDAO.createCode(codice, oggetto);
	}
	
	public boolean spedisci(String codice) {
		return robotDAO.spedizione(codice);
	}
	
}
