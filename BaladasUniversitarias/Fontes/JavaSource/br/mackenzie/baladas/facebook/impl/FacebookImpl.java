package br.mackenzie.baladas.facebook.impl;

import java.util.LinkedList;
import java.util.List;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.to.Evento;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FacebookImpl implements Facebook{
	private static final String ID_APP = "1410104595870928";
	private static final String SEGREDO_APP = "41a45d319476e327c0a0f2812b0c0594";
	
	FacebookClient conectorFb;
	
	public FacebookImpl(String token){
		AccessToken accessToken = new DefaultFacebookClient().obtainExtendedAccessToken(ID_APP, SEGREDO_APP, token);
		conectorFb = new DefaultFacebookClient(accessToken.getAccessToken());
	}
	
	public User obterUsuario(){
		return this.conectorFb.fetchObject("me", User.class);
	}
	
	public List<Evento> obterEventos() {
		List<Evento> listaAux = new LinkedList<Evento>();
		Connection<Evento> con = this.conectorFb.fetchConnection("search", Evento.class, Parameter.with("type", "event"), Parameter.with("q", "universitária"));
		Connection<Evento> mac = this.conectorFb.fetchConnection("search", Evento.class, Parameter.with("type", "event"), Parameter.with("q", "mack"));
		Connection<Evento> each = this.conectorFb.fetchConnection("search", Evento.class, Parameter.with("type", "event"), Parameter.with("q", "EACH"));
		
		listaAux.addAll(con.getData());
		listaAux.addAll(mac.getData());
		listaAux.addAll(each.getData());
		while (con.getNextPageUrl() != null || "".equals(con.getNextPageUrl())) {
			con = this.conectorFb.fetchConnectionPage(con.getNextPageUrl(), Evento.class);
			listaAux.addAll(con.getData());
		}
		
		return listaAux;
	}
	
	public Evento obterDetalhesEvento(Evento ev) {
		return obterDetalhesEventoPeloId(ev.getId());
	}
	
	public Evento obterDetalhesEventoPeloId(String id) {
		return this.conectorFb.fetchConnection("event", Evento.class, Parameter.with("id", id), Parameter.with("center", "-23.55073,-46.633837"), Parameter.with("distance", "10000")).getData().get(0);
	}
	
	public void publicarEvento(String idEvento, String nomeEvento, String imgEvento, String path) {
		String texto = "";
		
		texto += "Teste de trabalho:\nNome do Evento: " + nomeEvento;
		this.conectorFb.publish("me/feed", FacebookType.class, Parameter.with("message", texto), Parameter.with("link", "https://facebook.com/events/" + idEvento));
	}
	
	public void confirmarPresencaEvento(String idEvento){
		try {
			this.conectorFb.publish(idEvento+"/attending", FacebookType.class);
		} catch(Exception e) {
			//TODO
		}
	}
}
