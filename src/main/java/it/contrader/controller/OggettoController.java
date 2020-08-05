package it.contrader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.contrader.dto.OggettoDTO;
import it.contrader.service.OggettoService;

@RestController
@RequestMapping("/oggetto")
@CrossOrigin(origins = "http://localhost:4200")

public class OggettoController extends AbstractController<OggettoDTO>{
	//@Autowired
	//private OggettoService oggettoService;

}
