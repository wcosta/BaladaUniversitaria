package br.mackenzie.baladas.util;

import java.util.Comparator;
import java.util.Date;

import br.mackenzie.baladas.facebook.to.Evento;

public class ComparadorData implements Comparator<Evento>{

	@Override
	public int compare(Evento o1, Evento o2) {
		Date d1 = ((Evento) o1).getStartTime();
		Date d2 = ((Evento) o2).getStartTime();
		
		return d1.compareTo(d2);
	}

}
