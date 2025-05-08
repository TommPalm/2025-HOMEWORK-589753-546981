package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class TestComandoPosa {

	private Partita p;
	private Stanza s;
	private Attrezzo a;
	private ComandoPosa c;
	
	@Before
	public void setUp(){
		this.p= new Partita();
		this.s= new Stanza("stanza");
		this.a = new Attrezzo("attrezzo",1);
		this.c= new ComandoPosa();
		p.setStanzaCorrente(s);
		p.getGiocatore().getBorsa().addAttrezzo(a);
	}

	@Test
	public void test_haLattrezzo() {
		c.setParametro("attrezzo");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void test_attrezzoSbagliato() {
		c.setParametro("ugdcfukleglh");
		c.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		assertFalse(p.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void test_parametroNull() {
		c.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		assertFalse(p.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}

}
