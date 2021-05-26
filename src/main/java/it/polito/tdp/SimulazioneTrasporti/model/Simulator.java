package it.polito.tdp.SimulazioneTrasporti.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.SimulazioneTrasporti.model.Event.EventType;

public class Simulator {
	
	private PriorityQueue<Event> queue;
	private Graph<Comuni,DefaultWeightedEdge> grafo;
	private Comuni magazzino;
	private List<Veicolo> veicoli;
	private List<Comuni> listaConsegnati;
	private double tempoMaxMin;
	private int numeroCons;
	
	public Simulator(Graph<Comuni, DefaultWeightedEdge> grafo, Comuni magazzino) {
		this.grafo = grafo;
		this.magazzino = magazzino;
	}
	
	public void init(int numMezzi, int numConsMax, double tempoMaxMin) {
		this.numeroCons=numConsMax;
		this.tempoMaxMin=tempoMaxMin;
		listaConsegnati=new ArrayList<Comuni>();
		listaConsegnati.add(magazzino);
		queue=new PriorityQueue<Event>();
//		Inizializzo veicoli
		this.veicoli=new ArrayList<>();
		for(int i=0;i<numMezzi;i++) {
			List<Consegna> listaConsegne=new ArrayList<Consegna>();
			this.veicoli.add(new Veicolo(i, listaConsegne ) );

			this.queue.add(new Event(0.0, EventType.CONSEGNA_IN_CORSO, this.veicoli.get(i), magazzino) );
			
		}
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e=queue.poll();
			processEvent(e);
		}
	}
	
	private void processEvent(Event e) {
		switch(e.getType()) {
		case CONSEGNA_IN_CORSO:
			List<Consegna> listaVicini=new ArrayList<Consegna>(this.viciniMigliore(e.getComune()));
			Consegna prossimo=null;
			for(Consegna c:listaVicini) {
				if( !listaConsegnati.contains(c.getComune()) ) {
					prossimo=c;
					break;
				}
			}
			if(prossimo!=null) {
				double tempo=e.getTime()+prossimo.getTime();
				double tempoMagazzinoFuturo= this.grafo.getEdgeWeight( this.grafo.getEdge(prossimo.getComune(), magazzino) ) ;
				if ( (tempo+tempoMagazzinoFuturo) >=tempoMaxMin) {
					this.queue.add(new Event(e.getTime(),EventType.RITORNO_MAGAZZINO,e.getVeicolo(),e.getComune()));
				} else {
					if(e.getVeicolo().getListaConsegna().size()<numeroCons) {
						listaConsegnati.add(prossimo.getComune());
						this.queue.add(new Event(tempo, EventType.CONSEGNA_EFFETTUATA, e.getVeicolo(), prossimo.getComune()) );
					} else {
						this.queue.add(new Event(e.getTime(),EventType.RITORNO_MAGAZZINO,e.getVeicolo(),e.getComune()));
					}
				}
			} else {
				this.queue.add(new Event(e.getTime(),EventType.RITORNO_MAGAZZINO,e.getVeicolo(),e.getComune()));
			}
			break;
		case CONSEGNA_EFFETTUATA:
			e.getVeicolo().getListaConsegna().add(new Consegna( e.getComune(), e.getTime() ));
			this.queue.add(new Event(e.getTime(), EventType.CONSEGNA_IN_CORSO, e.getVeicolo(), e.getComune()) );
			break;
		case RITORNO_MAGAZZINO:
			if(e.getComune().equals(this.magazzino)) {
				break;
			}
			double tempoMagazzino= this.grafo.getEdgeWeight( this.grafo.getEdge(e.getComune(), magazzino) ) ;
			e.getVeicolo().getListaConsegna().add(new Consegna( magazzino, tempoMagazzino+e.getTime() ));
			break;

		}
		
	}

	
	private List<Consegna> viciniMigliore(Comuni c) {
		
		List<Consegna> result=new ArrayList<Consegna>();
		List<Comuni> vicini= Graphs.neighborListOf(this.grafo, c);
		
		for(Comuni comuni: vicini) {
				double minimo= this.grafo.getEdgeWeight(this.grafo.getEdge(c, comuni));
				result.add(new Consegna(comuni,minimo));
		}
		Collections.sort(result);
		return result;
	}




	public List<Veicolo> getVeicoli() {
		return veicoli;
	}
}
