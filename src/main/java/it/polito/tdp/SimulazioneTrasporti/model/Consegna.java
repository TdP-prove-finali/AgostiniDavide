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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comune == null) ? 0 : comune.hashCode());
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
		Consegna other = (Consegna) obj;
		if (comune == null) {
			if (other.comune != null)
				return false;
		} else if (!comune.equals(other.comune))
			return false;
		return true;
	}
	
	

	
	
	

}
