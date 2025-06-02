package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.comandi.ComandoVai;

public class TestComandoVai {

	private Partita p ;
	private ComandoVai c;
	
	@Before
	public void setTest() throws FileNotFoundException, FormatoFileNonValidoException {
		Labirinto lab = Labirinto.builder("labirintoPerTest_comandoVai.txt").getLabirinto();
		p = new Partita(lab);
		this.c= new ComandoVai();
	}
	
	@Test
	public void test_direzioneCorretta() {
		c.setParametro("nord");
		c.esegui(p);
		assertTrue(p.getStanzaCorrente().getNome().equals("finale"));
	}
	
	@Test
	public void test_direzioneNull() {
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().getNome().equals("finale"));
		assertTrue(p.getStanzaCorrente().getNome().equals("iniziale"));
	}
	
	@Test
	public void test_direzioneErrata() {
		c.setParametro("sud");
		c.esegui(p);
		assertFalse(p.getStanzaCorrente().getNome().equals("finale"));
		assertTrue(p.getStanzaCorrente().getNome().equals("iniziale"));
	}

}
