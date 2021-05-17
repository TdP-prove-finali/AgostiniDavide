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
		grafo=new SimpleWeightedGraph<Comuni,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
//		AGGIUNGO VERTICI
		
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
		
		if(n>7) {
			throw new Exception("Troppi tentativi");
		}
		
		switch(regione.getNomeRegione()) {
		case "Liguria":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiLiguria(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Abruzzo":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiAbruzzo(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Basilicata":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiBasilicata(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
							System.out.println("Miao");
						}
					}
				}
			};
			break;
		case "Calabria":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiCalabria(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Campania":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiCampania(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Lazio":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiLazio(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Marche":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiMarche(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Molise":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiMolise(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();		
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Puglia":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiPuglia(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Sardegna":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiSardegna(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Sicilia":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiSicilia(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Toscana":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiToscana(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Umbria":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiUmbria(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Veneto":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiVeneto(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Valle d'Aosta/VallÃ©e d'Aoste":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiValle(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Trentino-Alto Adige/SÃ¼dtirol":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiTrentino(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Emilia-Romagna":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiEmiliaRomagna(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		case "Friuli-Venezia Giulia":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiFriuli(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
		case "Piemonte":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiPiemonte(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
		case "Lombardia":
			for(Comuni c:grafo.vertexSet()) {
				Map<Integer,Collegamento> mappaColl=new HashMap<Integer,Collegamento>(dao.mappaCollegamentiLombardia(c.getCodiceInteroComune()));
				for(Comuni dest:grafo.vertexSet()) {
					if(!c.equals(dest) && !grafo.containsEdge(c, dest)) {
						int var=dest.getCodiceInteroComune();
						if(mappaColl.containsKey(var)) {
							Graphs.addEdge(grafo, c, dest, mappaColl.get(var).getPeso());
						} else {
							System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
							n++;
							this.creaGrafo(regione, numeroConsegne, magazzino);
						}
					}
				}
			};
			break;
		}
		
		int numArchi=(int) ( (numeroConsegne * (numeroConsegne+1) )/2);
		if(grafo.edgeSet().size()!=numArchi) {
			System.out.println("Riciclo per presenza isola/e o comune/i non raggiungibili");
			n++;
			this.creaGrafo(regione, numeroConsegne, magazzino);
		}
			
		return grafo;
			
		
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
