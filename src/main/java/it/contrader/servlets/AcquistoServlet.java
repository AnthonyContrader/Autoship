package it.contrader.servlets;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.CodiceDTO;
import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.service.CodiceService;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;
import it.contrader.service.RobotService;
import it.contrader.service.Service;

public class AcquistoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public AcquistoServlet() {
	}
	
	
	public void updateList(HttpServletRequest request) {
		Service <OggettoDTO> service = new OggettoService();
		List<OggettoDTO>listDTO = ((OggettoService) service).getAllInCell();
		request.setAttribute("list", listDTO);
	}

 public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<OggettoDTO> service = new OggettoService();
		Service <MagazzinoDTO> magazzinoService = new MagazzinoService();
		Service <CodiceDTO> codiceService = new CodiceService();
		RobotService robotService = new RobotService();
		String mode = request.getParameter("mode");
		int id;
		boolean ans;		
		
		switch (mode.toUpperCase()) {
		case "OGGETTOLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/acquisto/acquistomanager.jsp").forward(request, response);
			break;
		
		case "UPDATE":
			id = Integer.parseInt(request.getParameter("id"));
			int checkCodice;
			try {
				checkCodice = ((MagazzinoService) magazzinoService).checkCodice(id);
				if(checkCodice == -1) {
					 byte[] array = new byte[5]; // length is bounded by 7
					 new Random().nextBytes(array);
					 String codice = new String(array, Charset.forName("UTF-8"));
					 CodiceDTO codicetoinsert = new CodiceDTO(codice);
					 codiceService.insert(codicetoinsert);
					 robotService.createCode(codice, id);
					 ans = codiceService.update(codicetoinsert);
					 request.setAttribute("ans", ans);
					 updateList(request);
					 getServletContext().getRequestDispatcher("/acquisto/acquistomanager.jsp").forward(request, response);
				}
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
			
			
		}
		
		

		
		}




}
