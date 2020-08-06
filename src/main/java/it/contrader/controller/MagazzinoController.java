package it.contrader.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import it.contrader.converter.OggettoConverter;

@RestController
@RequestMapping("/magazzino")
@CrossOrigin(origins = "http://localhost:4200")
public class MagazzinoController extends AbstractController<MagazzinoDTO>{
	
	@Autowired
	private MagazzinoService service;
	
	@Autowired
	private OggettoService oggettoService;
	
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
	

	
}
