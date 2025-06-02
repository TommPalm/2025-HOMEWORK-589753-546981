package it.uniroma3.diadia.comandi;

import java.util.Map;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	
	private IO io = new IOConsole();
	private String parametro;
	

	@Override
	public String getNome() {
		return "interagisci";
	}

	@Override
	public void esegui(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		Map<String,AbstractPersonaggio> npc = corrente.getNpc();
		
		if(corrente.hasPersonaggio()) {
			if(this.parametro==null) {
				io.stampa("con chi vuoi interagire?");
				return;
			}
			if(npc.containsKey(this.parametro)) {
				io.stampa(npc.get(parametro).agisci(partita));
			}
			else io.stampa(this.parametro +" non è presente nella stanza");
		}
		
		else {
			io.stampa("non c'è nessuno");
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
