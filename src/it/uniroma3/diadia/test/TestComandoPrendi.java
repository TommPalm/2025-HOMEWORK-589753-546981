package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class TestComandoPrendi {

	private Partita p;
	private ComandoPrendi c;
	
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		Labirinto lab = Labirinto.builder("labirintoPerTest_comandoPrendi.txt").getLabirinto();
		this.p= new Partita(lab);
		this.c= new ComandoPrendi();
	}

	@Test
	public void test_parametroNull() {
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("1"));	
		assertTrue(p.getStanzaCorrente().hasAttrezzo("2"));	
	}
	
	@Test
	public void test_parametroCorretto_preso() {
		c.setParametro("1");
		c.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertFalse(p.getStanzaCorrente().hasAttrezzo("1"));	
		assertTrue(p.getStanzaCorrente().hasAttrezzo("2"));	
	}
	
	@Test
	public void test_parametroCorretto_nonPreso() { //l'attrezzo è troppo pesante
		c.setParametro("2");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("1"));	
		assertTrue(p.getStanzaCorrente().hasAttrezzo("2"));	
	}
	
	@Test
	public void test_parametroErrato() { //l'attrezzo è troppo pesante
		c.setParametro("");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("1"));
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("2"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("1"));	
		assertTrue(p.getStanzaCorrente().hasAttrezzo("2"));	
	}

}
