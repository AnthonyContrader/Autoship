package it.contrader.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.dto.OggettoDTO;
import it.contrader.service.MagazzinoService;
import it.contrader.service.OggettoService;
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
		String mode = request.getParameter("mode");
		int id;
		boolean ans;		
		
		switch (mode.toUpperCase()) {
		case "OGGETTOLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/acquisto/acquistomanager.jsp").forward(request, response);
			break;
		
		/*case "UPDATE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = 
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/acquisto/acquistomanager.jsp").forward(request, response);
			break;*/
		
			
			
		}
		
		

		
		}




}
