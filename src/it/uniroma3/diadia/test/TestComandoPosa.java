package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class TestComandoPosa {

	private Partita p;
	private ComandoPosa c;
	private Attrezzo a;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException{
		Labirinto l = Labirinto.builder("labirintoPerTest_comandoPosa.txt").getLabirinto();
		this.p= new Partita(l);
		this.c= new ComandoPosa();
		a = new Attrezzo("attrezzo",1);
		p.getGiocatore().getBorsa().addAttrezzo(a);
	}

	@Test
	public void test_attrezzoPresente() {
		c.setParametro("attrezzo");
		c.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("attrezzo"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("attrezzo"));
	}
	
	@Test
	public void test_attrezzoAssente() {
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
