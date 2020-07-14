package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class MagazzinoView extends AbstractView{
	
	private String choice;
    
	private Request request;
	
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
