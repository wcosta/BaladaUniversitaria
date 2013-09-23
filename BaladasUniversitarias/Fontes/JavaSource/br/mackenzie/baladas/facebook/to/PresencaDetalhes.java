package br.mackenzie.baladas.facebook.to;

import com.restfb.Facebook;
import com.restfb.types.NamedFacebookType;

public class PresencaDetalhes extends NamedFacebookType{
	private static final long serialVersionUID = 1L;
	
	public static final int CONFIRMADO = 1;
	public static final int RECUSADO = 2;
	public static final int TALVEZ = 3;
	public static final int NAO_RESPONDIDO = 0;
	
	@Facebook
	private String rspv_status;

	public String getRspv_status() {
		return rspv_status;
	}
	
	public int getStatus () {
		if ("attending".equals(rspv_status)) return CONFIRMADO;
		else if ("".equals(rspv_status)) return RECUSADO;
		else if ("unsure".equals(rspv_status)) return TALVEZ;
		else return NAO_RESPONDIDO;
	}
}
