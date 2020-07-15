package it.contrader.view.oggetto;

import it.contrader.controller.Request;
import it.contrader.dto.OggettoDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class OggettoReadView extends AbstractView {
	private Request request;

	private int id;
	private final String mode = "READ";

	public OggettoReadView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode READ del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			OggettoDTO oggetto = (OggettoDTO) request.get("oggetto");
			System.out.println(oggetto);
			MainDispatcher.getInstance().callView("Oggetto", null);
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da visionare
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci id dell'oggetto:");
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
		MainDispatcher.getInstance().callAction("Oggetto", "doControl", request);
	}

}
