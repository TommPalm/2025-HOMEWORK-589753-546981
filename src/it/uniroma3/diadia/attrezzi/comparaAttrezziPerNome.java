package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class comparaAttrezziPerNome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo a, Attrezzo o) {
		return a.getNome().compareTo(o.getNome());
	}

}
