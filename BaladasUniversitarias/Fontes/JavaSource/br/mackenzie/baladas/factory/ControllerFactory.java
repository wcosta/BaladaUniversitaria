package br.mackenzie.baladas.factory;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.impl.FacebookImpl;

public class ControllerFactory {
	private static Facebook facebook;
	
	public static Facebook getFacebookInstance(String token) {
		if (facebook == null) {
			facebook = new FacebookImpl(token);
		} else if (!facebook.getToken().equals(token)) {
			facebook = new FacebookImpl(token);
		}
		return facebook;
	}
}
