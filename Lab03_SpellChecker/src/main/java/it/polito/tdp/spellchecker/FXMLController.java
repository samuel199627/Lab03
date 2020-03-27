package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary dictionary;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TextArea inputText;
    
    
    //ha parlato a lezione che si poteva anche usare la ComboBox e la differenza e' che nel ChoiceBox
    //si possono solo inserire delle String, mentre nella ComboBox si e' piu' flessibili.
    //Ha anche detto che la grafica di una ComboBox e' piu' carina e quindi in generale consiglia
    //di abituarsi ad usare la ComboBox.
    @FXML
    private ChoiceBox<String> boxLanguage;

    @FXML
    private Button btnSpell;
    
    @FXML
    private TextArea outputText;
    
    @FXML
    private Label errorLabel;

    @FXML
    private Button btnClear;
    
    @FXML
    private Label outputLabel;

    @FXML
    void doClearText(ActionEvent event) {

    	inputText.clear();
    	outputText.clear();
    	errorLabel.setText("");
    	outputLabel.setText("");
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	long inizio = System.currentTimeMillis();


    
    	
    	//System.out.println("INIZIAMO IL CONTROLLO DELLE PAROLE\n");
    	//il dizionario lo abbiamo inzializzato nell'entry point, qui dobbiamo andarlo a caricare con la lingua giusta

    	this.dictionary.loadDictionary(boxLanguage.getValue());
    	/*
    	System.out.println("VERIFICO PRIME 100 PAROLE DIZIONARIO\n");
    	for(int i=0;i<100;i++) {
    		System.out.println("parola "+(i+1)+" ->"+this.dictionary.dizionario.get(i));
    	}
    	*/
    	
    	List<String> input=Arrays.asList(inputText.getText().toLowerCase().replaceAll("[.,?\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").split(" "));
    	//System.out.println("INPUT\n\n"+input); //quando input era solo la stringa che importava tutto il testo
    	/*
    	System.out.println("VERIFICO CONTENUTO IMPORTAZIONE\n");
    	for(int i=0;i<input.size();i++) {
    		System.out.println("parola "+(i+1)+" ->"+input.get(i));
    	}
    	*/
    	//List<RichWord> output=this.dictionary.speelCheckTextLinear(input);
    	List<RichWord> output=this.dictionary.speelCheckTextDichotomic(input);
    	outputText.clear();
    	int errors=0;
    	for(RichWord out: output) {
    		if(out.isCorretta()==false) {
    			outputText.appendText(out.getParola()+"\n");
    			errors++;
    		}
    	}
    	errorLabel.setText(""+errors+" errors");
    	
    	long fine = System.currentTimeMillis();
    	long time=(fine-inizio);
    	outputLabel.setText(""+time+" ms");
    	
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


