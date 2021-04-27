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
	
	public Simulator(Graph<Comuni, DefaultWeightedEdge> grafo, Comuni magazzino,double tempoMaxMin) {
		this.grafo = grafo;
		this.magazzino = magazzino;
		this.tempoMaxMin=tempoMaxMin;
	}


	// Magazzino, n mezzi, numero massimo consegne per mezzo
	public void init(int numMezzi, int numConsMax ) {
		this.numeroCons=numConsMax;
		
		this.veicoli=new ArrayList<>();
		for(int i=0;i<numMezzi;i++) {
//			Inizializzo i veicoli
			List<Consegna> listaConsegne=new ArrayList<Consegna>();
//			Map<Double,Comuni> mappaConsegne=new HashMap<Double,Comuni>();
			this.veicoli.add(new Veicolo(i, listaConsegne ) );
		}
		listaConsegnati=new ArrayList<Comuni>();
		listaConsegnati.add(magazzino);
		queue=new PriorityQueue<Event>();
		
		List<ComuneDistanza> listaVicini=new ArrayList<ComuneDistanza>(this.viciniMigliore(magazzino));
		
		for(int i=0;i<numMezzi;i++) {
			if(i<listaVicini.size()) {
//				Imposto destinazione iniziale a veicolo
//				this.veicoli.get(i).setLibero(false);
				ComuneDistanza prossimo=listaVicini.get(i);
//				this.veicoli.get(i).getMappaConsegne().put(prossimo.getMinuti(), prossimo.getComune());
				double tempoMagazzino= prossimo.getMinuti();
				if(tempoMagazzino<= tempoMaxMin) {
					listaConsegnati.add(prossimo.getComune());
					Event e=new Event( prossimo.getMinuti(), EventType.CONSEGNA_EFFETTUATA, this.veicoli.get(i), prossimo.getComune() );
					queue.add(e);
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
//					flag=1;
					break;
				}
			}
			if(prossimo!=null) {
//				e.getVeicolo().setLibero(false);
				double tempo=e.getTime()+prossimo.getMinuti();
				listaConsegnati.add(prossimo.getComune());
//				listaOrdini.remove(prossimo.getComune());
				this.queue.add(new Event(tempo, EventType.CONSEGNA_EFFETTUATA, e.getVeicolo(), prossimo.getComune()) );
			}
			break;
		case CONSEGNA_EFFETTUATA:
//			NON LO TOGLIE DA LISTAORDINI PERCHE'?
//			IDEA PARALLELA:inserire un boolean in Comuni con su scritto consegna effettuata.
//			e.getVeicolo().setLibero(true);
			double tempoConsegna=e.getTime()+15.0;
//			Non funziona
			double tempoMagazzino= this.grafo.getEdgeWeight( this.grafo.getEdge(e.getComune(), magazzino) ) ;
			if(tempoMagazzino+e.getTime()<= tempoMaxMin)
				if(e.getVeicolo().getListaConsegna().size() < numeroCons) {
					e.getVeicolo().getListaConsegna().add(new Consegna( e.getComune(), tempoConsegna ));
					this.queue.add(new Event(tempoConsegna, EventType.CONSEGNA_IN_CORSO, e.getVeicolo(), e.getComune()) );
				}
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
