package br.mackenzie.baladas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.restfb.exception.FacebookOAuthException;

/**
 * Servlet Filter implementation class TrataExcecaoFilter
 */
@WebFilter({ "/TrataExcecaoFilter", "/*" })
public class TrataExcecaoFilter implements Filter {
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(request, response);
		} catch (FacebookOAuthException ex) {
			request.setAttribute("mensagemSistema", "Falha na autenticação!");
			request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
		} catch (NullPointerException e) {
			if (e.getMessage().contains("accessToken")) {
				request.setAttribute("mensagemSistema", "Token de acesso não pode estar nulo!");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			} else {
				request.setAttribute("mensagemSistema", "Erro inesperado.");
				request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
