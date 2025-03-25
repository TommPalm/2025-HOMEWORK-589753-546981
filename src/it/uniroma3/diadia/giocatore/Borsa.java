package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private IOConsole io;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
		this.io = new IOConsole();
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			io.stampa("pesa troppo!");
			return false;
		}
		if (this.numeroAttrezzi==10) {
			io.stampa("borsa già piena");
			return false;
		}	
		else {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		// ---> TODO (implementare questo metodo) <---
		if(this.hasAttrezzo(nomeAttrezzo)) {
			for(Attrezzo att : this.attrezzi) {
				//cerchiamo l'attrezzo da eliminare
				if(att!=null) {
					if(att.getNome().equals(nomeAttrezzo)) {
						//scaliamo gli attrezzi
						for(int i=0; i< this.numeroAttrezzi;i++) {
							if(attrezzi[i]==att) {
								attrezzi[i]= attrezzi[i+1];
							}
						}	
					a = att;
					this.numeroAttrezzi = this.numeroAttrezzi-1;
					}
				}
			}
		}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}
