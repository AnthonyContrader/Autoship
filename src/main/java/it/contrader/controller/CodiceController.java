package it.contrader.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.CodiceDTO;
import it.contrader.service.CodiceService;

@RestController
@RequestMapping("/codice")
@CrossOrigin(origins = "http://localhost:4200")
public class CodiceController extends AbstractController<CodiceDTO>{

	@Autowired
	private CodiceService service;
	
	@GetMapping("/getallcodes")
	public List<String> getAllCodes(HttpServletRequest request){
		List<String> otp = new ArrayList<>();
			
		List<CodiceDTO> codici = service.getAll();
			
		for(CodiceDTO c : codici) {
			otp.add(c.getOtp());
		}
			
		return otp;
	}
	
}
