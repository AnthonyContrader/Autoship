package it.contrader.view;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeCorriereView extends AbstractView{
	 private Request request;
	 String choice;
	@Override
	public void showResults(Request request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showOptions() {
        System.out.println("-------------MENU------------\n");
        System.out.println(" Seleziona cosa vuoi gestire:");
        System.out.println(" [C]odici [E]sci");
        //Il metodo che salva l'input nella stringa choice.
        //getInput() � definito in AbstractView.
        choice = this.getInput();
    }

	public void submit() {    
    	//crea una nuova Request (vedi classe Request)
    	request = new Request();
        switch (choice) {
       
        case "c":
        	this.request.put("mode", "CODICELIST");
        	MainDispatcher.getInstance().callAction("Codice", "doControl", request);
        	break;
 
        case "e":
        	MainDispatcher.getInstance().callAction("Login", "doControl", null);
        	break;

        default:        	
            request.put("choice", choice);
        	MainDispatcher.getInstance().callAction("Login", "doControl", request);
        }
    }

}
