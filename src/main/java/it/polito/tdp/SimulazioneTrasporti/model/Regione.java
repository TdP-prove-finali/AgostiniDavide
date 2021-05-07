package it.polito.tdp.SimulazioneTrasporti.model;

public class Regione implements Comparable<Regione>{
	
		private int codiceRegione;
		private String nomeRegione;
		/**
		 * @param codiceRegione
		 * @param nomeRegione
		 */
		public Regione(int codiceRegione, String nomeRegione) {
			this.codiceRegione = codiceRegione;
			this.nomeRegione = nomeRegione;
		}
		public int getCodiceRegione() {
			return codiceRegione;
		}
		public void setCodiceRegione(int codiceRegione) {
			this.codiceRegione = codiceRegione;
		}
		public String getNomeRegione() {
			return nomeRegione;
		}
		public void setNomeRegione(String nomeRegione) {
			this.nomeRegione = nomeRegione;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + codiceRegione;
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
			Regione other = (Regione) obj;
			if (codiceRegione != other.codiceRegione)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return nomeRegione;
		}
		@Override
		public int compareTo(Regione o) {
			return this.getNomeRegione().compareTo(o.getNomeRegione());
		}
		
		
		
		
	}
