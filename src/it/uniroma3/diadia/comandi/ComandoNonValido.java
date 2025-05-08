package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
public class ComandoNonValido implements Comando {

	private IO io = new IOConsole();

	@Override
	public void esegui(Partita partita) {
		io.stampa("il comando non esiste o non è valido");

	}

	@Override
	public void setParametro(String parametro) {
		// inutile

	}

	@Override
	public String getNome() {
		return "non valido";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
