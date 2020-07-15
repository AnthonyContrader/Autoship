package it.contrader.view.oggetto;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class OggettoUserUpdateView extends AbstractView{
	
	private Request request;
	private int id;
	private String nome;
	private int dimensione;
	private final String mode = "UPDATE";
	
	public OggettoUserUpdateView() {
	}

	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Ordinazione andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Oggetto", null);
		}
	}
	
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci l'id dell'oggetto da ordinare:");
			id = Integer.parseInt(getInput());
		} catch (Exception e) {

		}
	}
	
	
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Oggetto", "doControl", request);
	}
}
