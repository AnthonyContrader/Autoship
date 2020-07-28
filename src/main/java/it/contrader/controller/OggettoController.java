package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.OggettoConverter;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;

@Controller
@RequestMapping("/oggetto")
public class OggettoController {
	
	@Autowired
	private OggettoService service;
	
	@Autowired
	private OggettoConverter converter;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "oggetto";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		OggettoDTO dto = service.read(id);
		dto.setCancellato(true);
		service.update(dto);
		setAll(request);
		return "oggetto";
	}
	
	@GetMapping("/reinsert")
	public String reinsert(HttpServletRequest request, @RequestParam("id") Long id) {
		OggettoDTO dto = service.read(id);
		dto.setCancellato(false);
		service.update(dto);
		setAll(request);
		return "oggetto";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "updateoggetto";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("nome") String nome,
			@RequestParam("dimensione") int dimensione) {

		OggettoDTO dto = new OggettoDTO();
		dto.setId(id);
		dto.setNome(nome);
		dto.setDimensione(dimensione);
		dto.setCancellato(false);
		service.update(dto);
		setAll(request);
		return "oggetto";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("nome") String nome,
			@RequestParam("dimensione") int dimensione) {

		OggettoDTO dto = new OggettoDTO();
		dto.setNome(nome);
		dto.setDimensione(dimensione);
		dto.setCancellato(false);
		service.insert(dto);
		setAll(request);
		return "oggetto";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "readoggetto";
	}
	
	private void setAll(HttpServletRequest request) {
		List<OggettoDTO> list = service.getAll();
		for(OggettoDTO oggetto : list) {
			MagazzinoDTO magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
			if(magazzino != null) {
				oggetto.setCella(true);
			}
			else {
				oggetto.setCella(false);
			}
		}
		request.setAttribute("list", service.getAll());
	}

}
