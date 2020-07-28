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
import it.contrader.dto.MagazzinoDTO;
import it.contrader.model.Oggetto;
import it.contrader.service.MagazzinoService;

@Controller
@RequestMapping("/magazzino")
public class MagazzinoController {

	@Autowired
	private MagazzinoService service;
	
	@Autowired
	private MagazzinoConverter converter;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "magazzino";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		MagazzinoDTO dto = service.read(id);
		dto.setCancellato(true);
		service.update(dto);
		setAll(request);
		return "magazzino";
	}
	
	@GetMapping("/reinsert")
	public String reinsert(HttpServletRequest request, @RequestParam("id") Long id) {
		MagazzinoDTO dto = service.read(id);
		dto.setCancellato(false);
		service.update(dto);
		setAll(request);
		return "magazzino";
	}
	
	@GetMapping("/preupdate")
	public String preUpdate(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "updatemagazzino";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("oggetto") Oggetto oggetto,
			@RequestParam("capienza") int capienza) {

		MagazzinoDTO dto = new MagazzinoDTO();
		dto.setId(id);
		dto.setCapienza(capienza);
		dto.setOggetto(oggetto);
		dto.setCancellato(false);
		service.update(dto);
		setAll(request);
		return "magazzino";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam("capienza") int capienza,
			@RequestParam("oggetto") Oggetto oggetto) {

		MagazzinoDTO dto = new MagazzinoDTO();
		dto.setCapienza(capienza);
		dto.setOggetto(oggetto);
		dto.setCancellato(false);
		service.insert(dto);
		setAll(request);
		return "magazzino";
	}
	
	@GetMapping("/read")
	public String read(HttpServletRequest request, @RequestParam("id") Long id) {
		request.setAttribute("dto", service.read(id));
		return "readmagazzino";
	}
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", service.getAll());
	}
}
