package it.uniroma3.diadia.comandi;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Borsa;

public class ComandoGuardaBorsa extends AbstractComando{

	private IO io = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		Borsa b = partita.getGiocatore().getBorsa();
		
		//stampa la lista ordinata per peso
		List<Attrezzo> l = b.getContenutoOrdinatoPerPeso();
		StringBuilder str = new StringBuilder();
		for(Attrezzo a : l) {
			str.append(a.getNome()+" "+a.getPeso()+"Kg, ");
		}
		io.stampa("[ "+str.toString()+"]");
		
		//stampa il set ordinato per nome
		StringBuilder strN = new StringBuilder();
		l = new ArrayList<>(b.getContenutoOrdinatoPerNome());
		for(Attrezzo a : l) {
			strN.append(a.getNome()+", ");
		}
		io.stampa("{ "+strN.toString()+"}");
		
		
		//stampa la mappa raggruppando gli oggetti per peso
		List<Integer> pesi = new ArrayList<>(b.getContenutoRaggruppatoPerPeso().keySet());
		StringBuilder strM = new StringBuilder();
		for(int i : pesi) {
			l = new ArrayList<>(b.getContenutoRaggruppatoPerPeso().get(i) );
			strM.append(i+", { ");
			for(Attrezzo a : l) {
				strM.append(a.getNome()+", ");
			}
			strM.append("} ");
		}
		io.stampa("( "+strM+" )");	
	}

	public String getNome() {
		return "guardaBorsa";
	}


}
