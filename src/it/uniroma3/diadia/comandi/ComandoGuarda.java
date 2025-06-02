package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoGuarda extends AbstractComando {
 
	private IO io = new IOConsole();

	@Override
	public void esegui(Partita partita) {
		io.stampa(partita.getStanzaCorrente().getDescrizione()+"\n"+partita.getGiocatore().getCfu());

	}

	public String getNome() {
		return "guarda";
	}


}
