package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;
public class ComandoVai extends AbstractComando {
 
	private String dir;
	private IO io = new IOConsole();
	 
	 
	@Override
	public void esegui(Partita partita) {
		
		Stanza prossimaStanza = null;
		if(this.dir!=null && (this.dir.equals("nord") || this.dir.equals("est") || this.dir.equals("ovest") || this.dir.equals("sud")))
			prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(dir));
		else prossimaStanza=null;
		
		if(dir==null) {
			io.stampa("dove vuoi andare? specifica la direzione");
			return;
		}
		
		if(prossimaStanza==null) {
			io.stampa("direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		io.stampa(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}

	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return this.dir;
	}
	@Override
	public void setParametro(String p) {
		this.dir= p;
	}

}
