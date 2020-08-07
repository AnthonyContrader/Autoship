package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.converter.CodiceConverter;
import it.contrader.dto.CarrelloDTO;
import it.contrader.dto.CodiceDTO;
import it.contrader.service.CarrelloService;
import it.contrader.service.CodiceService;

@RestController
@RequestMapping("/carrello")
@CrossOrigin(origins = "http://localhost:4200")
public class CarrelloController extends AbstractController<CarrelloDTO>{

	@Autowired
	private CarrelloService service;
	
	@Autowired
	private CodiceService codiceService;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	@GetMapping("/getcarrello")
	public List<CarrelloDTO> getCarrello(HttpServletRequest request, @RequestParam("otp") String otp) {
		CodiceDTO codice = codiceService.findByOtp(otp);
		return service.findCarrellosByCodice(codiceConverter.toEntity(codice));
	}
	
}
