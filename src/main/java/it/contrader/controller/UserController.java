package it.contrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.UserService;


/**
 * 
 * Questa classe estende AbstractController con tipo UserDTO.
 * In aggiunta ai metodi di CRUD si implementa il metodo di login.
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 * @param<UserDTO>
 * 
 * @see AbstractController
 *
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends AbstractController<UserDTO>{
	
	@Autowired
	private UserService userService;


	//POST Angular a UserDTO
	@PostMapping(value = "/login")
	public UserDTO login( @RequestBody LoginDTO loginDTO ) {
		return userService.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
	}
	
	@GetMapping(value = "/getalladmin")
	public List<UserDTO> getAllAdmin() {
		List<UserDTO> userList = userService.getAll();
		List<UserDTO> dummyList = userService.getAll();
		
		for(UserDTO user: dummyList) {
			if(user.getUsertype().equals(Usertype.SUPERUTENTE)){
				userList.remove(user);
			}
		}
		return userList;
	}
}