package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando {
 
 
	private IO io = new IOConsole();
	static final private String[] elencoComandi = {"vai","aiuto","fine"
			,"prendi","posa"
			,"guarda","guardaBorsa"
			,"saluta","interagisci"
			,"regala"};

	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			io.stampa(elencoComandi[i]+" ");
		io.stampa("");
	}

	
	public boolean esisteComando(String a) {
		for(int i=0; i< elencoComandi.length;i++) {
			if(elencoComandi[i].equals(a)) {
				return true;
			}
		}
		return false;
	}


	public String getNome() {
		return "aiuto";
	}

}
