package it.contrader.controller;

import java.util.List;

import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.OggettoService;

public class OggettoController implements Controller  {


private static String sub_package = "oggetto.";
	
	private OggettoService oggettoService;
	/**
	 * Costruisce un oggetto di tipo UserService per poterne usare i metodi
	 */
	public OggettoController() {
		this.oggettoService = new OggettoService();
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
					OggettoDTO oggettoDTO = oggettoService.read(id);
					request.put("oggetto", oggettoDTO);
					MainDispatcher.getInstance().callView(sub_package + "OggettoRead", request);
					break;
				
				// Arriva qui dalla UserInsertView. Estrae i parametri da inserire e chiama il service per inserire uno user con questi parametri
				case "INSERT":
					nome = request.get("nome").toString();
					dimensione = (int) request.get("dimensione");
					
					//costruisce l'oggetto user da inserire
					OggettoDTO oggettotoinsert = new OggettoDTO(nome, dimensione);
					//invoca il service
					oggettoService.insert(oggettotoinsert);
					request = new Request();
					request.put("mode", "mode");
					//Rimanda alla view con la risposta
					MainDispatcher.getInstance().callView(sub_package + "OggettoInsert", request);
					break;
				
				// Arriva qui dalla UserDeleteView. Estrae l'id dell'utente da cancellare e lo passa al Service
				case "DELETE":
					id = Integer.parseInt(request.get("id").toString());
					//Qui chiama il service
					oggettoService.delete(id);
					request = new Request();
					request.put("mode", "mode");
					MainDispatcher.getInstance().callView(sub_package + "OggettoDelete", request);
					break;
				
				// Arriva qui dalla UserUpdateView
				case "UPDATE":
					id = Integer.parseInt(request.get("id").toString());
					nome = request.get("nome").toString();
					dimensione = (int) request.get("dimensione");
					OggettoDTO oggettotoupdate = new OggettoDTO(nome, dimensione);
					oggettotoupdate.setId(id);
					oggettoService.update(oggettotoupdate);
					request = new Request();
					request.put("mode", "mode");
					MainDispatcher.getInstance().callView(sub_package + "UserUpdate", request);
					break;
					
				//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
				case "OGGETTOLIST":
					List<OggettoDTO> oggettiDTO = oggettoService.getAll();
					//Impacchetta la request con la lista degli user
					request.put("oggetto", oggettiDTO);
					MainDispatcher.getInstance().callView("Oggetto", request);
					break;
					
				//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
				//con REQUEST NULL (vedi una View specifica)
				case "GETCHOICE":
							
							//toUpperCase() mette in maiuscolo la scelta
					switch (choice.toUpperCase()) {
					
					case "L":
						MainDispatcher.getInstance().callView(sub_package + "OggettoRead", null);
						break;
						
					case "I":
						MainDispatcher.getInstance().callView(sub_package + "OggettoInsert", null);
						break;
						
					case "M":
						MainDispatcher.getInstance().callView(sub_package + "OggettoUpdate", null);
						break;
					
					case "C":
						MainDispatcher.getInstance().callView(sub_package + "OggettoDelete", null);
						break;
		
					case "E":
						MainDispatcher.getInstance().callView("Login", null);
						break;

					case "B":
						MainDispatcher.getInstance().callView("HomeAdmin", null);
						break;
						
					default:
						MainDispatcher.getInstance().callView("Login", null);
					}
					
				default:
					MainDispatcher.getInstance().callView("Login", null);
				}
			}
}
