package it.contrader.controller;

import java.util.List;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Oggetto;
import it.contrader.service.MagazzinoService;

/**
 * 
 * @author Vittorio
 *
 *Si osservi che nel Controller compaiono solo oggetti del DTO e non del Model!
 */
public class MagazzinoController implements Controller {

	/**
	 * definisce il pacchetto di vista user.
	 */
	private static String sub_package = "magazzino.";
	
	private MagazzinoService magazzinoService;
	/**
	 * Costruisce un oggetto di tipo UserService per poterne usare i metodi
	 */
	public MagazzinoController() {
		this.magazzinoService = new MagazzinoService();
	}
	
	
	
	/**
	 * Metodo dell'interfaccia Controller. Estrae dalla request la mode
	 * (che riceve dalle view specifiche e pu� essere la richesta di una 
	 * scelta da parte dell'utente "GETCHOICE") e la scelta dell'utente.
	 * 
	 * Se la modalit� corrisponde ad una CRUD il controller chiama i service,
	 * altrimenti rimanda alla View della CRUD per richiedere i parametri
	 */
	@Override
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
			break;
		
		// Arriva qui dalla UserInsertView. Estrae i parametri da inserire e chiama il service per inserire uno user con questi parametri
		case "INSERT":
			oggetto = (Oggetto) request.get("id_oggetto");
			capienza = (int) request.get("capienza");
			posizione = (int) request.get("posizione");
			
			//costruisce l'oggetto user da inserire
			MagazzinoDTO magazzinotoinsert = new MagazzinoDTO(oggetto, capienza, posizione);
			//invoca il service
			magazzinoService.insert(magazzinotoinsert);
			request = new Request();
			request.put("mode", "mode");
			//Rimanda alla view con la risposta
			MainDispatcher.getInstance().callView(sub_package + "MagazzinoInsert", request);
			break;
		
		// Arriva qui dalla UserDeleteView. Estrae l'id dell'utente da cancellare e lo passa al Service
		case "DELETE":
			id = Integer.parseInt(request.get("id").toString());
			//Qui chiama il service
			magazzinoService.delete(id);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "MagazzinoDelete", request);
			break;
		
		// Arriva qui dalla UserUpdateView
		case "UPDATE":
			id = Integer.parseInt(request.get("id").toString());
			oggetto = (Oggetto) request.get("id_oggetto");
			capienza = (int) request.get("capienza");
			posizione = (int) request.get("usertype");
			MagazzinoDTO magazzinotoupdate = new MagazzinoDTO(oggetto, capienza, posizione);
			magazzinotoupdate.setId(id);
			magazzinoService.update(magazzinotoupdate);
			request = new Request();
			request.put("mode", "mode");
			MainDispatcher.getInstance().callView(sub_package + "UserUpdate", request);
			break;
			
		//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
		case "MAGAZZINOLIST":
			List<MagazzinoDTO> magazzinoDTO = magazzinoService.getAll();
			//Impacchetta la request con la lista degli user
			request.put("magazzino", magazzinoDTO);
			MainDispatcher.getInstance().callView("Magazzino", request);
			break;
			
		//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
		//con REQUEST NULL (vedi una View specifica)
		case "GETCHOICE":
					
					//toUpperCase() mette in maiuscolo la scelta
			switch (choice.toUpperCase()) {
			
			/*case "L":
				MainDispatcher.getInstance().callView(sub_package + "UserRead", null);
				break;
			*/	
			case "I":
				MainDispatcher.getInstance().callView(sub_package + "MagazzinoInsert", null);
				break;
			/*	
			case "M":
				MainDispatcher.getInstance().callView(sub_package + "UserUpdate", null);
				break;
			
			case "C":
				MainDispatcher.getInstance().callView(sub_package + "UserDelete", null);
				break;
				*/
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
