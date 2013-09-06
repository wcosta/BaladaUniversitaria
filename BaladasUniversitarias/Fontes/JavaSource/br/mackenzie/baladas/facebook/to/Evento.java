package br.mackenzie.baladas.facebook.to;

import br.mackenzie.baladas.util.FormatUtil;

import com.restfb.Facebook;
import com.restfb.types.Event;

public class Evento extends Event{
	private static final long serialVersionUID = 7359230846748248825L;
	
	@Facebook("timezone")
	private String timezone;
	
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
}
