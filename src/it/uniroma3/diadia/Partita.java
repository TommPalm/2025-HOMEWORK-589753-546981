package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto lab;
	private Giocatore giocatore;
	
	public Partita(Labirinto lab){
		this.lab= lab;
		this.finita = false;
		this.giocatore= new Giocatore();
		this.stanzaCorrente= lab.getStanzaIniziale();
	}


	public Stanza getStanzaVincente() {
		return lab.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return this.finita || this.vinta() || this.giocatore.getCfu()<1 ;
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);;		
	}	
	
	public Labirinto getLabirinto() {
		return this.lab;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public boolean giocatoreIsVivo() {
		if(this.getCfu()>0) {
			return true;
		}
		else return false;
	}
	
	public void setLabirinto(Labirinto l) {
		this.lab=l;
	}
}
