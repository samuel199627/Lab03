package it.polito.tdp.spellchecker;

import java.net.URL;

import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class FXMLController {
	
	Dictionary dictionary;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> boxLanguage;

    @FXML
    private Button btnSpell;

    @FXML
    private Button btnClear;

    @FXML
    void doClearText(ActionEvent event) {

    }

    @FXML
    void doSpellCheck(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";

        
        //Per il choice box non so da scene builder come si mettono i campi quindi l'ho fatto manualmente qui settando all'inizializzazione il default ad inglese
        //notare che nella definizione sopra ho dovuto mettere 'string' nel segno di diamante per specificare che tipo di dati compone il choice box
        boxLanguage.getItems().addAll("English","Italian");
        boxLanguage.setValue("English");
        
        
    }
    
    //ha senso quindi per quanto riguarda il dizionario andare a crearsi un metodo esterno che importa solamente il dizionario
    //e non lo va a creare.
    //Il modello lo andiamo a creare in Entry Point
    public void setModel(Dictionary dizionario) {
    	this.dictionary=dizionario;
    	
    }
}


