package br.mackenzie.baladas.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mackenzie.baladas.factory.ControllerFactory;

import com.restfb.types.Event;

/**
 * Servlet implementation class PaginaInicial
 */
public class PaginaInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	response.setContentType("text/html;charset=UTF-8");
    	String token = request.getParameter("token");
        List<Event> listEvent = ControllerFactory.getFacebookInstance(token.trim()).obterEventos();
        request.setAttribute("Eventos", listEvent);
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/PaginaInicial.jsp");
        rd.forward(request, response);
    }

    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}
}
