package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Borsa;
import it.uniroma3.diadia.IO;
public class ComandoPosa extends AbstractComando {

	private String nomeAttrezzo = null;
	private IO io = new IOConsole();
 
	@Override
	public void esegui(Partita partita) {
		Borsa b = partita.getGiocatore().getBorsa();
		
		if(this.nomeAttrezzo==null) {
			io.stampa("cosa vuoi posare?");
			return;
		}
		if(b.hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = b.getAttrezzo(nomeAttrezzo);
			partita.getStanzaCorrente().addAttrezzo(a);
			b.removeAttrezzo(nomeAttrezzo);
		}
		else {
			io.stampa("l'attrezzo " + nomeAttrezzo + " non è presente nella borsa");
		}
	}

	public String getNome() {
		return "posa";
	}
	
	@Override
	public void setParametro(String p) {
		this.nomeAttrezzo=p;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
