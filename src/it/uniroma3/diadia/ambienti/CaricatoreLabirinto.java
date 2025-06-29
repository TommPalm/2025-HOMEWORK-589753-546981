package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	private static final String STANZE_MARKER = "Stanze:";             

	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  	

	private static final String STANZE_BUIE_MARKER = "Buia:";  

	private static final String STANZE_BLOCCATE_MARKER = "Bloccata:";  

	private static final String STANZE_MAGICHE_MARKER = "Magica:";  

	private static final String PERSONAGGI_MARKER_MAGO = "Mago:";

	private static final String PERSONAGGI_MARKER_STREGA = "Strega:";

	private static final String PERSONAGGI_MARKER_CANE = "Cane:";

	private static final String ATTREZZI_MARKER = "Attrezzi:";

	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private BufferedReader reader;

	private Map<String, Stanza> nome2stanza;
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Integer toInt;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECreaMaghi();
			this.leggiECreaCani();
			this.leggiECreaStreghe();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} 
		finally {
			try {
				reader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}


	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} 
		catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}


	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {

			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+" non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per vedere la stanza "+specifica+"\n"));
					String attrezzoPerVedere = scannerDiLinea.next();

					Stanza stanza = new StanzaBuia(nomeStanza, attrezzoPerVedere);
					this.nome2stanza.put(nomeStanza, stanza);
				}
			}
		} 

	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {

			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+" non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  direzione della stanza"+ specifica+" non esiste\n"));
					Direzione direzione = Direzione.valueOf(scannerDiLinea.next());
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema nella creazione dell'attrezzo per sbloccare la stanza "+specifica+"\n"));
					String attrezzoSbloccante = scannerDiLinea.next();

					Stanza stanza = new StanzaBloccata(nomeStanza, direzione, attrezzoSbloccante);
					this.nome2stanza.put(nomeStanza, stanza);
				}
			}
		} 
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_MAGO);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {

			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il mago non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del mago ...\n"));
					String mago = scannerDiLinea.next();			
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema con il nome dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String attrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("vi è stato qualche problema con il peso dell'attrezzo per il mago della stanza "+specifica+"\n"));
					String peso = scannerDiLinea.next();
					toInt= Integer.parseInt(peso);
					
					StringBuilder ss = new StringBuilder();
					while(scannerDiLinea.hasNext()) {
						ss.append(scannerDiLinea.next()+" ");
					}
					String presentazione = ss.toString();

					Mago personaggio = new Mago(mago, presentazione, attrezzo, toInt);
					this.nome2stanza.get(nomeStanza).addMago(personaggio);
				}
			}
		} 
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_STREGA);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {

			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere la strega non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione della strega ...\n"));
					String strega = scannerDiLinea.next();
					StringBuilder ss = new StringBuilder();
					while(scannerDiLinea.hasNext()) {
						ss.append(scannerDiLinea.next()+" ");
					}
					String presentazione = ss.toString();

					Strega personaggio = new Strega(strega, presentazione);
					this.nome2stanza.get(nomeStanza).addStrega(personaggio);
				}
			}
		} 
	}

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheStanze = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER_CANE);
		for(String specifica : separaStringheAlleVirgole(specificheStanze)) {

			try (Scanner scannerDiLinea = new Scanner(specifica)) 	{	
				while (scannerDiLinea.hasNext()) {

					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la  stanza "+ specifica+"per aggiungere il cane non esiste\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("problemini nella creazione del cane ...\n"));
					String cane = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica la presentazione del cane\n"));
					String presentazione = scannerDiLinea.next();					
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica il cibo preferito del cane\n"));
					String ciboPreferito = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica il regalo del cane\n"));
					String regalo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("specifica il peso del regalo del cane\n"));
					String peso = scannerDiLinea.next();
					int Peso = Integer.parseInt(peso);

					Cane personaggio = new Cane(cane, presentazione,regalo,Peso, ciboPreferito);
					this.nome2stanza.get(nomeStanza).addCane(personaggio);
				}
			}
		} 
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specifiche : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specifiche)) 	{	
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
				}
			}
		} 
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(Direzione.valueOf(dir), arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + ((LineNumberReader) this.reader).getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
