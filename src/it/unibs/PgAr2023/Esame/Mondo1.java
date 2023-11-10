package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;
import java.util.Random;



public class Mondo1 {

    private static final int NMONDO = 1;
    
    public static boolean muovitiNellaMappa(MCharacter main) {
        ArrayList<Nodo> mappa = new ArrayList<>();
        Random random = new Random();
        if (random.nextBoolean()) mappa = CreatoreMappe.inizializzaMappaRandom(NMONDO);
        else mappa = CreatoreMappe.inizializzaMappaBase(NMONDO);
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
                    } while (main.getVita() >= 0 && mappa.get(posizioneMC).getMostro().getVita() >= 0); //finche uno dei due non muore
                    if (main.getVita() <= 0) {
                        System.out.println(IOStream.MORTE);
                        return false;
                    }
                    System.out.println(IOStream.FINESCONTRO);
                    if (mappa.get(posizioneMC).getMostro() instanceof Cammo) break; //la condizione di terminazione è che il boss finale (morto dopo lo scontro) sia cammo
                }
            }
            posizioneMC = IOStream.sceltaStrada(mappa, posizioneMC);

        }while (posizioneMC < mappa.size()); //fichè non è nel nodo finale
        System.out.println(IOStream.VITTORIA);
        return true;
    }

}
