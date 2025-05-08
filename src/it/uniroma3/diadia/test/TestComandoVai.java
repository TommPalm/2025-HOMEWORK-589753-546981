package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;

public class TestComandoVai {

	private Partita p = new Partita();
	private Stanza s1;
	private Stanza s2;
	private ComandoVai c;
	
	@Before
	public void setTest() {
		this.s1= new Stanza("inizio");
		this.s2= new Stanza("successivo");
		s1.impostaStanzaAdiacente("nord", s2);
		p.setStanzaCorrente(s1);
		this.c= new ComandoVai();
	}
	
	@Test
	public void test_direzioneCorretta() {
		c.setParametro("nord");
		c.esegui(p);
		assertTrue(p.getStanzaCorrente().equals(s2));
	}
	
	@Test
	public void test_direzioneNull() {
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().equals(s2));
		assertTrue(p.getStanzaCorrente().equals(s1));
	}
	
	@Test
	public void test_direzioneErrata() {
		c.setParametro("sud");
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().equals(s2));
		assertTrue(p.getStanzaCorrente().equals(s1));
	}

}
