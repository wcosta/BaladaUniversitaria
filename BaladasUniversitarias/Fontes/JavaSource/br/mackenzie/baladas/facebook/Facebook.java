package br.mackenzie.baladas.facebook;

import java.util.List;

import com.restfb.types.Event;


public interface Facebook {
	public List<Event> obterEventos();
}
