package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.CarrelloConverter;
import it.contrader.converter.CodiceConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dto.CarrelloDTO;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Codice;
import it.contrader.model.Magazzino;
import it.contrader.service.CarrelloService;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;

@Controller
@RequestMapping("/carrello")
public class CarrelloController {

	@Autowired
	private CarrelloService service;
	
	@Autowired
	private CodiceService codiceService;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return"carrello";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		CarrelloDTO carrello = service.read(id);
		MagazzinoDTO magazzino = magazzinoService.findByOggettoAndCodice(carrello.getOggetto(), carrello.getCodice());
		CodiceDTO codice = codiceService.read(carrello.getCodice().getId());
		service.delete(id);
		magazzino.setCodice(null);
		magazzinoService.update(magazzino);
		List<MagazzinoDTO> magazzinoList = magazzinoService.findMagazzinosByCodice(codiceConverter.toEntity(codice));
		if(magazzinoList.isEmpty()) {
			codice.setCancellato(true);
			codiceService.update(codice);
		}
		setAll(request);
		return"carrello";
	}
	
	
	private void setAll(HttpServletRequest request) {
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
		request.setAttribute("list", service.findCarrellosByUser(userConverter.toEntity(userDTO)));
	}	
}
