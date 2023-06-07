package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;
import java.util.Random;



public class Mondo1 {
    
    public static ArrayList<Nodo> mondoBase = inizializzaMappaBase();
    private static final int MAXNODI = 30;
    private static final int MINNODI = 6;


    public static ArrayList<Nodo> inizializzaMappaBase() {
        ArrayList<Nodo> mondoBase = new ArrayList<>();
        mondoBase.add(new NodoIniziale(0));
        for (int i = 1; i<=5; i++) {
            mondoBase.add(new NodoIntermedio(i));
        }
        mondoBase.add(new NodoFinale(6));

        mondoBase.get(0).aggiungiCollegamento(mondoBase.get(1)); 
        mondoBase.get(1).aggiungiCollegamento(mondoBase.get(0));mondoBase.get(1).aggiungiCollegamento(mondoBase.get(2));mondoBase.get(1).aggiungiCollegamento(mondoBase.get(3));
        mondoBase.get(2).aggiungiCollegamento(mondoBase.get(1));mondoBase.get(2).aggiungiCollegamento(mondoBase.get(4));
        mondoBase.get(3).aggiungiCollegamento(mondoBase.get(1));mondoBase.get(3).aggiungiCollegamento(mondoBase.get(4));
        mondoBase.get(4).aggiungiCollegamento(mondoBase.get(2));mondoBase.get(4).aggiungiCollegamento(mondoBase.get(3));mondoBase.get(4).aggiungiCollegamento(mondoBase.get(5));
        mondoBase.get(5).aggiungiCollegamento(mondoBase.get(4));mondoBase.get(5).aggiungiCollegamento(mondoBase.get(6));
        mondoBase.get(6).aggiungiCollegamento(mondoBase.get(5)); 
        
        return mondoBase;    

    }

    public static ArrayList<Nodo> inizializzaMappaRandom() {
        Random random = new Random();
        ArrayList<Nodo> mappa = new ArrayList<>();
        int nIntermedi = random.nextInt(MINNODI -2, MAXNODI - 2); //scelgo quanti intermedi avere
        for (int i = 1; i<= nIntermedi; i++) mappa.add(new NodoIntermedio(i)); //li aggiungo alla lista
        
        //blocco per gli intermedi
        for (Nodo nodo : mappa) {

            int aggiungiCollegamenti = random.nextInt(1, nIntermedi/2); //sceglie un numero di collegamenti fra 1 e la metà di tutti
            for (int i =0; i< aggiungiCollegamenti; i++) {
                int prossimoNodo = random.nextInt(1, nIntermedi); //sceglie la prosssima destinazione fra gli intermedi
                if (nodo.getNodiConnessi().isEmpty()) nodo.getNodiConnessi().add(mappa.get(prossimoNodo)); //doppio riferimento da nodo
                else {
                    int missing = 0;
                    for (Nodo nodiregistrati : nodo.getNodiConnessi()) { //controlla se quella destinazione è gia stata registarata
                        if (nodiregistrati.getHashCode() == prossimoNodo) break;
                        else missing++;
                    }
                    if (missing == nodo.getNodiConnessi().size()) nodo.getNodiConnessi().add(mappa.get(prossimoNodo));
                }
                
                if (mappa.get(prossimoNodo).getNodiConnessi().isEmpty()) mappa.get(prossimoNodo).getNodiConnessi().add(nodo); //a prosismo nodo
                else { //stessi controlli anche per la destinazione
                    int missing = 0;
                    for (Nodo nodiregistrati : mappa.get(prossimoNodo).getNodiConnessi()) { //controlla se quella destinazione è gia stata registarata
                        if (nodiregistrati.getHashCode() == nodo.getHashCode()) break;
                        else missing++;
                    }
                    if (missing == mappa.get(prossimoNodo).getNodiConnessi().size()) mappa.get(prossimoNodo).getNodiConnessi().add(nodo); //aggiunge il doppio riferimento
                }
            }
        }

        //blocco per il nodo iniziale
        mappa.add(new NodoIniziale(0)); //sarà sempre 0 linizio
        mappa.get(0).aggiungiCollegamento(mappa.get(1)); //lo 0 si collegerà sempre e solo a 1
        mappa.get(1).aggiungiCollegamento(mappa.get(0));

        //blocco nodo finale
        int ultimoIndice = mappa.size();
        mappa.add(new NodoFinale(ultimoIndice)); //aggiunge l'ultimo nodo
        mappa.get(ultimoIndice).aggiungiCollegamento(mappa.get(ultimoIndice -1));
        mappa.get(ultimoIndice -1).aggiungiCollegamento(mappa.get(ultimoIndice));

        return mappa;
    }


    public static ArrayList<Nodo> getMondoBase() {
        return mondoBase;
    }

    public static boolean muovitiNellaMappa(MCharacter main, int tentativiRimasti) {
        ArrayList<Nodo> mappa = new ArrayList<>();
        Random random = new Random();
        if (random.nextBoolean()) mappa = inizializzaMappaRandom();
        else mappa = inizializzaMappaBase();
        int posizioneMC = 0;


        do { //inizio del viaggio
            IOStream.mostraPosizione(posizioneMC, main);
            mappa.get(posizioneMC).setVisitato();
            if (mappa.get(posizioneMC) instanceof NodoIntermedio || mappa.get(posizioneMC) instanceof NodoFinale) { //è un nodo intermedio
                if (mappa.get(posizioneMC).getMostro() == null) { //se non c'è il mostro
                    System.out.println(IOStream.EFFETTO);
                    NodoIntermedio.effettoStatistiche(main);
                }
                else {
                    if (mappa.get(posizioneMC) instanceof NodoFinale) System.out.println(IOStream.BOSS);
                    else System.out.println(IOStream.MOSTRO);
                    System.out.println(IOStream.INIZIOSCONTRO);
                    do {
                        IOStream.HUDScontro(mappa.get(posizioneMC).getMostro(), main);
                        mappa.get(posizioneMC).getMostro().scontro(main); //altrimenti scontro (ogni coppia di fendenti è uno scontro)
                    } while (main.getVita() > 0 && mappa.get(posizioneMC).getMostro().getVita() > 0); //finche uno dei due non muore
                    if (main.getVita() < 0) {
                        System.out.println(IOStream.MORTE);
                        return false;
                    }
                    System.out.println(IOStream.FINESCONTRO);
                }
            }
            posizioneMC = IOStream.sceltaStrada(mappa, posizioneMC);

        }while (posizioneMC < mappa.size());
        System.out.println(IOStream.VITTORIA);
        return true;
    }

}
