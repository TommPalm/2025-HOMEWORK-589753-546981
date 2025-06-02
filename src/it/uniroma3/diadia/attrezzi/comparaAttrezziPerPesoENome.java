package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class comparaAttrezziPerPesoENome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo a, Attrezzo o) {
		if(a.getPeso()==o.getPeso()) {
			return a.getNome().compareTo(o.getNome());
		}
		else return a.getPeso()-o.getPeso();
	}

}
