package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.CodiceConverter;
import it.contrader.converter.OggettoConverter;
import it.contrader.converter.UserConverter;
import it.contrader.dto.CarrelloDTO;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Carrello.CarrelloStato;
import it.contrader.model.Oggetto;
import it.contrader.service.CarrelloService;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {
	
	@Autowired
	private OggettoService service;
	
	@Autowired
	private OggettoConverter converter;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private CodiceService codiceService;
	
	@Autowired
	private CarrelloService carrelloService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "acquisto";
	}
	
	@GetMapping("/update")
	public String udpate(HttpServletRequest request, @RequestParam("id") Long id) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		String otp = (String) request.getSession().getAttribute("otp");
		OggettoDTO oggetto = service.read(id);
		MagazzinoDTO magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
		CodiceDTO codice = codiceService.findByOtp(otp);
		if(codice == null) {
			codice = new CodiceDTO();
			codice.setOtp(otp);
			codice.setCancellato(false);
			codice = codiceService.insert(codice);
		}
		magazzino.setCodice(codiceConverter.toEntity(codice));
		magazzinoService.update(magazzino);
		CarrelloDTO carrello = new CarrelloDTO();
		carrello.setUser(userConverter.toEntity(user));
		carrello.setOggetto(converter.toEntity(oggetto));
		carrello.setCodice(codiceConverter.toEntity(codice));
		carrello.setStato(CarrelloStato.Ordinato);
		carrelloService.insert(carrello);
		setAll(request);
		return "acquisto";
	}	
	
	private void setAll(HttpServletRequest request) {
		MagazzinoDTO magazzino;
		List<OggettoDTO> list = service.getAll();
		List<OggettoDTO> dummyList = service.getAll();
		if(list != null) {
			for(OggettoDTO oggetto : dummyList) {
				Oggetto oggettoEntity = converter.toEntity(oggetto);
				magazzino = magazzinoService.findByOggetto(oggettoEntity);
				if(magazzino == null || magazzino.getCodice() != null) {
					list.remove(oggetto);
				}
			}
		}
		request.setAttribute("list", list);
	}
}
