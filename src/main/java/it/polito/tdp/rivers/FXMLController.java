/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.MediaEConteggio;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
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

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    private List<River> rivers;
    private List<String> date;
    private int nInsodisfatti;
    private double occupazione;
    
    @FXML
    void onSimula(ActionEvent event) {
    	txtResult.clear();
    	Integer k = Integer.parseInt(txtK.getText());
    	if(k.equals(null)) {
    		txtResult.setText("Inserisci valore numerico al fattore k!");
    	}
    	else {
    		
    		River fiume = boxRiver.getValue();
    		MediaEConteggio m = model.getMediaECconteggio(fiume.getId());
    		
    		nInsodisfatti=model.simula(k, m.getAvg(), fiume.getId());
    		occupazione=model.simula2(k, m.getAvg(), fiume.getId());
    		
    		txtResult.setText("Giorni non garantiti: "+nInsodisfatti+"\n"+
    		"Occupazione media del bacino: "+occupazione);
    	}
    }
    
    @FXML
    void onScegli(ActionEvent event) {
    	txtResult.clear();
    	if(boxRiver==null) {
    		txtResult.setText("Seleziona una fiume!");
    	}
    	else {
    		River fiume = boxRiver.getValue();
  
    		MediaEConteggio m = model.getMediaECconteggio(fiume.getId());
    		date = model.getAllDate(fiume.getId());
    		
    		txtNumMeasurements.setText(String.valueOf(m.getCount()));
    		txtFMed.setText(String.valueOf(m.getAvg()));
    		txtStartDate.setText(date.get(0));
    		txtEndDate.setText(date.get(date.size()-1));
    	}
   
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	rivers= model.getRivers();
    	for(River r: rivers) {
    	boxRiver.getItems().add(r);
    	}
    }
}
