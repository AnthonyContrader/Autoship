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

public class MagazzinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MagazzinoServlet() {
	}

	public void updateList(HttpServletRequest request) {
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
		List<OggettoDTO> oggettiDTO = ((OggettoService) service).getNotInCell();
		request.setAttribute("oggetti", oggettiDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<MagazzinoDTO> service = new MagazzinoService();
		Service<OggettoDTO> serviceOggetto = new OggettoService();
		String mode = request.getParameter("mode");
		MagazzinoDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "MAGAZZINOLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/magazzino/magazzinomanager.jsp").forward(request, response);
			break;

		case "READ":
			getOggetti(request);
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);

			if (request.getParameter("update") == null) {
				getServletContext().getRequestDispatcher("/magazzino/readmagazzino.jsp").forward(request, response);

			}

			else
				getServletContext().getRequestDispatcher("/magazzino/updatemagazzino.jsp").forward(request, response);

			break;

		case "INSERT":
			int id_oggetto;
			if (!(request.getParameter("id_oggetto").toString().equals(""))) {
				id_oggetto = Integer.parseInt(request.getParameter("id_oggetto").toString());
			} else {
				id_oggetto = 0;
			}
			int capienza = Integer.parseInt(request.getParameter("capienza").toString());
			if(id_oggetto != 0) {
				int dimensione = ((OggettoService) serviceOggetto).dimensione(id_oggetto);
				if(capienza < dimensione) {
					id_oggetto = 0;
				}
			}
			dto = new MagazzinoDTO(id_oggetto, capienza, null);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/magazzino/magazzinomanager.jsp").forward(request, response);
			break;

		case "UPDATE":
			id = Integer.parseInt(request.getParameter("id"));
			MagazzinoDTO olddto = service.read(id);
			if (!(request.getParameter("id_oggetto").toString().trim().equals(""))) {
				if(request.getParameter("id_oggetto").toString().trim().equals("0")) {
					id_oggetto = 0;
				}
				else {
					id_oggetto = Integer.parseInt(request.getParameter("id_oggetto").toString());
				}
			}
			else {
				id_oggetto = olddto.getId_oggetto();
			}
			if (!(request.getParameter("capienza").toString().trim().equals(""))) {
				capienza = Integer.parseInt(request.getParameter("capienza"));
			}
			else {
				capienza = olddto.getCapienza();
			}			
			if(id_oggetto != 0) {
				int dimensione = ((OggettoService) serviceOggetto).dimensione(id_oggetto);
				if(capienza < dimensione) {
					id_oggetto = 0;
				}
			}
			int checkOggetto = ((MagazzinoService) service).checkOggetto(id_oggetto);
			if(checkOggetto > 0) {
				id_oggetto = olddto.getId_oggetto();
			}
			dto = new MagazzinoDTO(id, id_oggetto, capienza, null);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/magazzino/magazzinomanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/magazzino/magazzinomanager.jsp").forward(request, response);
			break;
		}
	}
}