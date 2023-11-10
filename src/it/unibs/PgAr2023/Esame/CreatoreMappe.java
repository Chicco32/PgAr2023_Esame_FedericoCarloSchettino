package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.xml.stream.XMLStreamException;

public class CreatoreMappe {

    public static ArrayList<Nodo> inizializzaMappaBase(int livello) {

        ArrayList<Nodo> mondoBase = new ArrayList<>();

        mondoBase.add(new NodoIniziale(0));
        for (int i = 1; i<=5; i++) mondoBase.add(new NodoIntermedio(i, livello));
        mondoBase.add(new NodoFinale(6, livello));

        mondoBase.get(0).aggiungiCollegamento(mondoBase.get(1)); 
        mondoBase.get(1).aggiungiCollegamento(mondoBase.get(0));mondoBase.get(1).aggiungiCollegamento(mondoBase.get(2));mondoBase.get(1).aggiungiCollegamento(mondoBase.get(3));
        mondoBase.get(2).aggiungiCollegamento(mondoBase.get(1));mondoBase.get(2).aggiungiCollegamento(mondoBase.get(4));
        mondoBase.get(3).aggiungiCollegamento(mondoBase.get(1));mondoBase.get(3).aggiungiCollegamento(mondoBase.get(4));
        mondoBase.get(4).aggiungiCollegamento(mondoBase.get(2));mondoBase.get(4).aggiungiCollegamento(mondoBase.get(3));mondoBase.get(4).aggiungiCollegamento(mondoBase.get(5));
        mondoBase.get(5).aggiungiCollegamento(mondoBase.get(4));mondoBase.get(5).aggiungiCollegamento(mondoBase.get(6));
        mondoBase.get(6).aggiungiCollegamento(mondoBase.get(5)); 
        
        return mondoBase;    

    }

    private static final int MAXNODI = 20; //il numero massimo di nodi che la mappa deve avere (inclusi inizio e fine)
    private static final int MINNODI = 6; //il numero minimo
    private static final int NUMCOLLEGAMENTIMAX = 7; //il numero massimo di collegamenti di un nodo(ovviamente se è superiore al numero di nodi non verrà considerato) 

    public static ArrayList<Nodo> inizializzaMappaRandom(int livello) {
        Random random = new Random();
        ArrayList<Nodo> mappa = new ArrayList<>();
        int nIntermedi = random.nextInt(MINNODI -2, MAXNODI - 2); //scelgo quanti intermedi avere
        for (int i = 1; i<= nIntermedi; i++) mappa.add(new NodoIntermedio(i, livello)); //li aggiungo alla lista
        
        //blocco per gli intermedi
        int numCollegamentiMax;
        if (nIntermedi <= NUMCOLLEGAMENTIMAX) numCollegamentiMax = nIntermedi/2; //se il numero di nodi è inferiore i collegamenti saranno di meno
        else numCollegamentiMax = NUMCOLLEGAMENTIMAX; //altrimenti sono il valore massimo

        for (Nodo nodo : mappa) {

            int aggiungiCollegamenti = random.nextInt(1, numCollegamentiMax); //sceglie un numero di collegamenti fra 1 e il numero massimo
            for (int i = 0; i < aggiungiCollegamenti; i++) {

                //scelta della destinazione
                int prossimoNodo;
                do {
                    prossimoNodo = random.nextInt(1, nIntermedi); //sceglie la prosssima destinazione fra gli intermedi
                } while (prossimoNodo == nodo.getHashCode()); //evita che il nodo si riferisca a se stesso
                
                //creazione dell'arco nel nodo di partenza
                creazioneArco(mappa, nodo, prossimoNodo);
                
                //creazione dell'arco nel nodo di destinazione
                creazioneArco(mappa, mappa.get(prossimoNodo -1), nodo.getHashCode());
            }
        }

        //blocco per il nodo iniziale
        mappa.add(0, new NodoIniziale(0)); //sarà sempre 0 l'inizio
        mappa.get(0).aggiungiCollegamento(mappa.get(1)); //lo 0 si collegerà sempre e solo a 1
        mappa.get(1).aggiungiCollegamento(mappa.get(0));

        //blocco nodo finale
        int ultimoIndice = mappa.size();
        mappa.add(new NodoFinale(ultimoIndice, livello)); //aggiunge l'ultimo nodo
        mappa.get(ultimoIndice).aggiungiCollegamento(mappa.get(ultimoIndice -1));
        mappa.get(ultimoIndice -1).aggiungiCollegamento(mappa.get(ultimoIndice));

        return mappa;
    }

    /**
     * crea un arco unidirezionale (per il bidirezionale bisogna richiamare la funzione invertendo i paramentri)
     * @param mappa la mappa con tutti i nodi
     * @param nodo il riferimento al nodo di partenza dell'arco
     * @param prossimoNodo l'hascode del nodo di arrivo
     */
    private static void creazioneArco(ArrayList<Nodo> mappa, Nodo nodo, int prossimoNodo) {
        if (nodo.getNodiConnessi().isEmpty()) nodo.aggiungiCollegamento(mappa.get(prossimoNodo - 1)); //doppio riferimento da nodo, nodo -1 perche l'hascode parte da 1 ma in questo caso la posizione nell'arraylist parte da 0
        else {
            int missing = 0;
            for (Nodo nodiregistrati : nodo.getNodiConnessi()) { //controlla se quella destinazione è gia stata registarata
                if (nodiregistrati.getHashCode() == prossimoNodo) break;
                else missing++;
            }
            if (missing == nodo.getNodiConnessi().size()) nodo.aggiungiCollegamento(mappa.get(prossimoNodo -1));
        }
    }

    /**
     * inizializza la mappa da un file xml, se qualcosa va storto nella lettura lo segnala e per non rompersi ritorna la mappa base
     * @return l'arralist di nodi con la mappa letta dalla classe xmlstream
     */
    public static ArrayList<Nodo> inizializzaMappaDaXML(int livello) {
        try {
            return traduttoreMappeDaMatrici(XMLStream.leggiMappe(), livello);
        } catch (XMLStreamException e) {
            System.out.println(XMLStream.ERRORE_LETTORE);
            return inizializzaMappaBase(livello);
        }
    }

    /**
     * xmlstream non è in grado di generare mappe ma solo di fornire letture quindi la mappa andrà tradotta da una matrice a una effettiva mappa utilizzabile
     * @param matrice la matrice di adiacenza (modulata su mappe non pesate e non orientate)
     * @return arraylist<Nodo> il formato utilizzabile di mappe
     */
    private static ArrayList<Nodo> traduttoreMappeDaMatrici (int[][] matrice, int livello) {
        ArrayList<Nodo> mappa = new ArrayList<>();
        int nodiInEccesso = 0;

        //creazione di un finto nodo in ecesso per toglierli dalla mappa finale
        int[] rigaVuota = new int[matrice.length];
        Arrays.fill(rigaVuota, 0);
        for (int i=matrice.length - 1; i>=0; i--) {
            if (matrice[i].equals(rigaVuota)) nodiInEccesso++;
            else break;
        }

        //generazione della mappa
        int numNodiDisponibili= matrice.length - nodiInEccesso;
        for(int i=0; i<numNodiDisponibili - 2; i++) mappa.add(new NodoIntermedio(i + 1, livello));
        mappa.add(0, new NodoIniziale(0));
        mappa.add(new NodoFinale(numNodiDisponibili - 1, livello));

        //traduzione dei collegamenti
        for (int i =0; i <numNodiDisponibili; i++) {
            for (int j=0; j< numNodiDisponibili; j++) {
                if (matrice[i][j] == 1) mappa.get(i).aggiungiCollegamento(mappa.get(j)); //non c'è il problema di chiamarla due volte perche essendo la matrice simmetrica il nodo inverso verrà generato
            }
        }

        return mappa;
    }

    public static void printMatriceMappe(int[][] matrice) {
        int dimensione = matrice.length;
		for (int i=0; i < dimensione; i++) {
			System.out.print("|");
			for (int j=0; j < dimensione; j++) {
				System.out.print(String.format("%d \t", matrice[i][j]));
			}
			System.out.println("|");
		}
	}
}
