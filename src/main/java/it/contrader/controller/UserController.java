package it.contrader.controller;



import java.util.List;

import it.contrader.dto.UserDTO;
import it.contrader.main.MainDispatcher;
import it.contrader.service.UserService;

public class UserController implements Controller{


		private static String sub_package = "user.";
		
		private UserService userService;
		
		public UserController() {
			
			this.userService = new UserService();
		}

		public void doControl(Request request) {
			
			//Estrae dalla request mode e choice
			String mode = (String) request.get("mode");
			
			String choice = (String) request.get("choice");

			//Definisce i campi della classe (serviranno sempre, tanto vale definirli una sola volta)
			int id;
			String username;
			String password;
			int usertype;

			switch (mode) {
			
			// Arriva qui dalla UserReadView. Invoca il Service con il parametro id e invia alla UserReadView uno user da mostrare 
			case "READ":
				id = Integer.parseInt(request.get("id").toString());
				UserDTO userDTO = userService.read(id);
				request.put("user", userDTO);
				MainDispatcher.getInstance().callView(sub_package + "UserRead", request);
				break;
			
			
				
			//Arriva qui dalla UserView Invoca il Service e invia alla UserView il risultato da mostrare 
			case "USERLIST":
				List<UserDTO> usersDTO = userService.getAll();
				//Impacchetta la request con la lista degli user
				request.put("users", usersDTO);
				MainDispatcher.getInstance().callView("User", request);
				break;
				
			//Esegue uno switch sulla base del comando inserito dall'utente e reindirizza tramite il Dispatcher alla View specifica per ogni operazione
			//con REQUEST NULL (vedi una View specifica)
			case "GETCHOICE":
						
						//toUpperCase() mette in maiuscolo la scelta
				switch (choice.toUpperCase()) {
				
				case "L":
					MainDispatcher.getInstance().callView(sub_package + "UserRead", null);
					break;
					
				
					
				default:
					MainDispatcher.getInstance().callView("Login", null);
				}
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
		}
}
