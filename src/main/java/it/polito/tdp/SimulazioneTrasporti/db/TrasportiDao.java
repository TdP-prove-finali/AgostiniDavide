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
	

	
	public Map<Integer,Collegamento> mappaCollegamentiAbruzzo(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM abruzzo " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiBasilicata(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM basilicata " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiCalabria(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM calabria " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<Integer,Collegamento> mappaCollegamentiCampania(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM campania " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiEmiliaRomagna(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM emilia_romagna " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiFriuli(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM friuli_venezia_giulia " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiLazio(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM lazio " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiLiguria(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM liguria " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiMarche(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM marche " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiMolise(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM molise " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<Integer,Collegamento> mappaCollegamentiPuglia(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM puglia " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiSardegna(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM sardegna " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<Integer,Collegamento> mappaCollegamentiSicilia(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM sicilia " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<Integer,Collegamento> mappaCollegamentiToscana(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM toscana " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Map<Integer,Collegamento> mappaCollegamentiTrentino(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM trentino_alto_adige " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiUmbria(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM umbria " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiValle(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM valle_d_aosta " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiVeneto(int codice) {
		String sql="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM veneto " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Integer,Collegamento> mappaCollegamentiPiemonte(int codice) {
		String sql1="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM piemonte2 " + 
				"WHERE comune_partenza= ? " + 
				"UNION " + 
				"SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM piemonte1 " + 
				"WHERE comune_partenza= ?";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql1);
		
			st.setInt(1, codice);
			st.setInt(2, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
//	m
	
	public Map<Integer,Collegamento> mappaCollegamentiLombardia(int codice) {
		String sql1="SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM lombardia1 " + 
				"WHERE comune_partenza= ? " + 
				"UNION " + 
				"SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM lombardia2 " + 
				"WHERE comune_partenza= ? " + 
				"UNION " + 
				"SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM lombardia3 " + 
				"WHERE comune_partenza= ? " + 
				"UNION " + 
				"SELECT comune_partenza, comune_arrivo, minuti_impiegati " + 
				"FROM lombardia4 " + 
				"WHERE comune_partenza= ? ";
		Map<Integer,Collegamento> result=new HashMap<>();
		Connection conn = DBConnect.getConnection();
		try {
			
			PreparedStatement st = conn.prepareStatement(sql1);
			
			st.setInt(1, codice);
			st.setInt(2, codice);
			st.setInt(3, codice);
			st.setInt(4, codice);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getInt("comune_arrivo") ,new Collegamento( res.getInt("comune_partenza"),res.getInt("comune_arrivo"),res.getDouble("minuti_impiegati") ));
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
