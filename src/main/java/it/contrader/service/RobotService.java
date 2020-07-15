package it.contrader.service;

import it.contrader.dao.OggettoDAO;
import it.contrader.dao.RobotDAO;

public class RobotService {
	
	private RobotDAO robotDAO;
	
	public RobotService() {
		robotDAO = new RobotDAO();
	}
	
	public boolean createCode(int codice, int oggetto) {
		return robotDAO.createCode(codice, oggetto);
	}
	
	

}
