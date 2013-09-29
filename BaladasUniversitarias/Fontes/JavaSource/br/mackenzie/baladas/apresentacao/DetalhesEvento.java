package br.mackenzie.baladas.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mackenzie.baladas.facebook.to.Evento;
import br.mackenzie.baladas.factory.ControllerFactory;

/**
 * Servlet implementation class DetalhesEvento
 */
public class DetalhesEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("fb_token");
		String id = request.getParameter("idEvento");
		
		Evento evento = ControllerFactory.getFacebookInstance(token).obterDetalhesEvento(id);
		
		request.setAttribute("evento", evento);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/DetalhesEvento.jsp");
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
