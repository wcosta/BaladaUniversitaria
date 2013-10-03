package br.mackenzie.baladas.facebook.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.to.AttendingFql;
import br.mackenzie.baladas.facebook.to.Evento;
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
	String token;
	
	public FacebookImpl(String token){
		AccessToken accessToken = new DefaultFacebookClient().obtainExtendedAccessToken(ID_APP, SEGREDO_APP, token);
		conectorFb = new DefaultFacebookClient(accessToken.getAccessToken());
		this.token = token;
	}
	
	public User obterUsuarioLogado(){
		return this.conectorFb.fetchObject("me", User.class);
	}
	
	public User obterUsuarioPeloId(String id){
		return this.conectorFb.fetchObject(id, User.class);
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
		
		listaAux = filtraEvento(listaAux);
		
		ComparadorData comparador = new ComparadorData();
		Collections.sort(listaAux, comparador);
		
		return listaAux;
	}
	
	public List<Evento> filtraEvento(List<Evento> lista) {
		List<Evento> listaAux = new LinkedList<Evento>();
		Iterator<Evento> it = lista.iterator();
		while(it.hasNext()) {
			Evento evento = it.next();
			if("America/Sao_Paulo".equals(evento.getTimezone())) {
				listaAux.add(evento);
			}
		}
		
		listaAux = obterDetalhesPresencasLista(listaAux);
		
		return listaAux;
	}
	
	public Evento obterDetalhesEvento(String id) {
		Evento ev = new Evento();
		String q = "SELECT eid, name, creator, unsure_count, " +
				"attending_count, declined_count, " +
				"all_members_count, description, " +
				"start_time, end_time, location, venue " +
				"FROM event WHERE eid = " + id;
		ev = this.conectorFb.executeFqlQuery(q, Evento.class).get(0);
		
		ev.setCriador(obterUsuarioPeloId(ev.getCreator()));
		
		return ev;
	}
	
	public List<User> obterPresencaAmigos(String idEvento) {
		List<User> listaRetorno = new LinkedList<User>();
		String q = "select uid, rsvp_status from event_member where eid = " + idEvento + " and uid IN (SELECT uid2 from friend where uid1 = me())";
		List<AttendingFql> list = this.conectorFb.executeFqlQuery(q, AttendingFql.class);
		if(!list.isEmpty()) {
			Iterator<AttendingFql> it = list.iterator();
			while(it.hasNext()) {
				AttendingFql aux = it.next();
				if("attending".equals(aux.getRsvp_status())){
					listaRetorno.add(obterUsuarioPeloId(aux.getUid()));
				}
			}
		}
		return listaRetorno;
	}
	
	public List<Evento> obterDetalhesPresencasLista(List<Evento> listaEv) {
		List<Evento> lista = new LinkedList<Evento>();
		Iterator<Evento> it = listaEv.iterator();
		Evento aux = null;
		while (it.hasNext()) {
			aux = obterDetalhesEvento(it.next().getId());
			if (Integer.parseInt(aux.getAll_members_count()) > 500) {
				lista.add(aux);
			}
		}
		
		return lista;
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
			//Bug do RestFB. Não consegue fazer uma requisição sem retorno.
		}
	}
	
	public String getToken() {
		return this.token;
	}
}
