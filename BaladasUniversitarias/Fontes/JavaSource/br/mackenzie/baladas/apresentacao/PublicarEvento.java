package br.mackenzie.baladas.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mackenzie.baladas.factory.ControllerFactory;

/**
 * Servlet implementation class PublicarEvento
 */
@WebServlet("/PublicarEvento")
public class PublicarEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String token = (String) request.getSession().getAttribute("fb_token");
		String id = request.getParameter("idEvento");
		String nome = request.getParameter("nomeEvento");
		String img = request.getParameter("imgEvento");
		
		ControllerFactory.getFacebookInstance(token).publicarEvento(id,nome,img,request.getContextPath());
		
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
