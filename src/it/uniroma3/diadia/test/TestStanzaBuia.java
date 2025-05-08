package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBuia {

	private Attrezzo a;
	private Attrezzo b;
	private StanzaBuia s;
	
	@Before
	public void setUp(){
		a = new Attrezzo("luce",1);
		b = new Attrezzo("non luce",0);
		s = new StanzaBuia("s","luce");
	}

	@Test
	public void test_getDescrizione_attrezzoLuminoso() {
		s.addAttrezzo(a);
		assertEquals(s.getDescrizione(),s.toString());
	}
	
	@Test
	public void test_getDescrizione_attrezzoNonLuminoso() {
		s.addAttrezzo(b);
		assertNotEquals(s.getDescrizione(),s.toString());
	}
	
	@Test
	public void test_getDescrizione_attrezzoAssente() {
		assertNotEquals(s.getDescrizione(),s.toString());
	}

}
