package br.mackenzie.baladas.facebook;

import java.util.List;

import br.mackenzie.baladas.facebook.to.Evento;


public interface Facebook {
	public List<Evento> obterEventos();
	public Evento obterDetalhesEvento(Evento ev);
	public Evento obterDetalhesEventoPeloId(String id);
}
