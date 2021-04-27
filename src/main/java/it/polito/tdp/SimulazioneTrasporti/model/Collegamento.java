package it.polito.tdp.SimulazioneTrasporti.model;

public class Collegamento {
	
	private String origine;
	private String destinazione;
	private double peso;
	/**
	 * @param origine
	 * @param destinazione
	 * @param peso
	 */
	public Collegamento(String origine, String destinazione, double peso) {
		this.origine = origine;
		this.destinazione = destinazione;
		this.peso = peso;
	}
	
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public String getDestinazione() {
		return destinazione;
	}
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
