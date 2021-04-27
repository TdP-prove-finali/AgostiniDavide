package it.polito.tdp.SimulazioneTrasporti.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.SimulazioneTrasporti.model.Collegamento;
import it.polito.tdp.SimulazioneTrasporti.model.Comuni;
import it.polito.tdp.SimulazioneTrasporti.model.Regione;

public class TrasportiDao {
	
	
	public List<Comuni> listaComuni(String nomeRegione, Map<Integer,Comuni> idMap) {
		String sql="SELECT * " + 
				"FROM nome_comuni " + 
				"WHERE nome_regione= ? ";
		List<Comuni> result=new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeRegione);
			ResultSet res = st.executeQuery();
			
			int i=0;

			while (res.next()) {
				Comuni c= new Comuni( res.getInt("codice_regione"),res.getString("codice_comune"),res.getString("nome_comune"),res.getString("nome_regione"),res.getInt("codice_int")  );
				result.add(c);
				idMap.put(i, c);
				i++;
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Regione> listaRegioni() {
		String sql="SELECT DISTINCT nome_regione, codice_regione " + 
				"FROM nome_comuni " +
				"WHERE codice_regione< 21 ";
		List<Regione> result=new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Regione( res.getInt("codice_regione"),res.getString("nome_regione")  ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	STRADA 1: Regione non nota, vado a ragionare sul db e inserisco `` per fare in modo che venga considerato il data-set giusto
//	Problema: nel momento in cui viene inserito st.setString() viene aggiunto alla stringa iniziale '' e ciò fa in modo che non venga considerato la tabella adeguata
//	Per risolvere il problema utilizzo uno "stratagemma": cambiando il nome delle regioni nel data-set il programma funziona normalmente
//	Esempio campania diventa 'campania'.
//	Con questo stratagemma invio da eclipse al db `'campania'` e il db utilizza `` per considerare quella stringa come una tabella e quindi cercherà 'campania'.
//	Ciò mi permette un adeguato funzionamento, ma potrà causare problemi nel caso di implementazioni successive a quella studiata.
	
	public Map<String,Collegamento> mappaCollegamenti(String codice1, String regione) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM `?` " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, regione);
			st.setString(2, codice1);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
//	CONTROLLARE BENE
//	Strada 2: Considero regione per regione una funzione che mi considera la regione designata.
//	Necessito di uno switch nel model.
//	Strada molto più lunga, ma più semplice.
//	Non sono presenti stratagemmi se non in Lombardia e Piemonte.
	
	public Map<String,Collegamento> mappaCollegamentiAbruzzo(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM abruzzo " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiBasilicata(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM basilicata " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiCalabria(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM calabria " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<String,Collegamento> mappaCollegamentiCampania(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM campania " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiEmiliaRomagna(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM emilia_romagna " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiFriuli(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM friuli_venezia_giulia " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiLazio(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM lazio " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiLiguria(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM liguria " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiMarche(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM marche " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiMolise(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM molise " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<String,Collegamento> mappaCollegamentiPuglia(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM puglia " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiSardegna(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM sardegna " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<String,Collegamento> mappaCollegamentiSicilia(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM sicilia " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<String,Collegamento> mappaCollegamentiToscana(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM toscana " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<String,Collegamento> mappaCollegamentiTrentino(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM trentino_alto_adige " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiUmbria(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM umbria " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiValle(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM valle_d_aosta " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Collegamento> mappaCollegamentiVeneto(String codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM veneto " + 
				"WHERE comune_partenza= ? ";
		Map<String,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("comune_arrivo") ,new Collegamento( res.getString("comune_partenza"),res.getString("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	Lombardia e Piemonte mancanti: superano il milione di dati.
	
}
