package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
public class ComandoNonValido extends AbstractComando {

	private IO io = new IOConsole();
 
	@Override
	public void esegui(Partita partita) {
		io.stampa("il comando non esiste o non è valido");

	}

	public String getNome() {
		return "non valido";
	}

}
