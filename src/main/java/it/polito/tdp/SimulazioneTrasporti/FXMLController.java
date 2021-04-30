/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.SimulazioneTrasporti;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.SimulazioneTrasporti.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRegione"
    private ComboBox<?> boxRegione; // Value injected by FXMLLoader

    @FXML // fx:id="btnRegione"
    private Button btnRegione; // Value injected by FXMLLoader

    @FXML // fx:id="boxMagazzino"
    private ComboBox<?> boxMagazzino; // Value injected by FXMLLoader

    @FXML // fx:id="btnOrdini"
    private Button btnOrdini; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumVeicoli"
    private TextField txtNumVeicoli; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumConsegne"
    private TextField txtNumConsegne; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResultSimula"
    private TextArea txtResultSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxDestinazione"
    private ComboBox<?> boxDestinazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinutiMax"
    private TextField txtMinutiMax; // Value injected by FXMLLoader

    @FXML // fx:id="btnOttimizza"
    private Button btnOttimizza; // Value injected by FXMLLoader

    @FXML // fx:id="txtResultOttimizza"
    private TextArea txtResultOttimizza; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {

    }

    @FXML
    void doOttimizzazione(ActionEvent event) {

    }

    @FXML
    void doRegione(ActionEvent event) {

    }

    @FXML
    void doSimulazione(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRegione != null : "fx:id=\"boxRegione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRegione != null : "fx:id=\"btnRegione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMagazzino != null : "fx:id=\"boxMagazzino\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOrdini != null : "fx:id=\"btnOrdini\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumVeicoli != null : "fx:id=\"txtNumVeicoli\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumConsegne != null : "fx:id=\"txtNumConsegne\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResultSimula != null : "fx:id=\"txtResultSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxDestinazione != null : "fx:id=\"boxDestinazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMinutiMax != null : "fx:id=\"txtMinutiMax\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnOttimizza != null : "fx:id=\"btnOttimizza\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResultOttimizza != null : "fx:id=\"txtResultOttimizza\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		
	}
}

