package it.contrader.controller;



import java.util.List;

import it.contrader.dto.OggettoDTO;
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
			
			case "INSERT":
				username = request.get("username").toString();
				password = request.get("password").toString();
				usertype = Integer.parseInt(request.get("usertype").toString());
				//costruisce l'oggetto user da inserire
				UserDTO usertoinsert = new UserDTO(username, password, usertype);
				//invoca il service
				userService.insert(usertoinsert);
				request = new Request();
				request.put("mode", "mode");
				//Rimanda alla view con la risposta
				MainDispatcher.getInstance().callView(sub_package + "UserInsert", request);
				break;
				
			case "DELETE":
				id = Integer.parseInt(request.get("id").toString());
				//Qui chiama il service
				userService.delete(id);
				request = new Request();
				request.put("mode", "mode");
				MainDispatcher.getInstance().callView(sub_package + "UserDelete", request);//parto da qui 
				break;
				
			case "UPDATE":
				id = Integer.parseInt(request.get("id").toString());
				username = request.get("username").toString();
				password = request.get("password").toString();
				usertype = Integer.parseInt(request.get("usertype").toString());
				UserDTO usertoupdate = new UserDTO(username, password, usertype);
				usertoupdate.setId(id);
				userService.update(usertoupdate);
				request = new Request();
				request.put("mode", "mode");
				MainDispatcher.getInstance().callView(sub_package + "UserUpdate", request);
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
				case "I":
					MainDispatcher.getInstance().callView(sub_package + "UserInsert", null);
					break;
					
				case "M":
					MainDispatcher.getInstance().callView(sub_package + "UserUpdate", null);
					break;
				
				case "C":
					MainDispatcher.getInstance().callView(sub_package + "UserDelete", null);
					break;
	
				case "E":
					MainDispatcher.getInstance().callView("Login", null);
					break;

				case "B":
					MainDispatcher.getInstance().callView("HomeAdmin", null);
					break;
				
					
				default:
					MainDispatcher.getInstance().callView("Login", null);
				}
				
			default:
				MainDispatcher.getInstance().callView("Login", null);
			}
		}
}
