package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
public class ComandoFine extends AbstractComando {


	private IO io = new IOConsole();
 
	@Override
	public void esegui(Partita partita) {
		io.stampa("Grazie di aver giocato!");
		partita.setFinita();

	}

	public String getNome() {
		return "fine";
	}

}