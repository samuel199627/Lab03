package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

//modello dell'applicazione

public class Dictionary {
	
	//senza il public non posso accederci da fuori
	//avrei dovuto crearmi metodi getter e setter ma non e' questo quello che volevo fare
	//quindi per risparmiare tempo ho messo public qui e bom
	public List<String> dizionario;
	
	
	public void loadDictionary(String language) {
		//perche' se nello stesso programma richiamo piu' volte lo spell Check
		//se cambio dizionario lo devo riscrivere da capo quindi lo rinizializzo
		dizionario=new LinkedList<String>() ;
		//System.out.println("PROVO A CARICARE IL DIZIONARIO\n"+"CON "+language+".txt");
		String file=language+".txt";
		
		try {
				FileReader fr = new FileReader("src/main/resources/"+file);
				BufferedReader br = new BufferedReader(fr); 
				String word;
				//leggiamo una riga alla volta dal file finche' questa riga non e' null e quindi il file 
				//e' finito e lo abbiamo scorso tutto
				while ((word = br.readLine()) != null) {
				// Aggiungere parola alla struttura dati
					dizionario.add(word);
				}
				br.close();
				fr.close();
		} 
		catch (IOException e){
				System.out.println("Errore nella lettura del file");
		              }
		
	}
	
	public List<RichWord> speelCheckText(List<String> inputTextList){
		List<RichWord> ritorna= new LinkedList<RichWord>();
		for(String word:inputTextList) {
			if(dizionario.contains(word)) {
				ritorna.add(new RichWord(word,true));
			}
			else {
				ritorna.add(new RichWord(word,false));
			}
		}
		return ritorna;
	}
	
	
	
		

}
