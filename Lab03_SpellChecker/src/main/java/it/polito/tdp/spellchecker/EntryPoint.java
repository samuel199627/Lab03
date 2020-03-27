package it.polito.tdp.spellchecker;

import javafx.application.Application;

import static javafx.application.Application.launch;

import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	
    	//qui il dizionario viene creato e poi il contenuto lo carichiamo nel
    	//controller in base alla lingua selezionata
    	Dictionary dizionario=new Dictionary();
    	FXMLController controller;
    	
    	//ci recuperiamo un riferimento al controller e lo facciamo tramite il loader che va a vedere
    	//qual'è il controller associato al file FXML
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
    	
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        controller=loader.getController();
        controller.setModel(dizionario);
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
