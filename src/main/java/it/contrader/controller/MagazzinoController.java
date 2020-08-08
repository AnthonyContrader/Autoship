package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.contrader.dto.CarrelloDTO;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.dto.UserDTO;
import it.contrader.model.Codice.CodiceStato;
import it.contrader.service.CarrelloService;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;
import it.contrader.service.UserService;
import it.contrader.converter.OggettoConverter;
import it.contrader.converter.UserConverter;

@RestController
@RequestMapping("/magazzino")
@CrossOrigin(origins = "http://localhost:4200")
public class MagazzinoController extends AbstractController<MagazzinoDTO>{
	
	@Autowired
	private MagazzinoService service;
			
	@Autowired
	private OggettoService oggettoService;
	
	@Autowired
	private CodiceService codiceService;
	
	@Autowired
	private CarrelloService carrelloService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/insertmagazzino")
	public MagazzinoDTO insertMagazzino(@RequestBody JsonNode json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		MagazzinoDTO magazzino = mapper.convertValue(json.get("magazzino"), MagazzinoDTO.class);
		Long id_oggetto = mapper.convertValue(json.get("oggetto"), Long.class);
		OggettoDTO oggetto;
		if(id_oggetto != null) {
			oggetto = oggettoService.read(id_oggetto);
			int dimensione = oggetto.getDimensione();
			if(magazzino.getCapienza() < dimensione) {
				id_oggetto = (long) 0;
			}
		}
		if(id_oggetto == null || id_oggetto == 0) {
			magazzino.setOggetto(null);
		}
		else{
			oggetto = oggettoService.read(id_oggetto);
			magazzino.setOggetto(oggetto);
		}
		magazzino.setCancellato(false);
		return service.insert(magazzino);
	}
	
	@PostMapping("/updatemagazzino")
	public MagazzinoDTO updateMagazzino(@RequestBody JsonNode json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		MagazzinoDTO magazzino = mapper.convertValue(json.get("magazzino"), MagazzinoDTO.class);
		Long id_oggetto = mapper.convertValue(json.get("oggetto"), Long.class);
		OggettoDTO oggetto;
		if(id_oggetto != null) {
			oggetto = oggettoService.read(id_oggetto);
			int dimensione = oggetto.getDimensione();
			if(magazzino.getCapienza() < dimensione) {
				id_oggetto = (long) 0;
			}
		}
		if(id_oggetto == null || id_oggetto == 0) {
			magazzino.setOggetto(null);
		}
		else{
			oggetto = oggettoService.read(id_oggetto);
			magazzino.setOggetto(oggetto);
		}
		magazzino.setCancellato(false);
		return service.update(magazzino);
	}
	
	@PostMapping("/deletemagazzino")
	public MagazzinoDTO deleteMagazzino(@RequestBody MagazzinoDTO magazzino){
		magazzino.setCancellato(true);
		return service.update(magazzino);
	}
	
	@PostMapping("/reinsertmagazzino")
	public MagazzinoDTO reinsertMagazzino(@RequestBody MagazzinoDTO magazzino){
		magazzino.setCancellato(false);
		return service.update(magazzino);
	}
	
	@PostMapping("/setcodice")
	public MagazzinoDTO setCodice(@RequestBody JsonNode json){
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
			MagazzinoDTO magazzino = mapper.convertValue(json.get("magazzino"), MagazzinoDTO.class);
			String otp = mapper.convertValue(json.get("otp"), String.class);
			Long id_user = mapper.convertValue(json.get("user"), Long.class);
			UserDTO user = userService.read(id_user);
			OggettoDTO oggetto = oggettoService.read(magazzino.getOggetto().getId());
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
			service.update(magazzino);
			CarrelloDTO carrello = new CarrelloDTO();
			carrello.setOggetto(oggetto);
			carrello.setCodice(codice);
			carrelloService.insert(carrello);
			return magazzino;
	}
	
	@GetMapping("/getobjectincell")
	public List<MagazzinoDTO> getObjectInCell(HttpServletRequest request) {
		List<MagazzinoDTO> magazzinoList = service.getAll();
		List<MagazzinoDTO> dummyList = service.getAll();
		
		for(MagazzinoDTO magazzino : dummyList) {
			if(magazzino.getOggetto() == null || magazzino.getCodice() != null) {
				magazzinoList.remove(magazzino);
			}
		}
		return magazzinoList;
	}
	
	@GetMapping("/getmagazzinowithoggetto")
	public List<Long> getMagazzinoWithOggetto(HttpServletRequest request) {
		List<Long> magazzinoList = new ArrayList<Long>();
		List<MagazzinoDTO> dummyList = service.getAll();
		
		for(MagazzinoDTO magazzino : dummyList) {
			if(magazzino.getOggetto() != null || magazzino.getCodice() == null) {
				magazzinoList.add(magazzino.getId());
			}
		}
		return magazzinoList;
	}
	
}
