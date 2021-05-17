package it.polito.tdp.SimulazioneTrasporti.model;

public class Collegamento {
	
	private int origine;
	private int destinazione;
	private double peso;
	
	public Collegamento(int origine, int destinazione, double peso) {
		this.origine = origine;
		this.destinazione = destinazione;
		this.peso = peso;
	}
	public int getOrigine() {
		return origine;
	}
	public void setOrigine(int origine) {
		this.origine = origine;
	}
	public int getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(int destinazione) {
		this.destinazione = destinazione;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	
	
	

}
