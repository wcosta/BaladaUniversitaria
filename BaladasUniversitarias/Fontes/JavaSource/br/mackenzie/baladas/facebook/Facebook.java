package br.mackenzie.baladas.facebook;

import java.util.List;

import com.restfb.types.User;

import br.mackenzie.baladas.facebook.to.Evento;


public interface Facebook {
	public User obterUsuario();
	public List<Evento> obterEventos();
	public Evento obterDetalhesEvento(Evento ev);
	public Evento obterDetalhesEventoPeloId(String id);
	public void publicarEvento(String idEvento, String nomeEvento,String imgEvento, String path);
	public void confirmarPresencaEvento(String idEvento);
}
