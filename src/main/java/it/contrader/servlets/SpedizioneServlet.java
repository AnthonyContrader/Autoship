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
import it.contrader.dto.OggettoDTO;
import it.contrader.service.CodiceService;
import it.contrader.service.RobotService;
import it.contrader.service.Service;

public class SpedizioneServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public SpedizioneServlet() {
		
	}
	
	public void updateList(HttpServletRequest request) {
		Service <CodiceDTO> service = new CodiceService();
		List<CodiceDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service <CodiceDTO> codiceService = new CodiceService();
		RobotService robotService = new RobotService();
		String mode = request.getParameter("mode");
		int id;		
		
		switch (mode.toUpperCase()) {
		case "CODICELIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/ordini/codicemanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			id = Integer.parseInt(request.getParameter("id"));
			String codice = ((CodiceService) codiceService).codice(id);
			robotService.spedisci(codice);
			codiceService.delete(id);
			updateList(request);
			getServletContext().getRequestDispatcher("/ordini/codicemanager.jsp").forward(request, response);
			break;
		
			
			
		}
		
		

		
		}

}
