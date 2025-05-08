package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;
public class ComandoPrendi implements Comando {

	private IO io = new IOConsole();
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo a = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(partita.getGiocatore().getBorsa().addAttrezzo(a))
				partita.getStanzaCorrente().removeAttrezzo(a);
		}
		else {
			io.stampa("l'attrezzo " + nomeAttrezzo + " non è presente nella stanza");
		}

	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo= parametro;

	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

}
