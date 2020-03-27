package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
		//dizionario=new LinkedList<String>() ;
		dizionario=new ArrayList<String>() ; //per confrontare le prestazioni
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
	
	/*
	public List<RichWord> speelCheckText(List<String> inputTextList){
		List<RichWord> ritorna= new LinkedList<RichWord>();
		//List<RichWord> ritorna= new ArrayList<RichWord>(); //per confrontare le prestazioni
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
	*/
	
	//implementa a ricerca in ordine dal primo
	public List<RichWord> speelCheckTextLinear(List<String> inputTextList){
		//List<RichWord> ritorna= new LinkedList<RichWord>();
		List<RichWord> ritorna= new ArrayList<RichWord>(); //per confrontare le prestazioni
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
	
	//Sapendo che il vocabolario è ordinato alfabeticamente, l'idea è quella di non iniziare 
	//la ricerca dal primo elemento, ma da quello centrale, cioè a metà del dizionario. 
	//Il procedimento viene ripetuto iterativamente fino a quando o si trova l’elemento 
	//cercato, o tutti gli elementi vengono scartati. In quest’ultimo caso la ricerca termina
	//indicando che il valore non è stato trovato.
	public List<RichWord> speelCheckTextDichotomic(List<String> inputTextList){
		//con questa ricerca ditocomica il numero massimo di iterazioni in cui si deve indovinare, cioe'
		//trovare la parola, corrisponde al logaritmo in base 2 della dimensione del dizionario (arrotondato
		//per eccesso perche' un tentativo a meta' logicamente non si puo' fare).
		//A tal proposito dividendo sempre per due la dimensione in cui cercare, sappiamo che java se il numero
		//e' dispari arrotonda per difetto. Noi per stare dalla parte della ragione andiamo ad arrotondare
		//per eccesso in modo che alcuni tentativi non ci vengano tolti dato che diminuendo la dimensione di
		//piu' del dovuto, rischiamo poi di saltare delle parti, cioe' delle regioni rischiano di rimanere vuote.
		//System.out.println("Dimensione dizionario "+dizionario.size());
		
		List<RichWord> ritorna= new LinkedList<RichWord>();
		//List<RichWord> ritorna= new ArrayList<RichWord>(); //per confrontare le prestazioni
		int pos; //posizione di meta'
		int size; //dimensione dei blocchi che creo ad ogni ciclo
		boolean trovato;
		boolean dopo;
		int conta;
		for(String word:inputTextList) {
			conta=0;
			//per ogni parola ci diamo un booleano del momento in cui lo troviamo
			trovato=false;
			dopo=false;
			pos=dizionario.size()/2;
			//inizialmente cerco tra due blocchi di 50 se la dimensione iniziale e' 100
			size=dizionario.size()/2;
			//qui dobbiamo implemantare la ricerca ditocomica
			while(trovato==false&&size>0) {
				
				/*
				System.out.println("\npos "+pos+" + it "+conta+ " + size "+size+" + dopo="+dopo);
				System.out.println(""+word);
				System.out.println(""+dizionario.get(pos));
				System.out.println(""+word.compareTo(dizionario.get(pos)));
				*/
				
				if(word.compareTo(dizionario.get(pos).toLowerCase())==0) {
					trovato=true;
					//dopo=false;
					break;
				}
				else if(word.compareTo(dizionario.get(pos).toLowerCase())>0) {
					dopo=true;
				}
				else {
					dopo=false;
				}
				conta++;
				
				//se la dimensione iniziale del dizionario e' 100, la prima ricerca al primo giro qui sopra
				//la faccio tra due gruppi di 50, la prossima sara' tra due gruppi di 25 quindi devo spostare la
				//posizione per il valore della dimensione corretta
				if(size%2==0||size==1) { //la dimensione la facciamo andare a zero solo quando eravamo gia' arrivati alla minima dimensione possibile
					size=size/2;
				}
				else { 
					size=(size+1)/2;
				}
				//aggiornamento della posizione in cui cercare
				if(dopo==false) {
					pos=pos-size;
				}
				else {
					pos=pos+size;
				}
				
			}
			
			if(trovato==true) {
				ritorna.add(new RichWord(word,true));
			}
			else {
				ritorna.add(new RichWord(word,false));
			}
		}
		return ritorna;
	}
	
	//idea di creare un metodo per la ricerca ditocomica ma poi abbandonata e fatta tutta nel while
	//perche' altrimenti avrei dovuto passare in argomento la lista del testo in ingresso e non
	//mi pareva il caso
	/*
	public boolean trovaNelDizionario(int size) {
		if(size%2==0) {
			
		}
		else {
			
		}
		return true;
	}
	*/
	
		

}
