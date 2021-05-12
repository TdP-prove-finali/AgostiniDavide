package it.polito.tdp.SimulazioneTrasporti.model;

import java.util.ArrayList;
import java.util.Collections;
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
	private List<Consegna> best;
	private Comuni magazzino;
	
	private int n;
	
	public Model() {
		dao=new TrasportiDao();
	}
	
	public List<Regione> regioni() {
		List<Regione> lista=new ArrayList<Regione>(dao.listaRegioni());
		Collections.sort(lista);
		return lista;
	}
	
	public List<Comuni> comuni(Regione regione) {
		idMap=new HashMap<Integer,Comuni>();
		listaComuni=new ArrayList<Comuni>(dao.listaComuni(regione.getNomeRegione(),idMap));
		Collections.sort(listaComuni);
		return listaComuni;
	}
	
	
	
	public Graph<Comuni,DefaultWeightedEdge> creaGrafo(Regione regione,int numeroConsegne,Comuni magazzino ) throws Exception {
		this.magazzino=magazzino;
//		idMap=new HashMap<Integer,Comuni>();
		grafo=new SimpleWeightedGraph<Comuni,DefaultWeightedEdge>(DefaultWeightedEdge.class);
//		listaComuni=new ArrayList<Comuni>(dao.listaComuni(regione.getNomeRegione(),idMap));
//		AGGIUNGO VERTICI
//		Con while evito che i duplicati vengano considerati come consegna
		
		ArrayList<Comuni> listaComuniNo=new ArrayList<Comuni>(listaComuni);

//		Aggiungo magazzino
		grafo.addVertex(magazzino);
		listaComuniNo.remove(magazzino);
		
		for(int i=0;i<numeroConsegne;i++)
		{
			int casuale=(int) (Math.random()*listaComuniNo.size());
			Comuni vertice= listaComuniNo.get(casuale);
			listaComuniNo.remove(vertice);
			grafo.addVertex(vertice);
			
		}
			
//		for(int i=0;i<numeroConsegne;i++) {
//			int casuale=(int) (Math.random()*idMap.size());
//			Comuni vertice= idMap.get(casuale);
//			if(!grafo.containsVertex(vertice)) 
//				grafo.addVertex(vertice);
//			else {
//		}
		
		

		
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
		
		switch(regione.getNomeRegione()) {
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
		case "Piemonte":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiPiemonte(Integer.toString(c.getCodiceInteroComune())));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest)) {
						String var=Integer.toString(dest.getCodiceInteroComune());
						if(mappaColl.containsKey(var))
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
					}
				}
			};
		case "Lombardia":
			for(Comuni c:grafo.vertexSet()) {
				Map<String,Collegamento> mappaColl=new HashMap<String,Collegamento>(dao.mappaCollegamentiLombardia(Integer.toString(c.getCodiceInteroComune())));
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
		
		if(n>3) {
			throw new Exception("Troppi tentativi");
		}
		
		int numArchi=(int) ( (numeroConsegne * (numeroConsegne+1) )/2);
		if(grafo.edgeSet().size()!=numArchi) {
			System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
			n++;
			this.creaGrafo(regione, numeroConsegne, magazzino);
		}
			
		return grafo;
			
		
}
	
	
//	m
	
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
	
	public List<Veicolo> Simula(int numMezzi, int numConsMax, double tempoMassimo) {
		Simulator sim=new Simulator(this.grafo,this.magazzino);
		sim.init(numMezzi, numConsMax,tempoMassimo);
		sim.run();
		return sim.getVeicoli();
	}
	
	public List<Consegna> percorsoMigliore(Comuni destinazione, double tempoMax) {
		List<Consegna> parziale=new ArrayList<>();
		this.best=new ArrayList<>();
		Consegna c=new Consegna(this.magazzino, 0.0);
		parziale.add(c);
		trovaRicorsivo(destinazione, parziale, tempoMax, 0.0);
		return this.best;
	}
	
	private void trovaRicorsivo (Comuni destinazione, List<Consegna> parziale, double tempoMax,double time) {
		if(time>tempoMax) {
			return;
		}
//		CASO TERMINALE
		if(parziale.get(parziale.size()-1).getComune().equals(destinazione)) {
			if(parziale.size()>=this.best.size()) {
				if(parziale.size()==this.best.size() && this.best.size()>0) {
					if(parziale.get(parziale.size()-1).getTime()<=this.best.get(best.size()-1).getTime() ) {
						this.best=new ArrayList<Consegna>(parziale);
					}
				} else {
					this.best=new ArrayList<Consegna>(parziale);
				}
			}
			return;
		}
//		Scorro i vicini dell'ultimo vertice in parziale
		for(Comuni vicino: Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1).getComune() ) ) {
			if(!parziale.contains(new Consegna(vicino,0.0))) {
				DefaultWeightedEdge e=this.grafo.getEdge(parziale.get(parziale.size()-1).getComune(), vicino);
//				Provo ad aggiungere
				time+= this.grafo.getEdgeWeight(e);
				parziale.add(new Consegna(vicino,time));
//				Continuo ricorsione
				this.trovaRicorsivo(destinazione, parziale,tempoMax, time);
				
				time-= this.grafo.getEdgeWeight(e);
				parziale.remove(parziale.size()-1);
			}
		}
	}

	public void setN(int n) {
		this.n = n;
	}

	
	
}
