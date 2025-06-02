package it.uniroma3.diadia.test;

import static org.junit.Assert.*;


import org.junit.Test;
import it.uniroma3.diadia.comandi.*;

public class TestFabbricaDiComandiRiflessiva {

	private FabbricaDiComandiRiflessiva f = new FabbricaDiComandiRiflessiva();
	
	@Test
	public void test_Vai() throws Exception {
		Comando c = f.costruisciComando("vai nord");
		assertTrue(c.getNome().equals("vai"));
		assertTrue(c.getParametro().equals("nord"));
	}
	@Test
	public void test_prendi() throws Exception{
		Comando c = f.costruisciComando("prendi attrezzo");
		assertTrue(c.getNome().equals("prendi"));
		assertTrue(c.getParametro().equals("attrezzo"));
	}
	@Test
	public void test_posa() throws Exception{
		Comando c = f.costruisciComando("posa attrezzo");
		assertTrue(c.getNome().equals("posa"));
		assertTrue(c.getParametro().equals("attrezzo"));
	}
	@Test
	public void test_guarda() throws Exception{
		Comando c = f.costruisciComando("guarda");
		assertTrue(c.getNome().equals("guarda"));
		assertNull(c.getParametro());
	}
	@Test
	public void test_aiuto() throws Exception{
		Comando c = f.costruisciComando("aiuto");
		assertTrue(c.getNome().equals("aiuto"));
		assertNull(c.getParametro());
	}
	@Test
	public void test_fine() throws Exception{
		Comando c = f.costruisciComando("fine");
		assertTrue(c.getNome().equals("fine"));
		assertNull(c.getParametro());
	}
	@Test
	public void test_nonValido() throws Exception{
		Comando c = f.costruisciComando("gdfbtrb");
		assertTrue(c.getNome().equals("non valido"));
		assertNull(c.getParametro());
	}

}
