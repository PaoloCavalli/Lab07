package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;
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
   txtResult.clear();

		try {
			Nerc selectedNerc = comboBox.getSelectionModel().getSelectedItem();
			if (selectedNerc == null) {
				txtResult.setText("Select a NERC (area identifier)");
				return;
			}

			int maxY = Integer.parseInt(txtAnni.getText());
			int yearListSize = model.getListaAnni().size();
			if (maxY <= 0 || maxY > yearListSize) {
				txtResult.setText("Select a number of years in range [1, " + yearListSize + "]");
				return;
			}

			int maxH = Integer.parseInt(txtOre.getText());
			if (maxH <= 0) {
				txtResult.setText("Select a number of hours greater than 0");
				return;
			}

			txtResult.setText(
					String.format("Computing the worst case analysis... for %d hours and %d years", maxH, maxY));
			List<PowerOutage> worstCase = model.getCasiPeggiori(maxY, maxH, selectedNerc);

			txtResult.clear();
			txtResult.appendText("Tot people affected: " + model.sommaClientiColpiti(worstCase) + "\n");
			txtResult.appendText("Tot hours of outage: " + model.sommaOreBlackOut(worstCase) + "\n");

			for (PowerOutage ee : worstCase) {
				txtResult.appendText(String.format("%d %s %s %d %d", ee.getAnno(), ee.getInizioBlackOut(),
						ee.getFineBlackOut(), ee.getDurataBlackOut(), ee.getClientiColpiti()));
				txtResult.appendText("\n");
			}

		} catch (NumberFormatException e) {
			txtResult.setText("Insert a valid number of years and of hours");
		}
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
