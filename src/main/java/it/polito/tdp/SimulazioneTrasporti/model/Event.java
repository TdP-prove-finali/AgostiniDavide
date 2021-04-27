package it.polito.tdp.SimulazioneTrasporti.model;

public class Event implements Comparable<Event>{

	public enum EventType {
		CONSEGNA_IN_CORSO,
		CONSEGNA_EFFETTUATA,
	}
	
	private Double time ;
	private EventType type ;
	private Veicolo veicolo;
	private Comuni comune;
	

	/**
	 * @param time
	 * @param type
	 * @param veicolo
	 * @param comune
	 */
	public Event(Double time, EventType type, Veicolo veicolo, Comuni comune) {
		super();
		this.time = time;
		this.type = type;
		this.veicolo = veicolo;
		this.comune = comune;
	}

	
	public Double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public EventType getType() {
		return type;
	}


	public void setType(EventType type) {
		this.type = type;
	}


	public Veicolo getVeicolo() {
		return veicolo;
	}


	public void setVeicolo(Veicolo veicolo) {
		this.veicolo = veicolo;
	}


	public Comuni getComune() {
		return comune;
	}


	public void setComune(Comuni comune) {
		this.comune = comune;
	}


	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.time.compareTo(o.getTime());
	}
	
	
	
	
}
