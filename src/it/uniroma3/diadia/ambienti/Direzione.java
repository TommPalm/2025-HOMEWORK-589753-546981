package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;

public enum Direzione {
	nord, sud,est,ovest;
	
	public static List<String> getString(List<Direzione> s){
		List<String> lista = new ArrayList<>();
		for(Direzione d : s) {
			lista.add(d.toString());
		}
		return lista;
	}
	
}
