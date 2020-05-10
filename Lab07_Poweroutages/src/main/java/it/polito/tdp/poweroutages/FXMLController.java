package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> comboBox;

    @FXML
    private TextField txtAnni;

    @FXML
    private TextField txtOre;

    @FXML
    private Button btnAnalisi;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalisi(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtAnni != null : "fx:id=\"txtAnni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOre != null : "fx:id=\"txtOre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        // Utilizzare questo font per incolonnare correttamente i dati;
        txtResult.setStyle("-fx-font-family: monospace");
    }
    
    
 	
    
  public void setModel (Model model) {
    	this.model=model;
    	List <Nerc> nercList = model.getNercList();
    	comboBox.getItems().addAll(nercList);
    }
    
 
   
    
    
}
