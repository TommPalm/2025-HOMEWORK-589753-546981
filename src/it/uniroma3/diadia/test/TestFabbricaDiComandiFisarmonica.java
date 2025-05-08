package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoAiuto;
import it.uniroma3.diadia.comandi.ComandoFine;
import it.uniroma3.diadia.comandi.ComandoGuarda;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class TestFabbricaDiComandiFisarmonica {

	private Partita p = new Partita();
	private FabbricaDiComandiFisarmonica f = new FabbricaDiComandiFisarmonica();
	
	@Test
	public void test_Vai() {
		Comando c = f.costruisciComando("vai nord");
		assertTrue(c.getNome().equals("vai"));
		assertTrue(c.getParametro().equals("nord"));
	}
	@Test
	public void test_prendi() {
		Comando c = f.costruisciComando("prendi attrezzo");
		assertTrue(c.getNome().equals("prendi"));
		assertTrue(c.getParametro().equals("attrezzo"));
	}
	@Test
	public void test_posa() {
		Comando c = f.costruisciComando("posa attrezzo");
		assertTrue(c.getNome().equals("posa"));
		assertTrue(c.getParametro().equals("attrezzo"));
	}
	@Test
	public void test_guarda() {
		Comando c = f.costruisciComando("guarda");
		assertTrue(c.getNome().equals("guarda"));
		assertNull(c.getParametro());
	}
	@Test
	public void test_aiuto() {
		Comando c = f.costruisciComando("aiuto");
		assertTrue(c.getNome().equals("aiuto"));
		assertNull(c.getParametro());
	}
	@Test
	public void test_fine() {
		Comando c = f.costruisciComando("fine");
		assertTrue(c.getNome().equals("fine"));
		assertNull(c.getParametro());
	}
	@Test
	public void test_nonValido() {
		Comando c = f.costruisciComando("gdfbtrb");
		assertTrue(c.getNome().equals("non valido"));
		assertNull(c.getParametro());
	}

}
