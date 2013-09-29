package br.mackenzie.baladas.apresentacao;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.to.Evento;
import br.mackenzie.baladas.factory.ControllerFactory;

/**
 * Servlet implementation class Termometro
 */
@WebServlet("/Termometro")
public class Termometro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static long tempoSemana = 1000 * 60 * 60 * 24 * 7;
       
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String token = (String) request.getSession().getAttribute("fb_token");
		
		String id = request.getParameter("idEvento");
		Facebook fb = ControllerFactory.getFacebookInstance(token);
		Evento ev = fb.obterDetalhesEvento(id);
		ev.setListaAmigosPresentes(fb.obterPresencaAmigos(id));
		
		int term = calcularTermometro(ev);
		
		String styleTerm = "termometro";
		String legenda = "";
		if (term < 20) {
			styleTerm += "0";
			legenda = "Congelando!";
		} else if (term >= 21 && term < 40) {
			styleTerm += "20";
			legenda = "Frio!";
		} else if (term >= 41 && term < 60) {
			styleTerm += "40";
			legenda = "Morno...";
		} else if (term >= 61 && term < 80) {
			styleTerm += "60";
			legenda = "Quente!";
		} else if (term >= 81) {
			styleTerm += "80";
			legenda = "BOMBANDO!!";
		}

		request.setAttribute("evento", ev);
		request.setAttribute("termometro", term);
		request.setAttribute("styleTermometro", styleTerm);
		request.setAttribute("legendaTermometro", legenda);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Termometro.jsp");
		rd.forward(request, response);
	}
	
	
	public int calcularTermometro(Evento e){
		int calc = 0;
		Date hoje = new Date();
		Date diaInicio = e.getStartTime();
		long timestamp = diaInicio.getTime() - hoje.getTime();
		long semanas = timestamp/tempoSemana;
		if (semanas < 0) {
			return 0;
		}
		int conf = Integer.parseInt(e.getAttending_count());
		int rec = Integer.parseInt(e.getDeclined_count());
		int talvez = Integer.parseInt(e.getUnsure_count());
		int todos = Integer.parseInt(e.getAll_members_count()) + conf + rec + talvez;
		
		if(semanas == 3) talvez = (talvez * 80)/100;
		else if (semanas == 2) talvez = (talvez * 65)/100;
		else if (semanas == 1) talvez = (talvez * 50)/100;
		
		calc = todos / (conf + talvez);
		
		return calc;
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
