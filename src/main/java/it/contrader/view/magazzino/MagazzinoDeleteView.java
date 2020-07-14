package it.contrader.view.magazzino;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class MagazzinoDeleteView extends AbstractView{
	
	private Request request;

	private int capienza;
	private int posizione;
	
	private final String mode = "INSERT";
	
	public MagazzinoDeleteView() {}
	
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("User", null);
		}
	}
	
	public void showOptions() {
		System.out.println("Inserisci la capienza della cella:");
		capienza = Integer.parseInt(getInput());
		System.out.println("Inserisci la posizione della cella:");
		posizione = Integer.parseInt(getInput());
	}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("id_oggetto", null);
		request.put("capienza", capienza);
		request.put("posizione", posizione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Magazzino", "doControl", request);
	}

}
