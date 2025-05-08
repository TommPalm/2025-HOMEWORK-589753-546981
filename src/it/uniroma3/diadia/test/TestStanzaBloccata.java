package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBloccata {

	private Stanza s;
	private StanzaBloccata sb;
	private Attrezzo a;
	private Attrezzo b;
	
	@Before
	public void setUp() {
		s= new Stanza("s");
		sb = new StanzaBloccata("sb","nord","chiave");
		a = new Attrezzo("chiave",1);
		b = new Attrezzo("non chiave",0);
		sb.impostaStanzaAdiacente("nord", s);
	}

	@Test
	public void test_chiaveCorretta() {
		sb.addAttrezzo(a);
		assertEquals(sb.getStanzaAdiacente("nord").getNome(), "s");
	}
	
	@Test
	public void test_chiaveErrata() {
		sb.addAttrezzo(b);
		assertNotEquals(sb.getStanzaAdiacente("nord").getNome(), "s");
		assertEquals(sb.getStanzaAdiacente("nord").getNome(), "sb");
	}
	
	@Test
	public void test_nienteChiave() {
		assertEquals(sb.getStanzaAdiacente("nord").getNome(), "sb");
		assertNotEquals(sb.getStanzaAdiacente("nord").getNome(), "s");
	}

}
