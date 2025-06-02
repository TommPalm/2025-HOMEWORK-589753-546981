package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	
	/*
	public Labirinto() {
		creaStanze();
	} 
    private void creaStanze() {

		// crea gli attrezzi 
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		// crea stanze del labirinto 
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		// collega le stanze 
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        // pone gli attrezzi nelle stanze 
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        stanzaIniziale = atrio;  
		stanzaVincente = biblioteca;
    }
    */
	
	public Labirinto(String file) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(file);
		c.carica();
		this.setStanzaIniziale(c.getStanzaIniziale());
		this.setStanzaVincente(c.getStanzaVincente());
	}
	
	public Labirinto() {
		
	}
	
	public static LabirintoBuilder builder(String file)  throws FileNotFoundException, FormatoFileNonValidoException{
		return new LabirintoBuilder(file);
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public void setStanzaIniziale(Stanza iniziale) {
		this.stanzaIniziale= iniziale;
	}
    
	
	public static class LabirintoBuilder {
		
		private Map<String,Stanza> stanze;  //stanze con chiave il nome
		private Labirinto lab;
		private Stanza ultima;  //ultima stanza aggiunta

		public LabirintoBuilder(String file) throws FileNotFoundException, FormatoFileNonValidoException {
			this.stanze = new HashMap<>();
			lab = new Labirinto(file);
			this.ultima = null;
		}
		
		public LabirintoBuilder() {
			this.stanze = new HashMap<>();
			lab = new Labirinto();
			this.ultima=null;
		}
		
		public void ultimaAggiunta(Stanza st) {
			this.stanze.put(st.getNome(), st);
			this.ultima = st;
		}
		
		public Map<String,Stanza> getStanze(){
			return this.stanze;
		}
		
		public List<Stanza> getListaStanze(){
			List<Stanza> l = new ArrayList<>(this.getStanze().values());
			return l;
		}
		
		public Stanza getUltima() {
			return this.ultima;
		}
		
		public Labirinto getLabirinto() {
			return this.lab;
		}
		
		public LabirintoBuilder addStanza(String nome) {
			Stanza nuova = new Stanza(nome);
			this.ultimaAggiunta(nuova);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			Stanza nuova = new StanzaMagica(nome,soglia);
			this.ultimaAggiunta(nuova);
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza nuova = new StanzaMagica(nome);
			this.ultimaAggiunta(nuova);
			return this;
		}
		
		public LabirintoBuilder addStanzaBuia(String nome, String luce) {
			Stanza nuova = new StanzaBuia(nome,luce);
			this.ultimaAggiunta(nuova);
			return this;
		}
		
		public LabirintoBuilder addStanzaBloccata(String nome,Direzione direzione,String chiave) {
			Stanza nuova = new StanzaBloccata(nome,direzione,chiave);
			this.ultimaAggiunta(nuova);
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			Attrezzo att = new Attrezzo(nome,peso);
			this.getUltima().addAttrezzo(att);
			return this;
		}
		
		public LabirintoBuilder adiacenza(String nome1,String nome2,Direzione direzione) {
			Stanza partenza = this.stanze.get(nome1);
			Stanza arrivo = this.stanze.get(nome2);
			partenza.impostaStanzaAdiacente(direzione, arrivo);
			return this;
		}
		
		public LabirintoBuilder setIniziale() {
			this.getLabirinto().setStanzaIniziale(this.getUltima());
			return this;
		}

		public LabirintoBuilder setVincente() {
			this.getLabirinto().setStanzaVincente(this.getUltima());
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String descrizione, String regalo, int peso) {
			Mago merlino = new Mago(nome, descrizione,regalo,peso);
			this.getUltima().addMago(merlino);
			return this;
		}

		public LabirintoBuilder addCane(String nome,String presentazione,String dono,int pesoDono,String cibo) {
			Cane cane = new Cane(nome,presentazione,dono, pesoDono,cibo);
			this.getUltima().addCane(cane);
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome,String presentazione) {
			Strega nocciola= new Strega(nome,presentazione);
			this.getUltima().addStrega(nocciola);
			return this;
		}
	}
    
}
