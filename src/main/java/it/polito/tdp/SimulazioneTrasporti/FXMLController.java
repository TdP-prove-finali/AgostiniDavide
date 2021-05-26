/**
 /**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.SimulazioneTrasporti;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.SimulazioneTrasporti.model.Comuni;
import it.polito.tdp.SimulazioneTrasporti.model.Consegna;
import it.polito.tdp.SimulazioneTrasporti.model.Model;
import it.polito.tdp.SimulazioneTrasporti.model.Regione;
import it.polito.tdp.SimulazioneTrasporti.model.Veicolo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	private Regione regione;
	Graph<Comuni,DefaultWeightedEdge> grafo;
	private Comuni magazzino;
	private int tentativi;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRegione"
    private ComboBox<Regione> boxRegione; // Value injected by FXMLLoader

    @FXML // fx:id="btnRegione"
    private Button btnRegione; // Value injected by FXMLLoader

    @FXML // fx:id="boxMagazzino"
    private ComboBox<Comuni> boxMagazzino; // Value injected by FXMLLoader

    @FXML // fx:id="btnOrdini"
    private Button btnOrdini; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumVeicoli"
    private TextField txtNumVeicoli; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumConsegne"
    private TextField txtNumConsegne; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtGiornata"
    private TextField txtGiornata; // Value injected by FXMLLoader


    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResultSimula"
    private TextArea txtResultSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxDestinazione"
    private ComboBox<Comuni> boxDestinazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinutiMax"
    private TextField txtMinutiMax; // Value injected by FXMLLoader

    @FXML // fx:id="btnOttimizza"
    private Button btnOttimizza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResultOttimizza"
    private TextArea txtResultOttimizza; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumConsegneMax"
    private TextField txtNumConsegneMax; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtComuni"
    private TextArea txtComuni; // Value injected by FXMLLoader

    @FXML // fx:id="radioPrimo"
    private RadioButton radioPrimo; // Value injected by FXMLLoader

    @FXML // fx:id="radioSecondo"
    private RadioButton radioSecondo; // Value injected by FXMLLoader
    
    @FXML
    void doRegione(ActionEvent event) {
		this.btnOttimizza.setDisable(true);
		this.btnSimula.setDisable(true);
		
		this.txtResultSimula.clear();
		this.txtResultOttimizza.clear();
		
		this.regione=this.boxRegione.getValue();
		if(regione==null) {
    		txtResultSimula.appendText("ERRORE: Scegli una regione per continuare!\n");
    		txtResultOttimizza.appendText("ERRORE: Scegli una regione per continuare!\n");
    		return;
    	} else {
			this.boxMagazzino.getItems().addAll(this.model.comuni(regione));
			btnRegione.setDisable(true);
	    	this.btnOrdini.setDisable(false);
		}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	this.txtResultSimula.clear();
		this.txtResultOttimizza.clear();
		
    	Comuni m=this.boxMagazzino.getValue();
    	if(m==null) {
    		txtResultSimula.appendText("ERRORE: Scegli un comune per il magazzino per continuare!\n");
    		txtResultOttimizza.appendText("ERRORE: Scegli un comune per il magazzino per continuare!\n");
    		return;
    	}
    	this.magazzino=m;
    	String numero=this.txtNumConsegne.getText();
    	
    	try {
			int numConsegne = Integer.parseInt(numero);
			if(numConsegne>this.model.comuni(regione).size()) {
				txtResultSimula.appendText("ERRORE: Ordini non effettuati.\nInserisci un numero di ordini inferiore al numero di comuni per regione\n");
				txtResultOttimizza.appendText("ERRORE: Ordini non effettuati.\nInserisci un numero di ordini inferiore al numero di comuni per regione\n");
				return;
			}
			grafo=new SimpleWeightedGraph<Comuni,DefaultWeightedEdge>(DefaultWeightedEdge.class);
			this.model.setN(0);
			grafo=this.model.creaGrafo(regione, numConsegne, m);
			if(grafo.vertexSet().size()>0) {
				txtResultSimula.appendText("Ordini effettuati!\nGrafo caricato con "+grafo.vertexSet().size()+" vertici e con "+grafo.edgeSet().size()+" archi (compreso magazzino)");
				txtResultOttimizza.appendText("Ordini effettuati!\nGrafo caricato con "+grafo.vertexSet().size()+" vertici e con "+grafo.edgeSet().size()+" archi (compreso magazzino)");
		    	this.txtComuni.clear();
		    	
		    	List<Comuni> lista=new ArrayList<Comuni>();
		    	lista.addAll(grafo.vertexSet());
		    	Collections.sort(lista);
		    	this.boxDestinazione.getItems().addAll(lista);
				for(Comuni d:lista) {
					if(!d.equals(magazzino)) {
						this.txtComuni.appendText(d.getNomeComune()+"\n");
					}
				}
				this.btnOttimizza.setDisable(false);
				this.btnSimula.setDisable(false);
		    	this.btnOrdini.setDisable(true);
			} else {
				txtResultSimula.appendText("ERRORE: Ordini non effettuati, assicurati di aver inserito un valore almeno >0\n");
				txtResultOttimizza.appendText("ERRORE: Ordini non effettuati, assicurati di aver inserito un valore almeno >0\n");
			}
		} catch (NumberFormatException ex) {
			txtResultSimula.appendText("ERRORE: Devi inserire un numero\n");
			txtResultOttimizza.appendText("ERRORE: Devi inserire un numero\n");
			return;
		} catch (Exception e) {
			this.tentativi++;
			txtResultSimula.appendText("Troppi tentativi di creazione grafo effettuati\nAssicurati di aver scelto un comune non presente su un isola e con collegamento via terra al resto della regione\nSe il problema persiste diminuisci il numero di ordini effettati\nNumero tentativi effettuati da utente: "+this.tentativi);
			txtResultOttimizza.appendText("Troppi tentativi di creazione grafo effettuati\nAssicurati di aver scelto un comune non presente su un isola e con collegamento via terra al resto della regione\nSe il problema persiste diminuisci il numero di ordini effettati\nNumero tentativi effettuati da utente: "+this.tentativi);
		}
    	

    }

    @FXML
    void doOttimizzazione(ActionEvent event) {
    	
    	this.txtResultOttimizza.clear();
    	String numero=this.txtMinutiMax.getText();
    	Comuni d=this.boxDestinazione.getValue();
    	if(d==null) {
    		txtResultOttimizza.appendText("Scegli una destinazione per continuare!\n");
    		return;
    	}
    	try {
    		double numMinuti = Double.parseDouble(numero);
    		
        	List<Consegna> listaComuni=new ArrayList<Consegna>(model.percorsoMigliore(d, numMinuti));
        	this.txtResultOttimizza.clear();
        	if(listaComuni.size()>0) {
        	this.txtResultOttimizza.appendText("Numero Consegne effettuate: "+(listaComuni.size()-1)+"\n");
        	} else {
        		this.txtResultOttimizza.appendText("Numero Consegne effettuate: "+listaComuni.size()+"\n");
        	}
        	for(Consegna cons: listaComuni) {
    			this.txtResultOttimizza.appendText("Comune: "+cons.getComune().getNomeComune()+"\t Tempo (minuti): "+cons.getTime()+"\n");
        	}
    	} catch (NumberFormatException ex) {
    		txtResultOttimizza.appendText("ERRORE: Devi inserire un numero\n");
    		return;
    	}
    	
    	
    	
    }
    
    @FXML
    void handlePrimo(ActionEvent event) {
    	if(radioPrimo.isSelected()) {
    		radioSecondo.setSelected(false);
    	}
    }

    @FXML
    void handleSecondo(ActionEvent event) {
    	if(radioSecondo.isSelected()) {
    		radioPrimo.setSelected(false);
    	}
    }

    @FXML
    void doSimulazione(ActionEvent event) {
    	String numVeic=this.txtNumVeicoli.getText();
    	String numCons=this.txtNumConsegneMax.getText();
    	String numGio=this.txtGiornata.getText();
    	this.txtResultSimula.clear();
    	
    	if( (this.radioPrimo.isSelected()==false ) && (this.radioSecondo.isSelected()==false) ) {
    		this.txtResultSimula.appendText("ERRORE: Scegliere il tipo di simulazione da effettuare");
    		return;
    	}
    	
    	this.txtResultSimula.appendText("Numero ordini: "+(grafo.vertexSet().size()-1)+"\n");
    	
    	try {
			int numConsegneMax = Integer.parseInt(numCons);
			int numVeicoli = Integer.parseInt(numVeic);
			double giornata=Double.parseDouble(numGio);
			
	    	List<Veicolo> listaVeicoli=new ArrayList<Veicolo>();
//	    	model.secondoAlgoritmo(numVeicoli,numConsegneMax,giornata)
	    	
	    	if(this.radioPrimo.isSelected()) {
	    		listaVeicoli.addAll(model.Simula(numVeicoli,numConsegneMax,giornata));
	    	} else {
	    		listaVeicoli.addAll(model.secondoAlgoritmo(numVeicoli,numConsegneMax,giornata));
	    	}
	    	int i=0;
	    	for (Veicolo v:listaVeicoli) {
	    		if(v.getListaConsegna().size()>0) {
	    			this.txtResultSimula.appendText("\nId veicolo: "+v.getIdVeicolo()+"\tNumero consegne effettuate: "+(v.getListaConsegna().size()-1)+"\n");
	    			i=i+v.getListaConsegna().size()-1;
	    			int j=0;
		    		for(Consegna cons:v.getListaConsegna()) {
		    			j++;
		    			if(v.getListaConsegna().size()!=j) {
		    				this.txtResultSimula.appendText("Comune: "+cons.getComune().getNomeComune()+"\t Tempo (minuti): "+cons.getTime()+"\n");
		    			} else {
		    				this.txtResultSimula.appendText("Ritorno al magazzino\nComune: "+cons.getComune().getNomeComune()+"\t Tempo (minuti): "+cons.getTime()+"\n");
		    			}
		    		}
	    		} else {
	    			this.txtResultSimula.appendText("\nId veicolo: "+v.getIdVeicolo()+"\tNumero consegne effettuate: "+v.getListaConsegna().size()+"\n");
		    		i=i+v.getListaConsegna().size();
	    		}
	    	}
	    	int nonEffe=(grafo.vertexSet().size()-1)-i;
	    	this.txtResultSimula.appendText("\nNumero consegne non effettuate: "+nonEffe);
		} catch (NumberFormatException ex) {
			txtResultSimula.appendText("ERRORE: Devi inserire un numero per le consegne, i veicoli e per i minuti della giornata\n");
			return;
		}
    }
    
    @FXML
    void doClear(ActionEvent event) {
    	this.boxMagazzino.getItems().clear();
    	this.boxDestinazione.getItems().clear();
    	this.txtResultOttimizza.clear();
    	this.txtResultSimula.clear();
    	this.txtComuni.clear();
    	this.txtGiornata.clear();
    	this.txtMinutiMax.clear();
    	this.txtNumConsegne.clear();
    	this.txtNumConsegneMax.clear();
    	this.txtNumVeicoli.clear();
    	
    	btnRegione.setDisable(true);
    	this.btnOrdini.setDisable(true);
		this.btnOttimizza.setDisable(true);
		this.btnRegione.setDisable(false);
		this.btnSimula.setDisable(true);
		this.tentativi=0;

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRegione != null : "fx:id=\"boxRegione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRegione != null : "fx:id=\"btnRegione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMagazzino != null : "fx:id=\"boxMagazzino\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumConsegne != null : "fx:id=\"txtNumConsegne\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOrdini != null : "fx:id=\"btnOrdini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumVeicoli != null : "fx:id=\"txtNumVeicoli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumConsegneMax != null : "fx:id=\"txtNumConsegneMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGiornata != null : "fx:id=\"txtGiornata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert radioPrimo != null : "fx:id=\"radioPrimo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert radioSecondo != null : "fx:id=\"radioSecondo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResultSimula != null : "fx:id=\"txtResultSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtComuni != null : "fx:id=\"txtComuni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxDestinazione != null : "fx:id=\"boxDestinazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMinutiMax != null : "fx:id=\"txtMinutiMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOttimizza != null : "fx:id=\"btnOttimizza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResultOttimizza != null : "fx:id=\"txtResultOttimizza\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		this.tentativi=0;
		boxRegione.getItems().addAll(model.regioni());
		this.btnOrdini.setDisable(true);
		this.btnOttimizza.setDisable(true);
		this.btnRegione.setDisable(false);
		this.btnSimula.setDisable(true);
	}
}
