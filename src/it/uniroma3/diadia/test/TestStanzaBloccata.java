package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class TestStanzaBloccata {

	private LabirintoBuilder labB = new LabirintoBuilder();
	private Labirinto lab;
	
	@Before
	public void setUp() {
		lab = labB
				.addStanza("s")
				.addStanzaBloccata("sb", Direzione.nord, "chiave")
				.setIniziale()
				.adiacenza("sb", "s", Direzione.nord)
				.getLabirinto();
	}

	@Test
	public void test_chiaveCorretta() {
		lab = labB.addAttrezzo("chiave", 1)
				.getLabirinto();
		assertEquals(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome(),"s");
	}
	
	@Test
	public void test_chiaveErrata() {
		lab = labB.addAttrezzo("non chiave", 0)
				.getLabirinto();
		assertNotEquals(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome(), "s");
		assertEquals(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome(), "sb");
	}
	
	@Test
	public void test_nienteChiave() {
		assertEquals(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome(), "sb");
		assertNotEquals(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.nord).getNome(), "s");
	}

}
