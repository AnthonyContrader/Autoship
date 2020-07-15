package it.contrader.view.oggetto;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class OggettoUserView extends AbstractView{

	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("\n------------------- Gestione oggetti ----------------\n");
			System.out.println("ID\tNome\tDimensione");
			System.out.println("----------------------------------------------------\n");
			
			@SuppressWarnings("unchecked")
			List<OggettoDTO> oggetti = (List<OggettoDTO>) request.get("oggetti");
			for (OggettoDTO o: oggetti)
				System.out.println(o);
			System.out.println();
		}
	}
	
	public void showOptions() {	}
	
	public void submit() {    
    	//crea una nuova Request (vedi classe Request)
		MainDispatcher.getInstance().callView("HomeUser", null);
    }
	
	
}
