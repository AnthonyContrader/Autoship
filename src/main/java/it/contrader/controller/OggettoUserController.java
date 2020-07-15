package it.contrader.controller;

import java.util.List;

import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.OggettoService;

public class OggettoUserController implements Controller  {
	
private static String sub_package = "oggetto.";
	
	private OggettoService oggettoService;
	
	public OggettoUserController() {
		this.oggettoService = new OggettoService();
	}
	
	public void doControl(Request request) {
		
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
		
		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		
		switch (mode) {
		
		case "OGGETTOLIST":
			List<OggettoDTO> oggettiDTO = oggettoService.getAllInCell();
			//Impacchetta la request con la lista degli user
			request.put("oggetti", oggettiDTO);
			MainDispatcher.getInstance().callView(sub_package + "OggettoUser", request);
			break;
		}
		
	}

}
