package br.mackenzie.baladas.facebook;

import java.util.List;

import br.mackenzie.baladas.facebook.to.Evento;

import com.restfb.types.User;


public interface Facebook {
	public User obterUsuarioLogado();
	public List<Evento> obterEventos();
	public Evento obterDetalhesEvento(String id);
	public void publicarEvento(String idEvento, String nomeEvento);
	public void confirmarPresencaEvento(String idEvento);
	public List<User> obterPresencaAmigos(String idEvento);
	public String getToken();
}
