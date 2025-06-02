package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.*;

public class TestLabirinto {

	private Labirinto l;;
	
	@Before
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l = Labirinto.builder("labirintoPerTest_labirinto.txt").getLabirinto();
	}
	
	@Test
	public void testGetStanzaVincente_giusto() {
		assertEquals(l.getStanzaVincente().getNome(),"finale");
	}
	
	@Test
	public void testGetStanzaVincente_errato() {
		assertNotEquals(l.getStanzaVincente().getNome(),"iniziale");
	}

	@Test
	public void testGetStanzaIniziale_giusto() {
		assertEquals(l.getStanzaIniziale().getNome(),"iniziale");
	}
	
	@Test
	public void testGetStanzaIniziale_errato() {
		assertNotEquals(l.getStanzaIniziale().getNome(),"finale");
	}

}
