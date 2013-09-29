package br.mackenzie.baladas.facebook.to;

import java.util.List;

import br.mackenzie.baladas.util.FormatUtil;

import com.restfb.Facebook;
import com.restfb.types.Event;
import com.restfb.types.User;

public class Evento extends Event {
	private static final long serialVersionUID = 7359230846748248825L;
	
	@Facebook
	private String timezone;
	@Facebook
	private String all_members_count;
	@Facebook
	private String attending_count;
	@Facebook
	private String declined_count;
	@Facebook
	private String unsure_count;
	@Facebook
	private String eid;
	@Facebook 
	private String creator;
	
	private User criador;
	
	private List<User> listaAmigosPresentes;
			
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
	public String getAll_members_count() {
		return all_members_count;
	}
	public String getAttending_count() {
		return attending_count;
	}
	public String getDeclined_count() {
		return declined_count;
	}
	public String getUnsure_count() {
		return unsure_count;
	}
	public String getEid() {
		return eid;
	}
	public String getCreator() {
		return creator;
	}
	public User getCriador() {
		return criador;
	}
	public void setCriador(User criador) {
		this.criador = criador;
	}
	public List<User> getListaAmigosPresentes() {
		return listaAmigosPresentes;
	}
	public void setListaAmigosPresentes(List<User> listaAmigosPresentes) {
		this.listaAmigosPresentes = listaAmigosPresentes;
	}
}
