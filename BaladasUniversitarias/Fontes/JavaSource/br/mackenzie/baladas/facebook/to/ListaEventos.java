package br.mackenzie.baladas.facebook.to;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.factory.ControllerFactory;

public class ListaEventos {
	Facebook facebook;
	
	List<Evento> listaEventos = new LinkedList<Evento>();
	
	public ListaEventos(String token) {
		facebook = ControllerFactory.getFacebookInstance(token);
		
		List<Evento> listaAux = new LinkedList<Evento>();
		Evento aux = new Evento();
		Iterator<Evento> it = facebook.obterEventos().iterator();
		while(it.hasNext()) {
			aux = it.next();
			aux = filtraEvento(aux);
			if(aux != null) {
				listaAux.add(aux);
			}
		}
		
		listaEventos = listaAux;
	}
	
	public Evento filtraEvento(Evento evento) {
		if("America/Sao_Paulo".equals(evento.getTimezone())) {
			return evento;
		}
		return null;
	}
	
	public List<Evento> getListaEventos(){
		return this.listaEventos;
	}
}
