package it.uniroma3.diadia.personaggi;

import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static String positivo = "Ti stavo cercando, è un onore incontrarti. ti darò una mano *rumore di magia*";
	private static String negativo = "ahh, mi hai spaventata!! *rumore di magia*";
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		
		Stanza corrente = partita.getStanzaCorrente();
		Map<Direzione,Stanza> adiacenti = corrente.getMapStanzeAdiacenti();
		List<Direzione> dir = corrente.getDirezioni();
		
		if(this.haSalutato()) {
			Stanza moltiAtt = corrente;
			int maggiore =0;
			for(Direzione d : dir) {
				if(corrente.getStanzaAdiacente(d).getNumeroAttrezzi()>=maggiore) {
					moltiAtt= adiacenti.get(d);	
					maggiore= moltiAtt.getNumeroAttrezzi();
				}
			}
			partita.setStanzaCorrente(moltiAtt);
			return positivo;
		}
		
		else {
			Stanza pochiAtt = corrente;
			int minore=10000;
			for(Direzione d : dir) {
				if(corrente.getStanzaAdiacente(d).getNumeroAttrezzi()<=minore) {
					pochiAtt = adiacenti.get(d);
					minore = pochiAtt.getNumeroAttrezzi();				}
			}
			partita.setStanzaCorrente(pochiAtt);
			return negativo;
		}
	}

	@Override
	public String riceviRegalo(Attrezzo a, Partita p) {
		return "ahahaha, un regalo. che risate *prende l'oggetto*";
	}

}
