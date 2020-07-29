package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.dto.CodiceDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.User.Usertype;
import it.contrader.service.CodiceService;
import it.contrader.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private CodiceService codiceService;

	@PostMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		UserDTO userDTO = service.findByUsernameAndPassword(username, password);
		if(userDTO != null) {
			request.getSession().setAttribute("user", userDTO);
			String codice;
			byte[] array;
			List<String> otp;
			switch (userDTO.getUsertype()) {
			
			case SUPERUSER:
				array = new byte[5]; // length is bounded by 7
				otp = getAllCodes();
				new Random().nextBytes(array);
				codice = Integer.toString((int)(Math.random() * (5000 - 1000)));
				while(otp.contains(codice)) {
					new Random().nextBytes(array);
					codice = Integer.toString((int)(Math.random() * (5000 - 1000)));
				}
				request.getSession().setAttribute("otp", codice);
				return "homesuperuser";
			
			case ADMIN:
				return "homeadmin";
		
			case USER:
				array = new byte[5]; // length is bounded by 7
				otp = getAllCodes();
				new Random().nextBytes(array);
				codice = Integer.toString((int)(Math.random() * (5000 - 1000)));
				while(otp.contains(codice)) {
					new Random().nextBytes(array);
					codice = Integer.toString((int)(Math.random() * (5000 - 1000)));
				}
				request.getSession().setAttribute("otp", codice);
				return "homeuser";
					
			case CORRIERE:
				return "homecorriere";
		
			default:
			return "index";
			}
		}
		else {
			return "index";
		}
	}

	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "users";
	}

	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		service.delete(id);
		setAll(request);
		return "users";
	}

	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "updateuser";
	}

	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {

		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.update(dto);
		setAll(request);
		return "users";

	}

	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("usertype") Usertype usertype) {
		UserDTO dto = new UserDTO();
		dto.setUsername(username);
		dto.setPassword(password);
		dto.setUsertype(usertype);
		service.insert(dto);
		setAll(request);
		return "users";
	}

	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.getSession().setAttribute("dto", service.read(id));
		return "readuser";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
	
	private List<String> getAllCodes() {
		List<String> otp = new ArrayList<>();
		
		List<CodiceDTO> codici = codiceService.getAll();
		
		for(CodiceDTO c : codici) {
			otp.add(c.getOtp());
		}
		
		return otp;
	}
}
