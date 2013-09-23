package br.mackenzie.baladas.facebook.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.to.Evento;
import br.mackenzie.baladas.facebook.to.EventoFql;
import br.mackenzie.baladas.facebook.to.PresencaDetalhes;
import br.mackenzie.baladas.util.ComparadorData;

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
		Connection<Evento> con = this.conectorFb.fetchConnection("search", Evento.class, Parameter.with("type", "event"), Parameter.with("q", "universit�ria"));
		Connection<Evento> mac = this.conectorFb.fetchConnection("search", Evento.class, Parameter.with("type", "event"), Parameter.with("q", "mack"));
		Connection<Evento> each = this.conectorFb.fetchConnection("search", Evento.class, Parameter.with("type", "event"), Parameter.with("q", "EACH"));
		
		listaAux.addAll(con.getData());
		listaAux.addAll(mac.getData());
		listaAux.addAll(each.getData());
		while (con.getNextPageUrl() != null || "".equals(con.getNextPageUrl())) {
			con = this.conectorFb.fetchConnectionPage(con.getNextPageUrl(), Evento.class);
			listaAux.addAll(con.getData());
		}
		ComparadorData comparador = new ComparadorData();
		Collections.sort(listaAux, comparador);
		
		return listaAux;
	}
	
	public Evento obterDetalhesEvento(Evento ev) {
		return obterDetalhesEventoPeloId(ev.getId());
	}
	
	public Evento obterDetalhesEventoPeloId(String id) {
		
		Evento ev = new Evento();
		ev = this.conectorFb.fetchConnection("event", Evento.class, 
				Parameter.with("id", id), 
				Parameter.with("center", "-23.55073,-46.633837"), 
				Parameter.with("distance", "10000"))
				.getData().get(0);
		String q = "SELECT eid, name, unsure_count, " +
				"attending_count, declined_count, " +
				"all_members_count FROM event WHERE eid = " + id;
		EventoFql ef = this.conectorFb.executeFqlQuery(q, EventoFql.class).get(0);
		ev.setFql(ef);
		
		ev.setListaDetalhes(obterDetalhesPresencas(id));
		
		return ev;
	}
	
	public List<PresencaDetalhes> obterDetalhesPresencas(String id) {
		List<PresencaDetalhes> detalhes = new LinkedList<PresencaDetalhes>();
		
		Connection<PresencaDetalhes> con = this.conectorFb.fetchConnection(id+"/invited", PresencaDetalhes.class);
		detalhes.addAll(con.getData());
		
		while (con.hasNext()) {
			con = this.conectorFb.fetchConnectionPage(con.getNextPageUrl(), PresencaDetalhes.class);
			detalhes.addAll(con.getData());
		}
		
		return detalhes;
	}
	
	public void publicarEvento(String idEvento, String nomeEvento) {
		String texto = "";
		texto += "Teste de trabalho:\nNome do Evento: " + nomeEvento;
		this.conectorFb.publish("me/feed", FacebookType.class, Parameter.with("message", texto), Parameter.with("link", "https://facebook.com/events/" + idEvento));
	}
	
	public void confirmarPresencaEvento(String idEvento){
		try {
			this.conectorFb.publish(idEvento+"/attending", FacebookType.class);
		} catch(Exception e) {
			//TODO
			//Bug do RestFB. N�o consegue fazer uma requisi��o sem retorno.
		}
	}
}
