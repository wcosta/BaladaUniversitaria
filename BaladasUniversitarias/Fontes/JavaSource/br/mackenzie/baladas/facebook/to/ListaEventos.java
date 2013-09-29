package br.mackenzie.baladas.facebook.to;

import java.util.LinkedList;
import java.util.List;

public class ListaEventos {	
	List<Evento> listaEventos = new LinkedList<Evento>();
	
	public ListaEventos(List<Evento> lista) {
		listaEventos = lista;
	}
	
	public List<Evento> getListaEventos(){
		return this.listaEventos;
	}
}
