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

import it.contrader.converter.OggettoConverter;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.service.OggettoService;
import it.contrader.service.MagazzinoService;

@RestController
@RequestMapping("/oggetto")
@CrossOrigin(origins = "http://localhost:4200")

public class OggettoController extends AbstractController<OggettoDTO>{
	@Autowired
	private OggettoService service;
	@Autowired
	private MagazzinoService magazzinoservice;
	
	@Autowired
	private OggettoConverter oggettoConverter;
	
	
	
	@PostMapping("/updateoggetto")
	public OggettoDTO updateoggetto(@RequestBody OggettoDTO oggetto) {
		oggetto.setCancellato(false);
		return service.update(oggetto);
	}
	
	@PostMapping("/deleteoggeto")
	public OggettoDTO deleteoggetto(@RequestBody OggettoDTO oggetto){
		oggetto.setCancellato(true);
		return service.update(oggetto);
	}
	@GetMapping("/getobjectnotincell")
	public List<OggettoDTO> getObjectNotInCell(HttpServletRequest request) {
		List<OggettoDTO> oggettoList = service.findByCancellatoFalse();
		List<OggettoDTO> dummyList = service.findByCancellatoFalse();
		MagazzinoDTO magazzino;
		
		for(OggettoDTO oggetto : dummyList) {
			magazzino = magazzinoservice.findByOggetto(oggettoConverter.toEntity(oggetto));
			if(magazzino != null) {
				oggettoList.remove(oggetto);
			}
		}
		return oggettoList;
	}

}
