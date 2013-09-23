package br.mackenzie.baladas.facebook.to;

import com.restfb.Facebook;

public class EventoFql {
	@Facebook
	private String eid;
	@Facebook
	private String name;
	@Facebook
	private String all_members_count;
	@Facebook
	private String attending_count;
	@Facebook
	private String declined_count;
	@Facebook
	private String unsure_count;
	
	public String getEid() {
		return eid;
	}
	public String getName() {
		return name;
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
}
