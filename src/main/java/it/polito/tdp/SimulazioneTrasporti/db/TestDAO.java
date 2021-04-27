package it.polito.tdp.SimulazioneTrasporti.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.SimulazioneTrasporti.model.Comuni;
import it.polito.tdp.SimulazioneTrasporti.model.Regione;

public class TestDAO {

	public static void main(String[] args) {
		TrasportiDao dao = new TrasportiDao();
		Map<Integer,Comuni> idMap=new HashMap<Integer,Comuni>();

		List<Regione> regione = dao.listaRegioni();
		System.out.println(regione);
		System.out.println("Regioni # rows: " + regione.size());
		
		String campania="campania";

		List<Comuni> comuni = dao.listaComuni(campania,idMap);
		System.out.println(comuni);
		System.out.println("Comuni # rows: " + comuni.size());
	}

}
