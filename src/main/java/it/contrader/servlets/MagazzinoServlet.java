import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.contrader.dto.MagazzinoDTO;
import it.contrader.service.MagazzinoService;


public class MagazzinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MagazzinoServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		Service<MagazzinoDTO> service = new MagazzinoService();
		List<MagazzinoDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<MagazzinoDTO> service = new MagazzinoService();
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
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/magazzino/readmagazzino.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/magazzino/updatemagazzino.jsp").forward(request, response);
			
			break;

		case "INSERT":
			int id_oggetto = Integer.parseInt(request.getParameter("id_oggetto").toString());
			int capienza = Integer.parseInt(request.getParameter("capienza").toString());
			dto = new MagazzinoDTO (id_oggetto,capienza);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/magazzino/magazzinomanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			id_oggetto = Integer.parseInt(request.getParameter("id_oggetto"));
			capienza = Integer.parseInt(request.getParameter("capienza"));
			id = Integer.parseInt(request.getParameter("id"));
			dto = new MagazzinoDTO (id,id_oggetto,capienza);
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