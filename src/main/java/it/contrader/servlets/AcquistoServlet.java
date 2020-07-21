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
	
	
	public void updateList(HttpServletRequest request) {//aggiorno la lista 
		Service<MagazzinoDTO> service = new MagazzinoService();
		List<MagazzinoDTO> listDTO = service.getAll();
		Service<OggettoDTO> serviceOggetto = new OggettoService();
		String nomeOggetto = "";
		for(MagazzinoDTO m: listDTO) {
			nomeOggetto = ((OggettoService) serviceOggetto).nome(m.getId_oggetto());
			if(nomeOggetto != null) {
				m.setNome_oggetto(nomeOggetto);
			}
			else {
				m.setNome_oggetto("Vuoto");
			}
		}
		request.setAttribute("list", listDTO);
	}

 public void getOggetti(HttpServletRequest request) {
	Service<OggettoDTO> service = new OggettoService();
	List<OggettoDTO> oggettiDTO = service.getAll();
	request.setAttribute("oggetti", oggettiDTO);
  }
 
 public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<MagazzinoDTO> service = new MagazzinoService();
		String mode = request.getParameter("mode");
		MagazzinoDTO dto;
		int id;
		boolean ans;
		
		
		switch (mode.toUpperCase()) {
		case "UPDATE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = ((MagazzinoService) service).removeOggetto(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/magazzino/readoggetto.jsp").forward(request, response);
			break;
		
			
			
		}
		
		

		
		}




}
