package it.contrader.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.contrader.converter.CodiceConverter;
import it.contrader.converter.OggettoConverter;
import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;

@Controller
@RequestMapping("/spedizione")
public class SpedizioneController {
	
	@Autowired
	private CodiceService service;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private OggettoService oggettoService;
	
	@Autowired
	private OggettoConverter oggettoConverter;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	@GetMapping("/getall")
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "spedizione";
	}
	
	@GetMapping("/delete")
	public String delete(HttpServletRequest request, @RequestParam("id") Long id) {
		CodiceDTO codice = service.read(id);
		String otpCodice = codice.getOtp();
		List<MagazzinoDTO> magazzinoList = magazzinoService.findMagazzinosByCodice(codiceConverter.toEntity(codice));
		for(MagazzinoDTO m : magazzinoList){
			OggettoDTO oggetto = oggettoConverter.toDTO(m.getOggetto());
			m.setOggetto(null);
			m.setCodice(null);
			magazzinoService.update(m);
			oggetto.setCancellato(true);
			oggettoService.update(oggetto);
		}
		service.delete(id);
		setAll(request);
		return "spedizione";
	}
	
	private void setAll(HttpServletRequest request) {
		request.setAttribute("list", service.getAll());
	}

}
