package br.mackenzie.baladas.facebook;

import java.util.List;

import br.mackenzie.baladas.facebook.to.Evento;

import com.restfb.types.User;


public interface Facebook {
	public User obterUsuario();
	public List<Evento> obterEventos();
	public Evento obterDetalhesEvento(Evento ev);
	public Evento obterDetalhesEventoPeloId(String id);
	public void publicarEvento(String idEvento, String nomeEvento);
	public void confirmarPresencaEvento(String idEvento);
}
