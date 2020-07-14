package it.contrader.controller;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Oggetto;
import it.contrader.service.MagazzinoService;

public class OggettoController implements Controller  {


	private MagazzinoService magazzinoService;
	public void doControl(Request request) {
		
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int id;
		Oggetto oggetto;
		int capienza;
		int posizione;

		switch (mode) {
		
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
		case "READ":
			id = Integer.parseInt(request.get("id").toString());
			MagazzinoDTO userDTO = magazzinoService.read(id);
			request.put("user", userDTO);
			MainDispatcher.getInstance().callView(sub_package + "MagazzinoRead", request);
		}

	
		
	
		
	}
	public OggettoController() {
		this.magazzinoService = new MagazzinoService();
	}
         
	
}
