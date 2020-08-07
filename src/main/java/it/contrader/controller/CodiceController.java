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

import it.contrader.converter.CodiceConverter;
import it.contrader.converter.UserConverter;
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

@RestController
@RequestMapping("/codice")
@CrossOrigin(origins = "http://localhost:4200")
public class CodiceController extends AbstractController<CodiceDTO>{

	@Autowired
	private CodiceService service;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private OggettoService oggettoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CarrelloService carrelloService;
	
	@Autowired
	private CodiceConverter codiceConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@GetMapping("/getallcodes")
	public List<String> getAllCodes(HttpServletRequest request){
		List<String> otp = new ArrayList<>();			
		List<CodiceDTO> codici = service.getAll();			
		for(CodiceDTO c : codici) {
			otp.add(c.getOtp());
		}			
		return otp;
	}
	
	@GetMapping("/getallcodesbyuser")
	public List<CodiceDTO> getAllCodesByUser(HttpServletRequest request, @RequestParam("user") Long id_user){
		UserDTO user = userService.read(id_user);
		return service.findCodicesByUser(userConverter.toEntity(user));
	}
	
	@GetMapping("/getallconfirmed")
	public List<CodiceDTO> getAllConfirmed(HttpServletRequest request){		
		List<CodiceDTO> codici = service.findByStato(CodiceStato.Confermato);					
		return codici;
	}
	
	@PostMapping("/confirm")
	public CodiceDTO confirm(@RequestBody CodiceDTO codice) {
		codice.setStato(CodiceStato.Confermato);
		service.update(codice);
		return codice;
	}
	
	@PostMapping("/send")
	public CodiceDTO send(@RequestBody CodiceDTO codice) {
		List<MagazzinoDTO> magazzinoList = magazzinoService.findMagazzinosByCodice(codiceConverter.toEntity(codice));
		for(MagazzinoDTO m : magazzinoList){
			OggettoDTO oggetto = m.getOggetto();
			m.setOggetto(null);
			m.setCodice(null);
			magazzinoService.update(m);
			oggetto.setCancellato(true);
			oggetto = oggettoService.update(oggetto);
		}
		codice.setCancellato(true);
		codice.setStato(CodiceStato.Spedito);
		service.update(codice);
		return codice;
	}
	
	@PostMapping("/deletecode")
	public void deleteCode(@RequestBody CodiceDTO codice) {
		List<CarrelloDTO> carrelloList = carrelloService.findCarrellosByCodice(codiceConverter.toEntity(codice));
		for(CarrelloDTO c : carrelloList) {
			carrelloService.delete(c.getId());
		}
		List<MagazzinoDTO> magazzinoList = magazzinoService.findMagazzinosByCodice(codiceConverter.toEntity(codice));
		for(MagazzinoDTO m : magazzinoList){
			m.setCodice(null);
			magazzinoService.update(m);
		}		
		service.delete(codice.getId());
	}
}
