package view.codice;

import it.contrader.controller.Request;
import it.contrader.dto.CodiceDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class CodiceReadView extends AbstractView {
	private Request request;

	private int id;
	private final String mode = "READ";

	public CodiceReadView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode READ del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request != null) {
			CodiceDTO codice = (CodiceDTO) request.get("codice");
			System.out.println(codice);
			MainDispatcher.getInstance().callView("Codice", null);
		}
	}

	/**
	 * Chiede all'utente di inserire l'id dell'utente da visionare
	 */
	@Override
	public void showOptions() {
			System.out.println("Inserisci id del codice:");
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
