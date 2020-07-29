package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.contrader.dto.UserDTO;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

	@Autowired
	private CarrelloService service;
	
	
	@Autowired
	private CarrelloConverter converter;
	
	
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return"carrello";
	}
	
	
	private void setAll(HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
		request.setAttribute("list", service.findByUser(converter.toEntity(userDTO)));
	}	
}
