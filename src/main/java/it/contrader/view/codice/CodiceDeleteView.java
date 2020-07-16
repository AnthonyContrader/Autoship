package it.contrader.view.codice;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CodiceDeleteView extends AbstractView{
	
	private Request request;

	private int id;
	private final String mode = "DELETE";

	public CodiceDeleteView() {
	}

	@Override
	public void showResults(Request request) {
		if (request != null) {
			System.out.println("Spedizione andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Codice", null);
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da visionare
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci id del codice che si intende spedire: ");
			id = Integer.parseInt(getInput());

	}

	/**
	 * impacchetta la request con l'id dell'utente da vedere
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Codice", "doControl", request);
	}
}
