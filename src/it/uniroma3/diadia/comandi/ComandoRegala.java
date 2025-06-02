package it.uniroma3.diadia.comandi;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


public class ComandoRegala extends AbstractComando {

	private String par;
	private IO io = new IOConsole();


	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public void esegui(Partita partita) {

		Stanza corrente = partita.getStanzaCorrente();
		List<AbstractPersonaggio> npc = new ArrayList<>(corrente.getNpc().values());

		if(corrente.hasPersonaggio()) {
			if(par==null) {
				io.stampa("cosa vuoi regalare?");
				return;
			}
			if(partita.getGiocatore().getBorsa().hasAttrezzo(par)) {
				io.stampa(npc.get(0).riceviRegalo(partita.getGiocatore().getBorsa().getAttrezzo(par), partita));
				partita.getGiocatore().getBorsa().removeAttrezzo(par);
			}
			else io.stampa("non hai "+this.getParametro());
		}
		else io.stampa("non ci sono personaggi a cui regalare cose");
	}

	@Override
	public void setParametro(String p) {
		this.par=p;
	}

	@Override
	public String getParametro() {
		return this.par;
	}

}
