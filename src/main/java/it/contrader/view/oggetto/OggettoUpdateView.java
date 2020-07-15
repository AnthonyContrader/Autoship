package it.contrader.view.oggetto;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class OggettoUpdateView extends AbstractView{
	private Request request;
	private int id;
	private String nome;
	private int dimensione;
	private final String mode = "UPDATE";

	public OggettoUpdateView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Oggetto", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci l'id dell'oggetto che vuoi modificare:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci il nome dell'oggetto:");
			nome = getInput();
			System.out.println("Inserisci la dimensione dell'oggetto:");
			dimensione = Integer.parseInt(getInput());
		} catch (Exception e) {

		}
	}


	/**
	 * Impacchetta la request con i dati inseriti nel metodo showOption()
	 */
	@Override
	public void submit() {
		request = new Request();
		request.put("id", id);
		request.put("nome", nome);
		request.put("dimensione", dimensione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Oggetto", "doControl", request);
	}

}
