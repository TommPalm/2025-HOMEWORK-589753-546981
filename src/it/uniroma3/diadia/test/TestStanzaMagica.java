package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaMagica {
	
	private StanzaMagica sm;
	private Attrezzo a;
	private Attrezzo b;

	@Before
	public void setUp(){
		sm = new StanzaMagica("sm",1);
		a = new Attrezzo("att",2);
	}

	/*non modifica a prescindere, ma se è sotto la soglia rimane immutato*/
	@Test
	public void test_sottoSoglia() {
		sm.addAttrezzo(a);
		b = sm.getAttrezzo(a.getNome());
		assertTrue(b.getPeso()==2);
	}
	
	@Test
	public void test_sopraSoglia() {
		sm.addAttrezzo(a);
		sm.addAttrezzo(a);
		b = sm.getAttrezzo("tta");  //prende il nome invertito perchè ha superato la soglia
		assertTrue(b.getPeso()==4);
	}

}
