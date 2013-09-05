package br.mackenzie.baladas.facebook.to;

import java.text.SimpleDateFormat;

import com.restfb.types.Event;

public class Evento extends Event {
	private static final long serialVersionUID = 1L;
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public String getDataInicioFormatado(){
		return sdf.format(super.getStartTime());
	}
	public String getDataFimFormatado(){
		return sdf.format(super.getEndTime());
	}
}
