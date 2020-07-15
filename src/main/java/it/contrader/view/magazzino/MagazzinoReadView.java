package it.contrader.view.magazzino;

import it.contrader.controller.Request;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.model.Oggetto;
import it.contrader.view.AbstractView;

public class MagazzinoReadView extends AbstractView{
	
	private Request request;

	private int id_oggetto;
	private int capienza;
	private int posizione;
	
	private final String mode = "READ";
	
	public MagazzinoReadView() {}
	
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			MagazzinoDTO Magazzino = (MagazzinoDTO) request.get("Magazzino");
			System.out.println(Magazzino);
			MainDispatcher.getInstance().callView("Magazzino", null);
		}
	}
	
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'oggetto altrimenti inserisci 0:");
		id_oggetto = Integer.parseInt(getInput());
		System.out.println("Inserisci la capienza della cella:");
		capienza = Integer.parseInt(getInput());
		System.out.println("Inserisci la posizione della cella:");
		posizione = Integer.parseInt(getInput());
	}
	
	@Override
	public void submit() {
		request = new Request();
		if(id_oggetto == 0) {
			request.put("id_oggetto", null);
		}
		else {
			request.put("id_oggetto", id_oggetto);
		}
		request.put("capienza", capienza);
		request.put("posizione", posizione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Magazzino", "doControl", request);
	}

}
