package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
public class ComandoFine implements Comando {

	private IO io = new IOConsole();

	@Override
	public void esegui(Partita partita) {
		io.stampa("Grazie di aver giocato!");
		partita.setFinita();

	}

	@Override
	public void setParametro(String parametro) {
		// inutile per questo comando
	}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		return null;
	}

}