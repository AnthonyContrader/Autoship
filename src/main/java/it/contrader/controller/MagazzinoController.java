package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
	public MagazzinoDTO insertMagazzino(@RequestParam("id_oggetto") Long id_oggetto,
			@RequestParam("capienza") int capienza) {
		OggettoDTO oggetto;
		if(id_oggetto != 0) {
			oggetto = oggettoService.read(id_oggetto);
			int dimensione = oggetto.getDimensione();
			if(capienza < dimensione) {
				id_oggetto = (long) 0;
			}
		}
		MagazzinoDTO dto = new MagazzinoDTO();
		dto.setCapienza(capienza);
		if(id_oggetto == 0) {
			dto.setOggetto(null);
		}
		else {
			oggetto = oggettoService.read(id_oggetto);
			dto.setOggetto(oggetto);
		}
		dto.setCancellato(false);
		return service.insert(dto);
	}
	
	@PostMapping("/updatemagazzino")
	public MagazzinoDTO updateMagazzino(@RequestParam("id") Long id, @RequestParam("id_oggetto") Long id_oggetto,
			@RequestParam("capienza") int capienza) {
		OggettoDTO oggetto;
		if(id_oggetto != 0) {
			oggetto = oggettoService.read(id_oggetto);
			int dimensione = oggetto.getDimensione();
			if(capienza < dimensione) {
				id_oggetto = (long) 0;
			}
		}
		MagazzinoDTO dto = new MagazzinoDTO();
		dto.setId(id);
		dto.setCapienza(capienza);
		if(id_oggetto == 0) {
			dto.setOggetto(null);
		}
		else {
			oggetto = oggettoService.read(id_oggetto);
			dto.setOggetto(oggetto);
		}
		dto.setCancellato(false);
		return service.update(dto);
	}
	
	@PostMapping("/deletemagazzino")
	public MagazzinoDTO deleteMagazzino(@RequestParam("id") Long id, @RequestParam("capienza") int capienza, @RequestParam("id_oggetto") Long id_oggetto){
		MagazzinoDTO dto = service.read(id);
		dto.setCancellato(true);
		return service.update(dto);
	}
	
	@PostMapping("/reinsertmagazzino")
	public MagazzinoDTO reinsertMagazzino(@RequestParam("id") Long id, @RequestParam("capienza") int capienza, @RequestParam("id_oggetto") Long id_oggetto){
		MagazzinoDTO dto = service.read(id);
		dto.setCancellato(false);
		return service.update(dto);
	}
	
}
