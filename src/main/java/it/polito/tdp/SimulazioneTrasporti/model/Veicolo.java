package it.polito.tdp.SimulazioneTrasporti.model;

import java.util.List;

public class Veicolo {

	private int idVeicolo;
	private List<Consegna> listaConsegna;
	/**
	 * @param idVeicolo
	 * @param listaConsegna
	 */
	public Veicolo(int idVeicolo, List<Consegna> listaConsegna) {
		super();
		this.idVeicolo = idVeicolo;
		this.listaConsegna = listaConsegna;
	}
	
	
	public int getIdVeicolo() {
		return idVeicolo;
	}
	public void setIdVeicolo(int idVeicolo) {
		this.idVeicolo = idVeicolo;
	}
	public List<Consegna> getListaConsegna() {
		return listaConsegna;
	}
	public void setListaConsegna(List<Consegna> listaConsegna) {
		this.listaConsegna = listaConsegna;
	}

	
	
	
}
