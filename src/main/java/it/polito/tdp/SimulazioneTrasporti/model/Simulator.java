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
		
		this.veicoli=new ArrayList<>();
		for(int i=0;i<numMezzi;i++) {
//			Inizializzo i veicoli
			List<Consegna> listaConsegne=new ArrayList<Consegna>();
			this.veicoli.add(new Veicolo(i, listaConsegne ) );
		}
		listaConsegnati=new ArrayList<Comuni>();
		listaConsegnati.add(magazzino);
		queue=new PriorityQueue<Event>();
		
		List<ComuneDistanza> listaVicini=new ArrayList<ComuneDistanza>(this.viciniMigliore(magazzino));
		
		for(int i=0;i<numMezzi;i++) {
			if(i<listaVicini.size()) {
//				Imposto destinazione iniziale a veicolo
				ComuneDistanza prossimo=listaVicini.get(i);
				double tempoMagazzino= prossimo.getMinuti();
				if(tempoMagazzino+prossimo.getMinuti() < tempoMaxMin) {
					Event e=new Event( prossimo.getMinuti(), EventType.CONSEGNA_EFFETTUATA, this.veicoli.get(i), prossimo.getComune() );
					queue.add(e);
				}
				else {
					
				}
			}
		}
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			Event e=queue.poll();
//			System.out.println(e);
			processEvent(e);
		}
	}
	
	private void processEvent(Event e) {
		switch(e.getType()) {
		case CONSEGNA_IN_CORSO:
			List<ComuneDistanza> listaVicini=new ArrayList<ComuneDistanza>(this.viciniMigliore(e.getComune()));
			ComuneDistanza prossimo=null;
			for(ComuneDistanza c:listaVicini) {
				if( !listaConsegnati.contains(c.getComune()) ) {
					prossimo=c;
					break;
				}
			}
			if(prossimo!=null) {
				double tempo=e.getTime()+prossimo.getMinuti();
				double tempoMagazzinoFuturo= this.grafo.getEdgeWeight( this.grafo.getEdge(prossimo.getComune(), magazzino) ) ;
				if ( (tempo+tempoMagazzinoFuturo) >=tempoMaxMin) {
					this.queue.add(new Event(e.getTime(),EventType.RITORNO_MAGAZZINO,e.getVeicolo(),e.getComune()));
				} else {
					this.queue.add(new Event(tempo, EventType.CONSEGNA_EFFETTUATA, e.getVeicolo(), prossimo.getComune()) );
				}
			} else {
				this.queue.add(new Event(e.getTime(),EventType.RITORNO_MAGAZZINO,e.getVeicolo(),e.getComune()));
			}
				
			break;
		case CONSEGNA_EFFETTUATA:
			if( (e.getVeicolo().getListaConsegna().size()) < numeroCons) {
				listaConsegnati.add(e.getComune());
				e.getVeicolo().getListaConsegna().add(new Consegna( e.getComune(), e.getTime() ));
				this.queue.add(new Event(e.getTime(), EventType.CONSEGNA_IN_CORSO, e.getVeicolo(), e.getComune()) );
			} else {
				this.queue.add(new Event(e.getTime(),EventType.RITORNO_MAGAZZINO,e.getVeicolo(),e.getComune()));
			}
			break;
		case RITORNO_MAGAZZINO:
			double tempoMagazzino= this.grafo.getEdgeWeight( this.grafo.getEdge(e.getComune(), magazzino) ) ;
			e.getVeicolo().getListaConsegna().add(new Consegna( magazzino, tempoMagazzino+e.getTime() ));
			break;

		}
		
	}

	
	private List<ComuneDistanza> viciniMigliore(Comuni c) {
		
		List<ComuneDistanza> result=new ArrayList<ComuneDistanza>();
		List<Comuni> vicini= Graphs.neighborListOf(this.grafo, c);
		
		for(Comuni comuni: vicini) {
				double minimo= this.grafo.getEdgeWeight(this.grafo.getEdge(c, comuni));
				result.add(new ComuneDistanza(comuni,minimo));
		}
		Collections.sort(result);
		return result;
	}




	public List<Veicolo> getVeicoli() {
		return veicoli;
	}
}
