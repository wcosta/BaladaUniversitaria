package br.mackenzie.baladas.facebook.to;

import java.util.List;

import br.mackenzie.baladas.util.FormatUtil;

import com.restfb.Facebook;
import com.restfb.types.Event;

public class Evento extends Event{
	private static final long serialVersionUID = 7359230846748248825L;
	
	@Facebook("timezone")
	private String timezone;
	
	private EventoFql fql;
	
	private List<PresencaDetalhes> listaDetalhes;
	
	public String getDataInicio(){
		if(super.getStartTime() != null) {
			return FormatUtil.formataDataHora(super.getStartTime());
		}
		return null;
	}
	public String getDataFim(){
		if(super.getEndTime() != null) {
			return FormatUtil.formataDataHora(super.getEndTime());
		}
		return null;
	}

	public String getTimezone() {
		return timezone;
	}
	public EventoFql getFql() {
		return fql;
	}
	public void setFql(EventoFql fql) {
		this.fql = fql;
	}
	public List<PresencaDetalhes> getListaDetalhes() {
		return listaDetalhes;
	}
	public void setListaDetalhes(List<PresencaDetalhes> listaDetalhes) {
		this.listaDetalhes = listaDetalhes;
	}
	public int getSize(){
		return this.listaDetalhes.size();
	}
}
