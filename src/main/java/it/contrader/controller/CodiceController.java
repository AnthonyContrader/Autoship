package it.contrader.controller;

import java.util.List;

import it.contrader.dto.CodiceDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.CodiceService;
import it.contrader.service.RobotService;

public class CodiceController implements Controller  {


private static String sub_package = "codice.";
	
	private CodiceService codiceService;
	private RobotService robotService;
	/**
	 * Costruisce un oggetto di tipo UserService per poterne usare i metodi
	 */
	public CodiceController() {
		this.codiceService = new CodiceService();
		this.robotService = new RobotService();
	}
	
	public void doControl(Request request) {
		
		
		//Estrae dalla request mode e choice
		String mode = (String) request.get("mode");
		
		String choice = (String) request.get("choice");

		//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
		int id;
		String nome;
		int dimensione;

		switch (mode) {
		
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
		// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
				case "READ":
					id = Integer.parseInt(request.get("id").toString());
					CodiceDTO codiceDTO = codiceService.read(id);
					request.put("codice", codiceDTO);
					MainDispatcher.getInstance().callView(sub_package + "CodiceRead", request);
					break;
					
				case "DELETE":
					id = Integer.parseInt(request.get("id").toString());
					int codice = codiceService.codice(id);
					robotService.spedisci(codice);
					codiceService.delete(id);
					MainDispatcher.getInstance().callView(sub_package + "CodiceDelete", request);
					break;
				

					
				//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
				case "CODICELIST":
					List<CodiceDTO> codiciDTO = codiceService.getAll();
					//Impacchetta la request con la lista degli user
					request.put("codici", codiciDTO);
					MainDispatcher.getInstance().callView("Codice", request);
					break;
					
				//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
				//con REQUEST NULL (vedi una View specifica)
				case "GETCHOICE":
							
							//toUpperCase() mette in maiuscolo la scelta
					switch (choice.toUpperCase()) {
					
					case "L":
						MainDispatcher.getInstance().callView(sub_package + "CodiceRead", null);
						break;
						
					case "S":
						MainDispatcher.getInstance().callView(sub_package + "CodiceDelete", null);
						break;
		
					case "E":
						MainDispatcher.getInstance().callView("Login", null);
						break;

					case "B":
						MainDispatcher.getInstance().callView("Codice", null);
						break;
						
					default:
						MainDispatcher.getInstance().callView("Login", null);
					}
					
				default:
					MainDispatcher.getInstance().callView("Login", null);
				}
			}

}
