package it.polito.tdp.SimulazioneTrasporti.model;

public class Consegna {
	
	private Comuni comune;
	private double time;
	/**
	 * @param comune
	 * @param time
	 */
	public Consegna(Comuni comune, double time) {
		super();
		this.comune = comune;
		this.time = time;
	}
	public Comuni getComune() {
		return comune;
	}
	public void setComune(Comuni comune) {
		this.comune = comune;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	

	
	
	

}
