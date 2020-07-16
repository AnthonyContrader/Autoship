package it.contrader.controller;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import it.contrader.dto.CodiceDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;
import it.contrader.service.RobotService;

public class OggettoUserController implements Controller  {
	
private static String sub_package = "oggetto.";
	
	private OggettoService oggettoService;
	private MagazzinoService magazzinoService;
	private RobotService robotService;
	private CodiceService codiceService;
	
	public OggettoUserController() {
		this.oggettoService = new OggettoService();
		this.magazzinoService = new MagazzinoService();
		this.robotService = new RobotService();
		this.codiceService = new CodiceService();
		
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
			int checkCodice;
			try {
				checkCodice = magazzinoService.checkCodice(id);
				if(checkCodice == -1) {
					int codice = (int) (Math.random() * (5000 - 1000));
					CodiceDTO codicetoinsert = new CodiceDTO(codice);
					codiceService.insert(codicetoinsert);
					robotService.createCode(codice, id);
				}
				request = new Request();
				request.put("mode", "mode");
				MainDispatcher.getInstance().callView(sub_package + "OggettoUserUpdate", request);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
