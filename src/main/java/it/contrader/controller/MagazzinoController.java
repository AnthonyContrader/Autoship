package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.MagazzinoConverter;
import it.contrader.converter.OggettoConverter;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.model.Oggetto;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;

@Controller
@RequestMapping("/magazzino")
public class MagazzinoController {

	@Autowired
	private MagazzinoService service;
	
	@Autowired
	private MagazzinoConverter converter;
	
	@Autowired
	private OggettoService oggettoService;
	
	@Autowired
	private OggettoConverter oggettoConverter;
	
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		request.setAttribute("action", null);
		getObjectNotInCell(request);
		return "magazzino";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		MagazzinoDTO dto = service.read(id);
		dto.setCancellato(true);
		service.update(dto);
		request.setAttribute("action", null);
		setAll(request);
		getObjectNotInCell(request);
		return "magazzino";
	}
	
	@GetMapping("/reinsert")
	public String reinsert(HttpServletRequest request, @RequestParam("id") Long id) {
		MagazzinoDTO dto = service.read(id);
		dto.setCancellato(false);
		service.update(dto);
		request.setAttribute("action", null);
		setAll(request);
		getObjectNotInCell(request);
		return "magazzino";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		request.setAttribute("action", "update");
		setAll(request);
		getObjectNotInCell(request);
		return "magazzino";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("id_oggetto") Long id_oggetto,
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
			dto.setOggetto(oggettoConverter.toEntity(oggetto));
		}
		dto.setCancellato(false);
		service.update(dto);
		request.setAttribute("action", null);
		getObjectNotInCell(request);
		setAll(request);
		return "magazzino";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("capienza") int capienza, @RequestParam("id_oggetto") Long id_oggetto) {		
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
			dto.setOggetto(oggettoConverter.toEntity(oggetto));
		}
		dto.setCancellato(false);
		service.insert(dto);
		request.setAttribute("action", null);
		getObjectNotInCell(request);
		setAll(request);
		return "magazzino";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "readmagazzino";
	}
	
	private void setAll(HttpServletRequest request) {
		request.setAttribute("list", service.getAll());
	}
	
	private void getObjectNotInCell(HttpServletRequest request) {
		List<OggettoDTO> oggettoList = oggettoService.findByCancellatoFalse();
		List<OggettoDTO> dummyList = oggettoService.findByCancellatoFalse();
		MagazzinoDTO magazzino;
		
		for(OggettoDTO oggetto : dummyList) {
			magazzino = service.findByOggetto(oggettoConverter.toEntity(oggetto));
			if(magazzino != null) {
				oggettoList.remove(oggetto);
			}
		}
		
		request.setAttribute("oggetti", oggettoList);
	}
}
