package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class TestLabirinto {

	private Labirinto l= new Labirinto();
	private Stanza a =new Stanza("a");
	private Stanza b =new Stanza("b");
	
	@Test
	public void testGetStanzaVincente_giusto() {
		l.setStanzaVincente(a);
		assertEquals(l.getStanzaVincente(),a);
	}
	
	@Test
	public void testGetStanzaVincente_errato() {
		l.setStanzaVincente(a);
		assertNotEquals(l.getStanzaVincente(),b);
	}

	@Test
	public void testGetStanzaIniziale_giusto() {
		l.setStanzaIniziale(a);
		assertEquals(l.getStanzaIniziale(),a);
	}
	
	@Test
	public void testGetStanzaIniziale_errato() {
		l.setStanzaIniziale(a);
		assertNotEquals(l.getStanzaIniziale(),b);
	}

}
