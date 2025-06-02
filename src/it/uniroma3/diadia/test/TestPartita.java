package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

public class TestPartita {
	
	private Partita p;
	private Stanza s1;
	private Stanza s2;
	
	@Before
	public void setUp() {
		p = new Partita(new Labirinto());
		s1 = new Stanza("prima");
		s2 = new Stanza("fine");
		p.getLabirinto().setStanzaVincente(s2);
		p.getLabirinto().setStanzaIniziale(s1);
		p.setStanzaCorrente(s1);
	}

	@Test
	public void testGetStanzaVincente() {
		assertEquals(p.getStanzaVincente(),s2);
	}
	
	@Test
	public void testGetStanzaVincente_falso() {
		assertNotEquals(p.getStanzaVincente(),s1);
	}

	@Test
	public void testSetStanzaCorrente_vero() {
		assertEquals(p.getStanzaCorrente(),s1);
	}
	
	@Test
	public void testSetStanzaCorrente_falso() {
		assertNotEquals(p.getStanzaCorrente(),s2);
	}
	
	@Test
	public void testSetStanzaCorrente_aggiorna() {
		p.setStanzaCorrente(s2);
		assertEquals(p.getStanzaCorrente(),s2);
	}

	
	@Test
	public void testIsFinita_nonFinita() {
		assertFalse(p.isFinita());
	}
	
	@Test
	public void testIsFinita_Finita() {
		p.setFinita();
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testIsFinita_CFUfiniti() {
		p.setCfu(0);
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testIsFinita_Vinta() {
		p.setStanzaCorrente(s2);
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testIsFinita_NonVinta() {
		assertFalse(p.isFinita());
	}
}
