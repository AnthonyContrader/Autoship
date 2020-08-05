package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;

@RestController
@RequestMapping("/magazzino")
@CrossOrigin(origins = "http://localhost:4200")
public class MagazzinoController extends AbstractController<MagazzinoDTO>{
	
	@Autowired
	private MagazzinoService service;
	
	@Autowired
	private OggettoService oggettoService;
	
	@PostMapping("/insertmagazzino")
	public MagazzinoDTO insertMagazzino(@RequestBody MagazzinoDTO magazzino) {
		OggettoDTO oggetto;
		if(magazzino.getOggetto() != null) {
			oggetto = oggettoService.read(magazzino.getOggetto().getId());
			int dimensione = oggetto.getDimensione();
			if(magazzino.getCapienza() < dimensione) {
				magazzino.setCapienza(0);
			}
		}
		magazzino.setCancellato(false);
		return service.insert(magazzino);
	}
	
	@PostMapping("/updatemagazzino")
	public MagazzinoDTO updateMagazzino(@RequestBody MagazzinoDTO magazzino) {
		OggettoDTO oggetto;
		if(magazzino.getOggetto() != null) {
			oggetto = oggettoService.read(magazzino.getOggetto().getId());
			int dimensione = oggetto.getDimensione();
			if(magazzino.getCapienza() < dimensione) {
				magazzino.setCapienza(0);
			}
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
