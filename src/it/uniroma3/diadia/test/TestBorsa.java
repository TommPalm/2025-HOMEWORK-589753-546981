package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestBorsa {

	private Borsa b= new Borsa();
	private Attrezzo a1= new Attrezzo("leggero",1);
	private Attrezzo a2= new Attrezzo("pieno",10);
	private Attrezzo a3= new Attrezzo("medio",9);
	

	@Test
	public void testAddAttrezzo_borsaVuota() {
		assertTrue(b.addAttrezzo(a1));
	}
	
	@Test
	public void testAddAttrezzo_borsaPiena() {
		b.addAttrezzo(a2);
		assertFalse(b.addAttrezzo(a1));
	}
	
	@Test
	public void testAddAttrezzo_borsaMedia() {
		b.addAttrezzo(a1);
		assertTrue(b.addAttrezzo(a3));
	}

	@Test
	public void testGetPeso_vuoto() {
		assertEquals(b.getPeso(),0);
	}

	@Test
	public void testGetPeso_pieno() {
		b.addAttrezzo(a2);
		assertEquals(b.getPeso(),10);
	}

	
	@Test
	public void testIsEmpty_vero() {
		assertTrue(b.isEmpty());
	}
	
	@Test
	public void testIsEmpty_falso() {
		b.addAttrezzo(a1);
		assertFalse(b.isEmpty());
	}


	@Test
	public void testHasAttrezzo_vuoto() {
		assertFalse(b.hasAttrezzo("pieno"));
	}
	
	@Test
	public void testHasAttrezzo_pieno_attrezzoGiusto() {
		b.addAttrezzo(a2);
		assertTrue(b.hasAttrezzo("pieno"));
	}
	
	@Test
	public void testHasAttrezzo_pieno_attrezzoSbagliato() {
		b.addAttrezzo(a2);
		assertFalse(b.hasAttrezzo("leggero"));
	}

	@Test
	public void testRemoveAttrezzo_borsaVuota() {
		assertNull(b.removeAttrezzo("b"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		b.addAttrezzo(a3);
		b.addAttrezzo(a1);
		assertEquals(b.removeAttrezzo("medio"),a3);
		assertFalse(b.hasAttrezzo("medio"));
	}

	
	
	
	
	
	
	
	
	
	
	
}
