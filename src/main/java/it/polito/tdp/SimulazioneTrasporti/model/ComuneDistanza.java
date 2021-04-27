package it.polito.tdp.SimulazioneTrasporti.model;

public class ComuneDistanza implements Comparable<ComuneDistanza>{

	private Comuni comune;
	private Double minuti;
	/**
	 * @param comune
	 * @param minuti
	 */
	public ComuneDistanza(Comuni comune, Double minuti) {
		this.comune = comune;
		this.minuti = minuti;
	}
	public Comuni getComune() {
		return comune;
	}
	public void setComune(Comuni comune) {
		this.comune = comune;
	}
	public Double getMinuti() {
		return minuti;
	}
	public void setMinuti(double minuti) {
		this.minuti = minuti;
	}
	@Override
	public int compareTo(ComuneDistanza o) {
		return (this.minuti.compareTo(o.getMinuti()));
	}
	
	
}
