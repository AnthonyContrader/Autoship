package it.contrader.view.magazzino;

import it.contrader.controller.Request;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class MagazzinoReadView extends AbstractView{
	
	private Request request;

	private int id;
	private final String mode = "READ";
	
	public MagazzinoReadView() {}
	
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			MagazzinoDTO magazzino = (MagazzinoDTO) request.get("magazzino");
			System.out.println(magazzino);
			MainDispatcher.getInstance().callView("Magazzino", null);
		}
	}
	
	public void showOptions() {
		System.out.println("Inserisci l'ID dell'oggetto altrimenti inserisci 0:");
		id = Integer.parseInt(getInput());
	}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Magazzino", "doControl", request);
	}

}
