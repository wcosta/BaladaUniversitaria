package br.mackenzie.baladas.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.to.ListaEventos;
import br.mackenzie.baladas.factory.ControllerFactory;

import com.restfb.types.User;

/*
 * Servlet implementation class PaginaInicial
 */
public class PaginaInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String token = request.getParameter("token");
		Facebook fb = ControllerFactory.getFacebookInstance(token);
		request.getSession().setAttribute("fb_token", token);
		User usuario = fb.obterUsuarioLogado();
		ListaEventos listaEventos = new ListaEventos(fb.obterEventos());
		request.setAttribute("Usuario", usuario);
		request.setAttribute("Eventos", listaEventos.getListaEventos());
		RequestDispatcher rd = request
				.getRequestDispatcher("/jsp/PaginaInicial.jsp");
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
