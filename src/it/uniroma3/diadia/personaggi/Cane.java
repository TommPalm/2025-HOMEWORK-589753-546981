package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private int danno = 2;
	private Attrezzo regalo;
	private String cibo;
	private IO io = new IOConsole();

	public Cane(String nome, String presenta, String nomeRegalo,int pesoRegalo, String nomeCibo) {
		super(nome, presenta);
		this.cibo= nomeCibo;
		this.regalo = new Attrezzo(nomeRegalo,pesoRegalo);
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-this.danno );
		return this.getNome()+" ti morde. hai perso "+this.danno+" CFU!";
	}

	@Override
	public String riceviRegalo(Attrezzo a, Partita p) {
		
		if(a.getNome().equals(this.cibo)) {
			p.getStanzaCorrente().addAttrezzo(regalo);
			return "gli è piaciuto quello che gli hai dato e posa a terra un oggetto";
		}
		else {
			io.stampa(this.getNome()+" mangia "+a.getNome()+" ,ma non gli è piaciuto");
			p.getStanzaCorrente().addAttrezzo(a);
			return this.agisci(p);
		}
	}

}
