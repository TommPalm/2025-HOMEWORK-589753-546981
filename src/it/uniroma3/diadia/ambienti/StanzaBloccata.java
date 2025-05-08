package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

public class StanzaBloccata extends Stanza {

	private String chiave;
	private String bloccata;
	private IO io = new IOConsole();
	
	public StanzaBloccata(String nome, String dir, String attrezzo) {
		super(nome);
		this.bloccata = dir;
		this.chiave= attrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		
        if(this.hasAttrezzo(chiave)) {
        	for(int i=0; i<this.numeroStanzeAdiacenti; i++)
            	if (this.direzioni[i].equals(direzione))
            		stanza = this.stanzeAdiacenti[i];
            return stanza;
        }
        
        else {
        	io.stampa("questa direzione è bloccata");
        	return this;
        }
	}

}
