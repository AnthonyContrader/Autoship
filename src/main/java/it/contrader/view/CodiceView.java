package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.CodiceDTO;
import it.contrader.main.MainDispatcher;

public class CodiceView extends AbstractView{
	private String choice;
    
	private Request request;
	
	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- Gestione Codice ----------------\n");
			System.out.println("ID\tCodice");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<CodiceDTO> codice = (List<CodiceDTO>) request.get("Codice");
			for (CodiceDTO c: codice)
				System.out.println(c);
			System.out.println();
		}
	}
	
	 public void showOptions()
	 {
	    	 System.out.println("-------------Codice------------\n");
	         System.out.println(" Seleziona l'opzione degli oggetti:");
	         System.out.println("[L]eggi [S]eleziona [C]ancella [B]ack [E]sci");
	         choice = this.getInput();
	 }
	 
	 public void submit() {    
	    	//crea una nuova Request (vedi classe Request)
		 	request = new Request();
			request.put("choice", choice);
			request.put("mode", "GETCHOICE");
	    	MainDispatcher.getInstance().callAction("Codice", "doControl", this.request);
	    }
}
