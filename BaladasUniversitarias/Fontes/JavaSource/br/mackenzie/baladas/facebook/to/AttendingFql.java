package br.mackenzie.baladas.facebook.to;

import com.restfb.Facebook;

public class AttendingFql {
	@Facebook
	private String uid;
	@Facebook
	private String rsvp_status;
	
	public String getUid() {
		return uid;
	}
	public String getRsvp_status() {
		return rsvp_status;
	}
}
