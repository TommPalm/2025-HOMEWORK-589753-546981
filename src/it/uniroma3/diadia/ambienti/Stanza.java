package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	//static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	//static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String,Attrezzo> attrezzi;
	protected int numeroAttrezzi;
	protected Map<Direzione,Stanza> stanzeAdiacenti;
	protected int numeroStanzeAdiacenti;
	protected Map<String,AbstractPersonaggio> npc;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new TreeMap<Direzione,Stanza>();
		this.attrezzi = new HashMap<>();
		this.npc = new TreeMap<>();
	}
	
	public Map<Direzione,Stanza> getMapStanzeAdiacenti(){
		return this.stanzeAdiacenti;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(direzione!=null){
			this.stanzeAdiacenti.put(direzione, stanza);
		}
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(this.getDirezioni().contains(direzione))
			return this.stanzeAdiacenti.get(direzione);
		else return null;
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {
		List<Attrezzo> l = new ArrayList<>(this.attrezzi.values());
		return l;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (String direzione : Direzione.getString(this.getDirezioni()))
			if (direzione!=null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		List<Attrezzo> l = new ArrayList<>(this.attrezzi.values());
		for (Attrezzo attrezzo : l) {
			if(attrezzo!=null)
				risultato.append(attrezzo.toString()+" ");
		}
		risultato.append("\nPersonaggi nella stanza: ");
		List<String> pers = new ArrayList<>(this.npc.keySet());
		for(String s : pers) {
			if(s!=null) {
				risultato.append(s);
			}
		}
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.hasAttrezzo(nomeAttrezzo))	{
			return this.attrezzi.get(nomeAttrezzo);
		}
		else return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		// TODO da implementare
		if(this.hasAttrezzo(attrezzo.getNome())) {
			this.attrezzi.remove(attrezzo.getNome());
			this.numeroAttrezzi--;
			return true;
		}
		else return false;
	}

	public int getNumeroAttrezzi() {
		return this.numeroAttrezzi;
	}

	public List<Direzione> getDirezioni() {
		return new ArrayList<Direzione>(this.getMapStanzeAdiacenti().keySet());
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza t = (Stanza) o;
		return this.getNome().equals(t.getNome());
	}
	
	public void addMago(Mago m) {
		this.npc.put(m.getNome(), m);
	}
	
	public void addCane(Cane c) {
		this.npc.put(c.getNome(),c);
	}
	
	public void addStrega(Strega s) {
		this.npc.put(s.getNome(),s);
	}
	
	public boolean hasPersonaggio() {
		if(this.npc.isEmpty()) return false;
		else return true;
	}
	
	public Map<String,AbstractPersonaggio> getNpc(){
		return this.npc;
	}

}