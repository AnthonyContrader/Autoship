package it.contrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

	@Autowired
	private CarrelloService service;
	
	
	@Autowired
	private CarrelloConverter converter;
	
	
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		
	setAll(request) ;
	return"carrello";
	}
	
	
	private void setAll(HttpServletRequest request) {
		UserDTO userDTO =request.getSession().getAttribute("user");
		request.setAttribute("list", service.findByUser(converter.toEntity(userDTO)));
	}
	
}
