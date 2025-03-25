package it.uniroma3.diadia;
import java.util.Scanner;
public class IOConsole {
	
	public void stampa(String messaggio) {
		System.out.println(messaggio);
	}
	
	public String leggi() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
	//	scannerDiLinee.close();
		return riga;
	}

}
