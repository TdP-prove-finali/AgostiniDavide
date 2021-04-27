package it.polito.tdp.SimulazioneTrasporti.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		
		Comuni c=new Comuni(7, "008001", "Airole", "Liguria", 8001);
		
		Graph<Comuni,DefaultWeightedEdge> grafo=new SimpleWeightedGraph<Comuni,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		grafo=m.creaGrafo("Liguria", 10, c);
		
		
		for(Comuni d:grafo.vertexSet()) {
			System.out.println(d.getNomeComune()+" "+d.getNomeRegione()) ;
		}
System.out.format("Grafo caricato con %d vertici %d archi \n", grafo.vertexSet().size(), grafo.edgeSet().size());



List<Veicolo> lista=new ArrayList<Veicolo>(m.Simula(5, 13));

	for (Veicolo v:lista) {
		System.out.println(v.getIdVeicolo()+" "+v.getListaConsegna().size()) ;
		for(Consegna cons:v.getListaConsegna()) {
			System.out.println(cons.getComune().getNomeComune()+" "+cons.getTime());
		}
	}


	
	}

}
