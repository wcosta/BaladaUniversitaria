package br.mackenzie.baladas.factory;

import br.mackenzie.baladas.facebook.Facebook;
import br.mackenzie.baladas.facebook.impl.FacebookImpl;

public class ControllerFactory {
	private static Facebook facebook;
	
	public static Facebook getFacebookInstance(String code) {
		if (facebook == null) {
			facebook = new FacebookImpl(code);
		}
		return facebook;
	}
}
