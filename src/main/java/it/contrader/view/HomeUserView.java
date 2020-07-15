package it.contrader.view;


import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;

public class HomeUserView extends AbstractView{
    private Request request;
	String choice;

	@Override
	public void showResults(Request request) {
		System.out.println("");

	}

	@Override

	public void showOptions() {
        System.out.println("-------------MENU------------\n");
        System.out.println("Seleziona cosa vuoi gestire:");
        System.out.println("[V]isualizza [O]rdina [E]sci");
        //Il metodo che salva l'input nella stringa choice.
        //getInput() è definito in AbstractView.
        choice = this.getInput();
    }

	 public void submit() {    
	    	//crea una nuova Request (vedi classe Request)
	    	request = new Request();
	        switch (choice) {
	       
	        case "v":
	        	this.request.put("mode", "OGGETTOLIST");
	        	MainDispatcher.getInstance().callAction("OggettoUser", "doControl", request);
	        	break;
	        	
	        case "o":
	        	this.request.put("mode", "ORDINATE");
	        	MainDispatcher.getInstance().callAction("OggettoUser", "doControl", request);
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
