package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza {

	private String chiave;
	private Direzione bloccata;
	private IO io = new IOConsole();
	
	public StanzaBloccata(String nome, Direzione dir, String attrezzo) {
		super(nome);
		this.bloccata = dir;
		this.chiave= attrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
        
		if(direzione.equals(bloccata)) {
			if(this.hasAttrezzo(chiave)) {
				return this.stanzeAdiacenti.get(direzione);
			}
			else {
        	io.stampa("questa direzione è bloccata");
        	return this;
			}
		}
		else {
			return this.stanzeAdiacenti.get(direzione);
		}
        
	}

}
