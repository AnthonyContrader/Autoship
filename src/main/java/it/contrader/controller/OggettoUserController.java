package it.contrader.controller;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.OggettoService;
import it.contrader.service.RobotService;

public class OggettoUserController implements Controller  {
	
private static String sub_package = "oggetto.";
	
	private OggettoService oggettoService;
	private RobotService robotService;
	
	public OggettoUserController() {
		this.oggettoService = new OggettoService();
		this.robotService = new RobotService();
	}
	
	
	public void doControl(Request request) {
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
			
		int id;
		
		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		
		switch (mode) {
		
		case "ORDINATE":
			MainDispatcher.getInstance().callView(sub_package + "OggettoUserUpdate", null);
			break;
		
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());			
			int codice = (int) (Math.random() * (5000 - 1000));
			robotService.createCode(codice, id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "OggettoUserUpdate", request);
			break;
		
		case "OGGETTOLIST":
			List<OggettoDTO> oggettiDTO = oggettoService.getAllInCell();
			//Impacchetta la request con la lista degli user
			request.put("oggetti", oggettiDTO);
			MainDispatcher.getInstance().callView(sub_package + "OggettoUser", request);
			break;
		}
		
	}

}
