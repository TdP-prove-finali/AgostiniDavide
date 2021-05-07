package it.polito.tdp.SimulazioneTrasporti.model;

public class Comuni implements Comparable<Comuni>{
	
	private int codiceRegione;
	private String codiceComune;
	private String nomeComune;
	private String nomeRegione;
	private int codiceInteroComune;
	/**
	 * @param codiceRegione
	 * @param codiceComune
	 * @param nomeComune
	 * @param nomeRegione
	 * @param codiceInteroComune
	 */
	public Comuni(int codiceRegione, String codiceComune, String nomeComune, String nomeRegione,
			int codiceInteroComune) {
		this.codiceRegione = codiceRegione;
		this.codiceComune = codiceComune;
		this.nomeComune = nomeComune;
		this.nomeRegione = nomeRegione;
		this.codiceInteroComune = codiceInteroComune;
		
	}
	
	

	public int getCodiceRegione() {
		return codiceRegione;
	}
	public void setCodiceRegione(int codiceRegione) {
		this.codiceRegione = codiceRegione;
	}
	public String getCodiceComune() {
		return codiceComune;
	}
	public void setCodiceComune(String codiceComune) {
		this.codiceComune = codiceComune;
	}
	public String getNomeComune() {
		return nomeComune;
	}
	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}
	public String getNomeRegione() {
		return nomeRegione;
	}
	public void setNomeRegione(String nomeRegione) {
		this.nomeRegione = nomeRegione;
	}
	public int getCodiceInteroComune() {
		return codiceInteroComune;
	}
	public void setCodiceInteroComune(int codiceInteroComune) {
		this.codiceInteroComune = codiceInteroComune;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceComune == null) ? 0 : codiceComune.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comuni other = (Comuni) obj;
		if (codiceComune == null) {
			if (other.codiceComune != null)
				return false;
		} else if (!codiceComune.equals(other.codiceComune))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return nomeComune;
	}

	@Override
	public int compareTo(Comuni o) {
		return this.getNomeComune().compareTo(o.getNomeComune());
	}
	
	
	
	
	
	

}
