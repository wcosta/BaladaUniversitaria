package br.mackenzie.baladas.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mackenzie.baladas.factory.ControllerFactory;

/**
 * Servlet implementation class ConfirmarPresenca
 */
public class ConfirmarPresenca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String token = (String) request.getSession().getAttribute("fb_token");
		
		String id = request.getParameter("idEvento");
		
		ControllerFactory.getFacebookInstance(token).confirmarPresencaEvento(id);
		request.setAttribute("mensagemSistema", "Presença confirmada no evento.");
		RequestDispatcher rd = request.getRequestDispatcher("/DetalhesEvento?idEvento="+id);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
