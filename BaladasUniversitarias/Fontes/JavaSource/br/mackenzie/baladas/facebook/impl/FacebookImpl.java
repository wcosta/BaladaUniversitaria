package br.mackenzie.baladas.facebook.impl;

import java.util.List;

import br.mackenzie.baladas.facebook.Facebook;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Event;

public class FacebookImpl implements Facebook{	
	FacebookClient conectorFb;
	
	public FacebookImpl(){
		String token = "CAAUCe4ZBIdNABAPdZAUXC3kjXI60eZBZAm8KPhInSGQ8SGSgrCx4bZCtijoq9KBn7ZA7n36fIbFOJa9fuYDXtSSI8RCab1CIATQWUtB7hO6qzo34I0OMMwOIXGsUGeXyV88QCUDeOPmAVDn52t1oMWeUB3mCjK9aFAraCTxP3sZAA2uHLHm8C4hu0CAm7MinTmJzJA4qFRFzwZDZD";
		conectorFb = new DefaultFacebookClient(token);
	}
	
	public List<Event> obterEventos() {
		return this.conectorFb.fetchConnection("search", Event.class,  Parameter.with("type", "event"), Parameter.with("q", "universitária"), Parameter.with("timezone", "America/Sao_Paulo")).getData();
	}
}
