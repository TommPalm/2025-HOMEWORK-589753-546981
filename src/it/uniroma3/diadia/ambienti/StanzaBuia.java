package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {

	private String attrezzoLuce;
	
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.attrezzoLuce = attrezzo;
	}

	@Override
	 public String getDescrizione() {
        if(this.hasAttrezzo(attrezzoLuce)) {
        	return this.toString();
        }
        else {
        	return "qui c'è buio pesto";
        }
    }
	
}
