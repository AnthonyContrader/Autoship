import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OggettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OggettoServlet() {
	}
	
	public void updateList(HttpServletRequest request) {
		Service<OggettoDTO> service = new OggettoService();
		List<OggettoDTO>listDTO = service.getAll();
		request.setAttribute("list", listDTO);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service<OggettoDTO> service = new OggettoService();
		String mode = request.getParameter("mode");
		OggettoDTO dto;
		int id;
		boolean ans;

		switch (mode.toUpperCase()) {

		case "OGGETTOLIST":
			updateList(request);
			getServletContext().getRequestDispatcher("/oggetto/oggettomanager.jsp").forward(request, response);
			break;

		case "READ":
			id = Integer.parseInt(request.getParameter("id"));
			dto = service.read(id);
			request.setAttribute("dto", dto);
			
			if (request.getParameter("update") == null) {
				 getServletContext().getRequestDispatcher("/oggetto/readoggetto.jsp").forward(request, response);
				
			}
			
			else getServletContext().getRequestDispatcher("/oggetto/updateoggetto.jsp").forward(request, response);
			
			break;

		case "INSERT":
			String nome = request.getParameter("nome").toString();
			int dimensione = Integer.parseInt(request.getParameter("dimensione").toString());
			int cancellato = Integer.parseInt(request.getParameter("cancellato").toString());
			dto = new OggettoDTO (nome,dimensione,cancellato);
			ans = service.insert(dto);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/oggetto/oggettomanager.jsp").forward(request, response);
			break;
			
		case "UPDATE":
			nome = request.getParameter("nome");
			dimensione = Integer.parseInt(request.getParameter("dimensione"));
			cancellato = Integer.parseInt(request.getParameter("cancellato"));
			id = Integer.parseInt(request.getParameter("id"));
			dto = new OggettoDTO (id,nome,dimensione,cancellato);
			ans = service.update(dto);
			updateList(request);
			getServletContext().getRequestDispatcher("/oggetto/oggettomanager.jsp").forward(request, response);
			break;

		case "DELETE":
			id = Integer.parseInt(request.getParameter("id"));
			ans = service.delete(id);
			request.setAttribute("ans", ans);
			updateList(request);
			getServletContext().getRequestDispatcher("/oggetto/oggettomanager.jsp").forward(request, response);
			break;
		}
	}
}