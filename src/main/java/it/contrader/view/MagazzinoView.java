package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;

public class MagazzinoView extends AbstractView{
	
	private String choice;
    
	private Request request;
	
	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- Gestione magazzino ----------------\n");
			System.out.println("ID\tID Oggetto\tCapienza\tPosizione");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<UserDTO> magazzino = (List<UserDTO>) request.get("magazzino");
			for (UserDTO m: magazzino)
				System.out.println(m);
			System.out.println();
		}
	}
	
	 public void showOptions()
	 {
	    	 System.out.println("-------------MAGAZZINO------------\n");
	         System.out.println(" Seleziona l'opzione del magazzino:");
	         System.out.println("[C]rea");
	         choice = this.getInput();
	 }
	 
	 public void submit() {    
	    	//crea una nuova Request (vedi classe Request)
	    	request = new Request();
	    	switch (choice) {
	    	case "c":
	    		MainDispatcher.getInstance().callAction("Magazzino", "doControl", request);
	    		default:
	    			showOptions();
	    	}
	    }

}
