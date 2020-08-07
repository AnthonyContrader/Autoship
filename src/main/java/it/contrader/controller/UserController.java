package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.converter.CodiceConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dto.CarrelloDTO;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.LoginDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.CarrelloService;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
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
	
	@Autowired
	private CodiceService codiceService;
	
	@Autowired
	private CarrelloService carrelloService;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private CodiceConverter codiceConverter;


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
	
	@DeleteMapping("/deleteuser")
	public void deleteUser(@RequestParam("id") Long id_user) {
		UserDTO user = userService.read(id_user);
		List<CarrelloDTO> carrelloList;
		List<MagazzinoDTO> magazzinoList;
		List<CodiceDTO> codiceList = codiceService.findCodicesByUser(userConverter.toEntity(user));
		for(CodiceDTO codice : codiceList) {
			carrelloList = carrelloService.findCarrellosByCodice(codiceConverter.toEntity(codice));
			magazzinoList = magazzinoService.findMagazzinosByCodice(codiceConverter.toEntity(codice));
			for(CarrelloDTO c : carrelloList) {
				c.setCodice(null);
				carrelloService.delete(c.getId());
			}
			for(MagazzinoDTO m : magazzinoList){
				m.setCodice(null);
				magazzinoService.update(m);
			}
			codiceService.delete(codice.getId());
		}
		userService.delete(id_user);

	}
}