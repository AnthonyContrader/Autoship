package it.contrader.view.magazzino;

import it.contrader.controller.Request;
import it.contrader.main.MainDispatcher;
import it.contrader.view.AbstractView;

public class MagazzinoUpdateView extends AbstractView{
	private Request request;
	private int id;
	private int id_oggetto;
	private int capienza;
	private int posizione;
	private final String mode = "UPDATE";

	public MagazzinoUpdateView() {
	}

	/**
	 * Se la request non � nulla (ovvero se si arriva dalla mode UPDATE del controller) mostra
	 * l'esito dell'operazione
	 */
	@Override
	public void showResults(Request request) {
		if (request!=null) {
			System.out.println("Modifica andata a buon fine.\n");
			MainDispatcher.getInstance().callView("Magazzino", null);
		}
	}

	/**
	 * Chiede all'utente di inserire gli attributi dell'utente da modificare
	 */
	@Override
	public void showOptions() {
		try {
			System.out.println("Inserisci l'id della cella che vuoi modificare:");
			id = Integer.parseInt(getInput());
			System.out.println("Inserisci l'id oggetto contenuta nella cella:");
			id_oggetto = Integer.parseInt(getInput());
			System.out.println("Inserisci la capienza della cella:");
			capienza = Integer.parseInt(getInput());
			System.out.println("Inserisci la posizione della cella:");
			posizione = Integer.parseInt(getInput());
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
		request.put("id_oggetto", id_oggetto);
		request.put("capienza", capienza);
		request.put("posizione", posizione);
		request.put("mode", mode);
		MainDispatcher.getInstance().callAction("Magazzino", "doControl", request);
	}

}
