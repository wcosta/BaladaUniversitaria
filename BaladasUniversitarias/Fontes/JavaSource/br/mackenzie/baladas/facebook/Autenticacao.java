package br.mackenzie.baladas.facebook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Autenticacao
 */
@WebServlet("/Autenticacao")
public class Autenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String url = "https://graph.facebook.com/oauth/access_token?";
	private static final String client_id = "client_id=1410104595870928";
	private String redirect_uri;
	private static final String client_secret = "&client_secret=41a45d319476e327c0a0f2812b0c0594&code=";
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAppUrl(request.getContextPath());
		String code = request.getParameter("code");
		if(code != null) {
			response.sendRedirect(getUrl(code));
			return;
		}
		String accessToken = request.getParameter("access_token");
		String expira = request.getParameter("expires");
		
		String url = request.getContextPath() + "/PaginaInicial?token=" + accessToken
			+ "&expira=" + expira;
		response.sendRedirect(url);
	}
	
	private String getUrl(String code){
		return url + client_id + getAppUrl() + client_secret + code;
	}
	
	private void setAppUrl(String contextPath) {
		this.redirect_uri = "&redirect_uri=http://localhost:8080" + contextPath + "/Autenticacao";
	}
	
	private String getAppUrl(){
		return redirect_uri;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
