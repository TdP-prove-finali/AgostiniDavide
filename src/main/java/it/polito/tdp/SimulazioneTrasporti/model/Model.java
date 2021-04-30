package it.polito.tdp.SimulazioneTrasporti.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.SimulazioneTrasporti.db.TrasportiDao;

public class Model {
	private Graph<Comuni,DefaultWeightedEdge> grafo;
	private TrasportiDao dao;
	private Map<Integer,Comuni> idMap;
	private List<Comuni> listaComuni;
	private List<Comuni> best;
	private Comuni magazzino;
	
	public Model() {
		dao=new TrasportiDao();
	}
	
	public Graph<Comuni,DefaultWeightedEdge> creaGrafo(String nomeRegione,int numeroConsegne,Comuni magazzino ) {
		this.magazzino=magazzino;
		idMap=new HashMap<Integer,Comuni>();
		grafo=new SimpleWeightedGraph<Comuni,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		listaComuni=new ArrayList<Comuni>(dao.listaComuni(nomeRegione,idMap));
		
//		AGGIUNGO VERTICI
//		Con while evito che i duplicati vengano considerati come consegna
		int i=0;
		while(i<numeroConsegne ) 
		{
			int casuale=(int) (Math.random()*idMap.size());
			Comuni vertice= idMap.get(casuale);
			if(!grafo.containsVertex(vertice)) {
				grafo.addVertex(vertice);
				i++;
			}
		}
			
//		for(int i=0;i<numeroConsegne;i++) {
//			int casuale=(int) (Math.random()*idMap.size());
//			Comuni vertice= idMap.get(casuale);
//			if(!grafo.containsVertex(vertice)) 
//				grafo.addVertex(vertice);
//			else {
//		}
		
		
//		Aggiungo magazzino
		grafo.addVertex(magazzino);
		
//		AGGIUNGO ARCHI
		
//		Strada 1
//		for(Comuni c:grafo.vertexSet()) {
//			Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamenti(Integer.toString(c.getCodiceInteroComune()), this.nomeDBRegione(nomeRegione)));
//			for(Comuni dest:grafo.vertexSet()) {
//				if(!c.equals(dest)) {
//					String var=Integer.toString(dest.getCodiceInteroComune());
//					if(mappaColl.containsKey(var))
//						Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
//				}
//			}
//		}
//		
//		Strada 2
		
		switch(nomeRegione) {
		case "Liguria":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiLiguria(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Abruzzo":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiAbruzzo(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Basilicata":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiBasilicata(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Calabria":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiCalabria(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Campania":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiCampania(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Lazio":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiLazio(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Marche":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiMarche(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Molise":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiMolise(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Puglia":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiPuglia(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Sardegna":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiSardegna(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Sicilia":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiSicilia(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Toscana":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiToscana(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Umbria":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiUmbria(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Veneto":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiVeneto(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Valle d'Aosta/Vallée d'Aoste":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiValle(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Trentino-Alto Adige/Südtirol":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiTrentino(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Emilia-Romagna":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiEmiliaRomagna(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		case "Friuli-Venezia Giulia":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiFriuli(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
			break;
		}
		
		int numArchi=(int) ( (numeroConsegne * (numeroConsegne+1) )/2);
		if(grafo.edgeSet().size()!=numArchi) {
			System.out.println("Riciclo per presenza isola/e");
			this.creaGrafo(nomeRegione, numeroConsegne, magazzino);
		}
			
		return grafo;
			
		
}
	
	
	
	
//	Utile in strada 1.
	private String nomeDBRegione(String regione) {
		String result="";
		
		switch(regione) {
		case "Liguria":
			result="liguria";
			break;
		case "Abruzzo":
			result="abruzzo";
			break;
		case "Basilicata":
			result="basilicata";
			break;
		case "Calabria":
			result="calabria";
			break;
		case "Campania":
			result="campania";
			break;
		case "Lazio":
			result="lazio";
			break;
		case "Marche":
			result="marche";
			break;
		case "Molise":
			result="Molise";
			break;
		case "Puglia":
			result="puglia";
			break;
		case "Sardegna":
			result="sardegna";
			break;
		case "Sicilia":
			result="sicilia";
			break;
		case "Toscana":
			result="toscana";
			break;
		case "Umbria":
			result="umbria";
			break;
		case "Veneto":
			result="veneto";
			break;
		case "Valle d'Aosta/Vallée d'Aoste":
			result="valle_d_aosta";
			break;
		case "Trentino-Alto Adige/Südtirol":
			result="trentino_alto_adige";
			break;
		case "Emilia-Romagna":
			result="emilia_romagna";
			break;
		case "Friuli-Venezia Giulia":
			result="friuli_venezia_giulia";
			break;
			
//			MANCANTI PIEMONTE E LOMBARDIA
			
		}
		return result;
	}
	
	public List<Veicolo> Simula(int numMezzi, int numConsMax) {
		double tempoMassimo=800.0;
		Simulator sim=new Simulator(this.grafo,this.magazzino,tempoMassimo);
		sim.init(numMezzi, numConsMax);
		sim.run();
		return sim.getVeicoli();
	}
	
	

}
