package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class TestComandoPrendi {

	private Partita p;
	private Stanza s;
	private Attrezzo a1;
	private Attrezzo a2;
	private ComandoPrendi c;
	
	
	@Before
	public void setUp(){
		this.p= new Partita();
		this.s= new Stanza("stanza");
		this.a1 = new Attrezzo("1",1);
		this.a2 = new Attrezzo("2",11);
		this.c= new ComandoPrendi();
		p.setStanzaCorrente(s);
		s.addAttrezzo(a1);
		s.addAttrezzo(a2);
	}

	@Test
	public void test_parametroNull() {
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertTrue(s.hasAttrezzo("1"));	
		assertTrue(s.hasAttrezzo("2"));	
	}
	
	@Test
	public void test_parametroCorretto_preso() {
		c.setParametro("1");
		c.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertFalse(s.hasAttrezzo("1"));	
		assertTrue(s.hasAttrezzo("2"));	
	}
	
	@Test
	public void test_parametroCorretto_nonPreso() { //l'attrezzo è troppo pesante
		c.setParametro("2");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertTrue(s.hasAttrezzo("1"));	
		assertTrue(s.hasAttrezzo("2"));	
	}
	
	@Test
	public void test_parametroErrato() { //l'attrezzo è troppo pesante
		c.setParametro("");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertTrue(s.hasAttrezzo("1"));	
		assertTrue(s.hasAttrezzo("2"));	
	}

}
