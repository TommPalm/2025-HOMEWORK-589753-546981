package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;

import java.util.Map;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {
	
	private IO io = new IOConsole();
	String parametro;

	@Override
	public String getNome() {
		return "saluta";
	}

	@Override
	public void esegui(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		Map<String,AbstractPersonaggio> npc = corrente.getNpc();
		
		if(corrente.hasPersonaggio()) {
			if(this.parametro==null) {
				io.stampa("chi stai salutando?");
				return;
			}
			if(npc.containsKey(this.parametro)) {
				io.stampa(npc.get(this.parametro).saluta());
			}
			else io.stampa(this.parametro +" non è presente nella stanza");
		}
		
		else {
			io.stampa("non c'è nessuno da salutare");
		}

	}
	
	@Override
	public void setParametro(String p) {
		this.parametro=p;
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}

}
