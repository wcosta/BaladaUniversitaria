package br.mackenzie.baladas.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	private static final SimpleDateFormat ddMMyyyyHHmm = 
			new SimpleDateFormat("dd/MM/yyyy - HH:mm");
	
	public static String formataDataHora(Date dataHora) {
		return ddMMyyyyHHmm.format(dataHora);
	}
}
