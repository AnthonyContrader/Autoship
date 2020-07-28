package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.OggettoConverter;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
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
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "acquisto";
	}
	
	@GetMapping("/udpate")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		String otp = (String) request.getSession().getAttribute("otp");
		OggettoDTO oggetto = service.read(id);
		MagazzinoDTO magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
		magazzino.setOtp(otp);
		CodiceDTO codice = new CodiceDTO();
		codice.setOtp(otp);
		codiceService.insert(codice);
		setAll(request);
		return "acquisto";
	}	
	
	private void setAll(HttpServletRequest request) {
		MagazzinoDTO magazzino;
		List<OggettoDTO> list = service.getAll();
		List<OggettoDTO> dummyList = service.getAll();
		if(list != null) {
			for(OggettoDTO oggetto : dummyList) {
				magazzino = magazzinoService.findByOggetto(converter.toEntity(oggetto));
				if(magazzino == null || magazzino.getOtp() == null) {
					list.remove(oggetto);
				}
			}
		}
		request.setAttribute("list", list);
	}
}
