package it.uniroma3.diadia;


import java.util.Scanner;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	
	private Partita partita;

	public DiaDia(Labirinto lab) {
		this.partita = new Partita(lab);
	}

	public void gioca() throws Exception {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
		scannerDiLinee.close();
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		 //FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
		System.out.println("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
		System.out.println("Hai esaurito i CFU...");
		return this.partita.isFinita();
		}

	public static void main(String[] argc) throws Exception {
		
		//ogni stanza può avere massimo un personaggio
		Labirinto lab = Labirinto.builder("labirinto.txt").getLabirinto();
				/*
				.addStanzaBloccata("atrio",Direzione.nord,"lasciapassare")
				.setIniziale()
				.addStanza("biblioteca")
				.setVincente()
				.addStanzaMagica("n11",0)
				.addStanzaBuia("laboratorio", "torcia")
				.addStrega("nocciola", "sono la potente strega del laboratorio")
				.addAttrezzo("lasciapassare", 1)
				.addStanza("n10")
				.addCane("botolo", "whooof", "palla",2,"osso")
				.addAttrezzo("torcia", 2)
				.addStanza("corridoio")
				.addStanza("cortile")
				.addMago("merlino", "salve, sono il generoso mago merlino", "acqua", 3)
				.addAttrezzo("osso", 5)
				
				.adiacenza("atrio", "biblioteca", Direzione.nord)
				.adiacenza("atrio","cortile",Direzione.sud)
				.adiacenza("atrio", "corridoio", Direzione.ovest)
				.adiacenza("cortile", "atrio", Direzione.nord)
				.adiacenza("cortile", "laboratorio",Direzione.ovest)
				.adiacenza("biblioteca", "atrio", Direzione.sud)
				.adiacenza("n10", "atrio", Direzione.est)
				.adiacenza("n10", "corridoio", Direzione.sud)
				.adiacenza("n11", "corridoio", Direzione.sud)
				.adiacenza("corridoio", "n11", Direzione.ovest)
				.adiacenza("corridoio", "n10", Direzione.nord)
				.adiacenza("corridoio", "atrio", Direzione.est)
				.adiacenza("corridoio", "laboratorio", Direzione.sud)
				.adiacenza("laboratorio", "corridoio", Direzione.nord)
				.adiacenza("laboratorio", "cortile", Direzione.est)
				.getLabirinto();*/
		DiaDia gioco = new DiaDia(lab);
		gioco.gioca();
	}
}