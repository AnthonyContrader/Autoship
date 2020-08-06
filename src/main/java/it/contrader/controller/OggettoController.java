package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.contrader.converter.OggettoConverter;
import it.contrader.dto.CarrelloDTO;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Codice.CodiceStato;
import it.contrader.service.OggettoService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.CarrelloService;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.UserService;
import it.contrader.converter.UserConverter;

@RestController
@RequestMapping("/oggetto")
@CrossOrigin(origins = "http://localhost:4200")

public class OggettoController extends AbstractController<OggettoDTO>{
	@Autowired
	private OggettoService service;
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private OggettoConverter converter;
	@Autowired
	private CodiceService codiceService;
	
	@Autowired
	private CarrelloService carrelloService;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserService userService;
	
	@GetMapping("/getallobject")
	public List<OggettoDTO> getAllObject(HttpServletRequest request) {
		MagazzinoDTO magazzino;
		List<OggettoDTO> list = service.getAll();
		for(OggettoDTO oggetto : list) {
			magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
			if(magazzino != null) {
				oggetto.setCella(true);
			}
			else {
				oggetto.setCella(false);
			}
		}
		return list;
	}
	
	@PostMapping("/deleteoggeto")
	public OggettoDTO deleteoggetto(@RequestBody OggettoDTO oggetto){
		oggetto.setCancellato(true);
		return service.update(oggetto);
	}
	
	@PostMapping("/reinsertoggetto")
	public OggettoDTO updateoggetto(@RequestBody OggettoDTO oggetto) {
		oggetto.setCancellato(false);
		return service.update(oggetto);
	}
	
	@GetMapping("/getobjectnotincell")
	public List<OggettoDTO> getObjectNotInCell(HttpServletRequest request) {
		List<OggettoDTO> oggettoList = service.findByCancellatoFalse();
		List<OggettoDTO> dummyList = service.findByCancellatoFalse();
		MagazzinoDTO magazzino;
		
		for(OggettoDTO oggetto : dummyList) {
			magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
			if(magazzino != null) {
				oggettoList.remove(oggetto);
			}
		}
		return oggettoList;
	}

	
	@GetMapping("/getobjectincell")
	public List<OggettoDTO> getObjectInCell(HttpServletRequest request) {
		List<OggettoDTO> oggettoList = service.findByCancellatoFalse();
		List<OggettoDTO> dummyList = service.findByCancellatoFalse();
		MagazzinoDTO magazzino;
		
		for(OggettoDTO oggetto : dummyList) {
			magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
			if(magazzino == null || magazzino.getCodice() != null) {
				oggettoList.remove(oggetto);
			}
		}
		return oggettoList;
	}
	
	@PostMapping("/setcodice")
	public OggettoDTO setCodice(@RequestBody JsonNode json){
			/*ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			Long id_oggetto = mapper.convertValue(json.get("oggetto"), Long.class);
			String otp = mapper.convertValue(json.get("otp"), String.class);
			Long id_user = mapper.convertValue(json.get("user"), Long.class);
			UserDTO user = userService.read(id_user);
			OggettoDTO oggetto = service.read(id_oggetto);
			MagazzinoDTO magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
			CodiceDTO codice = codiceService.findByOtp(otp);
			if(codice == null) {
				codice = new CodiceDTO();
				codice.setOtp(otp);
				codice.setUser(user);
				codice.setStato(CodiceStato.Attesa);
				codice.setCancellato(false);
				codice = codiceService.insert(codice);
			}
			magazzino.setCodice(codice);
			magazzinoService.update(magazzino);
			CarrelloDTO carrello = new CarrelloDTO();
			carrello.setOggetto(oggetto);
			carrello.setCodice(codice);
			carrelloService.insert(carrello);
			return oggetto;*/
			
			System.out.println("Ciao");
			return null;
	}
}
