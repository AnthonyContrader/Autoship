package it.contrader.controller;

import it.contrader.main.MainDispatcher;
import it.contrader.service.LoginService;

public class HomeController implements Controller {

	private LoginService loginService;
/**
 * Istanzia un oggetto di classe LoginService tramite il costruttore della classe
 */
	public HomeController() {
		loginService = new LoginService();
	}

	/**
	 * Se la request non � nulla la spacchetta estraendo i valori relativi alle chiavi "usename" e "password". Quindi chiama il Login Service 
	 * e ottiene uno usertype dal database. Se non trova le credenziali rimanda alla Login View-
	 */
	public void doControl(Request request) {
		if (request != null) {
			
			String username = request.get("username").toString();
			String password = request.get("password").toString();

			// Qui invoca il Login Service
			int usertype= loginService.login(username, password);

			// Reindirizza alla giusta view in base allo usertype
			switch(usertype) {
			
			case 1:
				MainDispatcher.getInstance().callView("HomeAdmin", request);
				break;
				
			case 2: 
				MainDispatcher.getInstance().callView("HomeUser", request);
				break;
				
			case 3:
				MainDispatcher.getInstance().callView("HomeCorriere", request);
				break;
			
			default:
				 MainDispatcher.getInstance().callView("Login", null);
				 break;
			}
		}
		else MainDispatcher.getInstance().callView("Login", null);

	}
}
