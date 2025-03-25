package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class TestStanza {
	
	private Stanza stanza1 = new Stanza("stanza1");
	private Stanza s2 = new Stanza("s2");
	private Attrezzo a1= new Attrezzo("lavagna",10);
	private Attrezzo a2= new Attrezzo("scrivania",2);
	
	//da togliere
	private Attrezzo a3= new Attrezzo("3",10);
	private Attrezzo a4= new Attrezzo("4",10);
	private Attrezzo a5= new Attrezzo("5",10);
	private Attrezzo a6= new Attrezzo("6",10);
	private Attrezzo a7= new Attrezzo("7",10);
	private Attrezzo a8= new Attrezzo("8",10);
	private Attrezzo a9= new Attrezzo("9",10);
	private Attrezzo a0= new Attrezzo("0",10);
   
	@Test
	public void testCostruttore() {
		assertEquals("stanza1", this.stanza1.getNome());
	}
	
	@Test
	public void testGetStanzaAdiacente_nessunaAdiacente() {
		assertEquals(stanza1.getStanzaAdiacente("nord"),null);
		assertEquals(stanza1.getStanzaAdiacente("sud"),null);
		assertEquals(stanza1.getStanzaAdiacente("est"),null);
		assertEquals(stanza1.getStanzaAdiacente("ovest"),null);
	}
	
	@Test
	public void testGetStanzaAdiacente_adiacente() {
		stanza1.impostaStanzaAdiacente("nord", s2);
		assertEquals(stanza1.getStanzaAdiacente("nord"),s2);
		
	}
	
	@Test 
	public void testGetStanzaAdiacente_NonAdiacente() {
		stanza1.impostaStanzaAdiacente("nord", s2);
		assertNotEquals(stanza1.getStanzaAdiacente("sud"),s2);
	}

	@Test
	public void testGetAttrezzi_nessunAttrezzo() {
		assertEquals(stanza1.getAttrezzo("lavagna"),null);
	}
	
	@Test
	public void testGetAttrezzi_attrezzoPresente() {
		stanza1.addAttrezzo(a1);
		assertEquals(stanza1.getAttrezzo("lavagna"),a1);
	}
	
	@Test
	public void testGetAttrezzi_attrezzoNonPresente() {
		stanza1.addAttrezzo(a1);
		assertNotEquals(stanza1.getAttrezzo("scrivania"),a1);
	}
	
	@Test 
	public void testHasAttrezzo() {
		stanza1.addAttrezzo(a1);
		assertTrue(stanza1.hasAttrezzo("lavagna"));
	}
	
	@Test 
	public void testGetDirezione() {
		stanza1.impostaStanzaAdiacente("nord", s2);
		String[] dir = new String[1];
		dir[0]="nord";
		String[] dir2 = new String[1];
		assertNotEquals(stanza1.getDirezioni(),dir2);
		assertEquals(stanza1.getDirezioni(),dir);
	}
	
	@Test 
	public void testRemoveAttrezzo() {
		stanza1.addAttrezzo(a1);
		stanza1.addAttrezzo(a2);
		assertTrue(stanza1.removeAttrezzo(a1));
		assertFalse(stanza1.hasAttrezzo("lavagna"));
	}
	
	@Test 
	public void testRemoveAttrezzo_numeroMassimo() {
		stanza1.addAttrezzo(a1);
		stanza1.addAttrezzo(a2);
		stanza1.addAttrezzo(a3);
		stanza1.addAttrezzo(a4);
		stanza1.addAttrezzo(a5);
		stanza1.addAttrezzo(a6);
		stanza1.addAttrezzo(a7);
		stanza1.addAttrezzo(a8);
		stanza1.addAttrezzo(a9);
		stanza1.addAttrezzo(a0);
		Attrezzo b = new Attrezzo("b", 0);
		assertFalse(stanza1.addAttrezzo(b));
		stanza1.removeAttrezzo(a4);
		assertTrue(stanza1.addAttrezzo(b));
	}
	
}
