package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

public class TestGiocatore {

	private Giocatore g1 =new Giocatore();
	
	@Test
	public void testGetCfu_iniziali() {
		assertEquals(g1.getCfu(),20);
	}
	
	@Test
	public void testGetCfu_settati() {
		g1.setCfu(2);
		assertEquals(g1.getCfu(),2);
	}
	
	@Test
	public void testGetCfu_errati() {
		g1.setCfu(1003555);
		assertNotEquals(g1.getCfu(),20);
	}

}
