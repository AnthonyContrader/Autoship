package it.contrader.view.oggetto;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class OggettoInsertView extends AbstractView{
	
	private Request request;

	private String nome;
	private int dimensione;
	
	private final String mode = "INSERT";
	
	public OggettoInsertView() {}
	
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Inserimento andato a buon fine.\n");
			MainDispatcher.getInstance().callView("Oggetto", null);
		}
	}
	
	public void showOptions() {
		System.out.println("Inserisci il nome dell'oggetto:");
		nome = getInput();
		System.out.println("Inserisci la dimensione dell'oggetto:");
		dimensione = Integer.parseInt(getInput());
	}
	
	@Override
	public void submit() {
		request = new Request();
		request.put("nome", nome);
		request.put("dimensione", dimensione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Oggetto", "doControl", request);
	}

}
