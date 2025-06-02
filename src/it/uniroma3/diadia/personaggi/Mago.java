package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	Attrezzo regalo;
	final static private String scuse = "mi dispiace, non ho nulla da darti";
	final static private String dono = "tieni amico, qualcosa per te *posa qualcosa a terra*";
	final static private String riceve_dono = "grazie, gli ho fatto una magia *posa l'oggetto a terra*";
	
	public Mago(String nome, String presenta, String nomeatt, int pesoatt) {
		super(nome, presenta);
		regalo = new Attrezzo(nomeatt,pesoatt);
		
	}

	@Override
	public String agisci(Partita partita) {
		if(regalo!=null) {
			partita.getStanzaCorrente().addAttrezzo(regalo);
			this.regalo=null;
			return dono;
		}
		else return scuse;
	}

	@Override
	public String riceviRegalo(Attrezzo a, Partita p) {
		Attrezzo att = new Attrezzo(a.getNome(),a.getPeso()/2);
		p.getStanzaCorrente().addAttrezzo(att);
		return riceve_dono;
	}

}
