package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class TestPartita {
	
	private Partita p = new Partita();
	private Stanza s1 = new Stanza("prima");
	private Stanza s2=new Stanza("fine");

	@Test
	public void testGetStanzaVincente() {
		p.getLabirinto().setVincente(s2);
		assertEquals(p.getStanzaVincente(),s2);
	}
	
	@Test
	public void testGetStanzaVincente_falso() {
		assertNotEquals(p.getStanzaVincente(),s1);
	}

	
	@Test
	public void testSetStanzaCorrente() {
		p.setStanzaCorrente(s1);
		assertEquals(p.getStanzaCorrente(),s1);
	}
	
	@Test
	public void testSetStanzaCorrente_falso() {
		assertNotEquals(p.getStanzaCorrente(),s1);
	}
	
	@Test
	public void testSetStanzaCorrente_aggiorna() {
		p.setStanzaCorrente(s1);
		p.setStanzaCorrente(s2);
		assertTrue(p.getStanzaCorrente()==s2);
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
		p.getLabirinto().setVincente(s1);
		p.setStanzaCorrente(s1);
		assertTrue(p.isFinita());
	}
	
	@Test
	public void testIsFinita_NonVinta() {
		p.getLabirinto().setVincente(s1);
		p.setStanzaCorrente(s2);
		assertFalse(p.isFinita());
	}
}
