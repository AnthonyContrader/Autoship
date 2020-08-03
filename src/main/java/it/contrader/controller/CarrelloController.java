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
import it.contrader.model.Codice.CodiceStato;
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
	private CarrelloConverter converter;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return"carrello";
	}
	
	@GetMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("action", null);
		CodiceDTO codice = codiceService.read(id);
		codice.setStato(CodiceStato.Confermato);
		codiceService.update(codice);
		setAll(request);
		return "carrello";
	}
	
	/*@GetMapping("/delete")
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
	}*/
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		CodiceDTO codiceDTO = codiceService.read(id);
		List<CarrelloDTO> carrelloList = service.findCarrellosByCodice(codiceConverter.toEntity(codiceDTO));
		for(CarrelloDTO c : carrelloList) {
			service.delete(c.getId());
		}
		List<MagazzinoDTO> magazzinoList = magazzinoService.findMagazzinosByCodice(codiceConverter.toEntity(codiceDTO));
		for(MagazzinoDTO m : magazzinoList){
			m.setCodice(null);
			magazzinoService.update(m);
		}		
		codiceService.delete(codiceDTO.getId());
		request.setAttribute("action", null);
		setAll(request);
		return "carrello";
	}
	
	@GetMapping("/getcarrello")
	public String getCarrello(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("action", "read");
		CodiceDTO codice = codiceService.read(id);
		request.setAttribute("list", service.findCarrellosByCodice(codiceConverter.toEntity(codice)));
		setAll(request);
		return "carrello";
	}
	
	@GetMapping("/deletecarrello")
	public String deleteCarrello(HttpServletRequest request, @RequestParam("id") Long id) {
		Codice codice = service.read(id).getCodice();
		MagazzinoDTO magazzino = magazzinoService.findByOggetto(service.read(id).getOggetto());
		magazzino.setCodice(null);
		magazzinoService.update(magazzino);
		service.delete(id);
		List<CarrelloDTO> carrelloList = service.findCarrellosByCodice(codice);
		if(carrelloList.isEmpty()) {
			request.setAttribute("action", null);
			codiceService.delete(codice.getId());
			setAll(request);		
		}
		else {
			setAll(request);
			request.setAttribute("codice", codice);
			request.setAttribute("action", "read");
			request.setAttribute("list", carrelloList);
		}
		return "carrello";
	}
	
	
	private void setAll(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		request.setAttribute("codiceList", codiceService.findCodicesByUser(userConverter.toEntity(user)));
	}	
}
