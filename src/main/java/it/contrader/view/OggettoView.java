package it.contrader.view;

import java.util.List;

import it.contrader.controller.Request;
import it.contrader.dto.OggettoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;

public class OggettoView extends AbstractView{
		
		private String choice;
	    
		private Request request;
		
		@Override
		public void showResults(Request request) {
			if (request != null) {
				System.out.println("\n------------------- Gestione oggetti ----------------\n");
				System.out.println("ID\tNome\tDimensione");
				System.out.println("----------------------------------------------------\n");
				
				@SuppressWarnings("unchecked")
				List<OggettoDTO> magazzino = (List<OggettoDTO>) request.get("magazzino");
				for (OggettoDTO m: magazzino)
					System.out.println(m);
				System.out.println();
			}
		}
		
		 public void showOptions()
		 {
		    	 System.out.println("-------------OGGETTO------------\n");
		         System.out.println(" Seleziona l'opzione del magazzino:");
		         System.out.println("[L]eggi [I]nserisci [M]odifica [C]ancella [B]ack [E]sci");
		         choice = this.getInput();
		 }
		 
		 public void submit() {    
		    	//crea una nuova Request (vedi classe Request)
			 	request = new Request();
				request.put("choice", choice);
				request.put("mode", "GETCHOICE");
		    	MainDispatcher.getInstance().callAction("Oggetto", "doControl", this.request);
		    }

	}

}
