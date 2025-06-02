package it.uniroma3.diadia.personaggi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.comparaAttrezziPerNome;
import it.uniroma3.diadia.attrezzi.comparaAttrezziPerPesoENome;

public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String,Attrezzo> attrezzi = new HashMap<>();
	private int pesoMax;
	private int pesoAttuale;
	private IOConsole io= new IOConsole();

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
		this.pesoAttuale=0;
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.pesoAttuale=0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo==null) {
			return false;
		}
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			io.stampa("pesa troppo!");
			return false;
		}	
		else {
			this.attrezzi.put(attrezzo.getNome(),attrezzo);
			this.pesoAttuale+= attrezzo.getPeso();
			//this.numeroAttrezzi++;
			return true;
		}
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.attrezzi.containsKey(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);
		}
		return a;
	}

	public int getPeso() {
		return this.pesoAttuale;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato= false;
		if(this.attrezzi.containsKey(nomeAttrezzo))
			trovato = true;
		return trovato;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.hasAttrezzo(nomeAttrezzo)) {
			a = this.attrezzi.get(nomeAttrezzo);
			this.attrezzi.remove(nomeAttrezzo);
			this.pesoAttuale-= a.getPeso();
		}
		return a;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			List<Attrezzo> list = new ArrayList<>(this.attrezzi.values());
			for (Attrezzo a : list)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	//ordina per peso, a parità di peso per nome
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> list = new ArrayList<>(this.attrezzi.values());
		comparaAttrezziPerPesoENome cm = new comparaAttrezziPerPesoENome();
		Collections.sort(list,cm);
		return list;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> set = new TreeSet<>(new comparaAttrezziPerNome());
		set.addAll(this.attrezzi.values());
		return set;
	}
	
	public 	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		List<Attrezzo> list = new ArrayList<>(this.attrezzi.values());
		Map<Integer,Set<Attrezzo>> map = new HashMap<>();
		for(Attrezzo a : list) {
			if(map.containsKey(a.getPeso())) {
				map.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo> set = new HashSet<>();
				set.add(a);
				map.put(a.getPeso(), set);
			}
		}	
		return map;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> set = new TreeSet<>(new comparaAttrezziPerPesoENome());
		set.addAll(this.attrezzi.values());
		return set;
	}
}
