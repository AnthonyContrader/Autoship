package it.contrader.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/acquisto")
public class AcquistoController {
	
	@Autowired
	private OggettoService service;
	
	@Autowired
	private MagazzinoService magazzinoService;
	
	@Autowired
	private RobotService robotService;
	
	@Autowired
	private CodiceService codiceService;
	
	
	public void updateList(HttpServletRequest request) {
		Service <OggettoDTO> service = new OggettoService();
		List<OggettoDTO>listDTO = ((OggettoService) service).getAllInCellOrdered();
		request.setAttribute("list", listDTO);
	}
	
	@GetMapping("/oggettolist")
	public String oggettolist(HttpServletRequest request, @RequestParam("id") Long id) {
		updateList(request);
		request.setAttribute("otp", codice);
		return "acquistomanager";
	}
	
	
	@GetMapping("/update")
	public String oggettolist(HttpServletRequest request, @RequestParam("id") Long id) {
		int id = Integer.parseInt(request.getParameter("id"));
		int checkCodice;
		try {
			checkCodice = ((MagazzinoService) magazzinoService).checkCodice(id);
			if(checkCodice == -1) {
				 CodiceDTO codicetoinsert = new CodiceDTO(codice);
				if(((CodiceService) codiceService).getCodice(codice) == -1) {
					 codiceService.insert(codicetoinsert);
				}
				 robotService.createCode(codice, id);
				boolean ans = codiceService.update(codicetoinsert);
				 request.setAttribute("ans", ans);
				 updateList(request);
				 request.setAttribute("otp", codice);
				 return "acquistomanager";
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}
