package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;
import java.util.Random;

public class Mondo3 {
    
    private static final int NMONDO = 3;
    private static final int MAX_ELEMENTI = 10;
    private static final int MIN_ELEMENTI = 3;

    public static boolean muovitiNellaMappa(MCharacter main) {
        ArrayList<Nodo> mappa = new ArrayList<>();
        Random random = new Random();
        if (random.nextBoolean()) mappa = CreatoreMappe.inizializzaMappaDaXML(NMONDO);
        else mappa = CreatoreMappe.inizializzaMappaBase(NMONDO);
        int numElementiUsati = random.nextInt(MIN_ELEMENTI, MAX_ELEMENTI + 1);
        int nPietre = Math.ceilDiv((numElementiUsati + 1),3) + 1;
        int nGolem = Math.ceilDiv((numElementiUsati - 1)*(numElementiUsati - 2),(nPietre * 2));
        Equilibrio equilibrioMappa = new Equilibrio(numElementiUsati);

        for (Nodo nodo : mappa) { //blocco per caricare di pietre i golems spawnati
            if (nodo instanceof NodoIntermedio) {
                if (nodo.getTamaSelvatico() != null) {
                    nodo.getTamaSelvatico().caricaPietreRandom(numElementiUsati, nPietre);
                }
            }
        }

        Giocatore Ash = new Giocatore(main, nGolem);
        int posizioneMC = 0;

        //per il testing mostra temporanemante quanti elementi usare
        System.out.println(String.format("Elementi in questa partita: %d", numElementiUsati));
        System.out.println(String.format("Pietre in questa partita %d", nPietre));
        System.out.println(String.format("Golem di partenza: %d", nGolem));

        do {//inizio del viaggio
            mappa.get(posizioneMC).setVisitato();

            if (mappa.get(posizioneMC) instanceof NodoIntermedio) { //blocco per i nodi intermedi
                System.out.println(String.format("Golem del nodo %d", posizioneMC));
                if (mappa.get(posizioneMC).getTamaSelvatico() == null) { //blocco per gli effetti
                    System.out.println("qui nessun tamagolem");
                    NodoIntermedio.effettoTamagolem(Ash, equilibrioMappa); //da implementare
                }
                else{ //blocco per gli scontri con i tamagolems selvatici
                    Partita nuovaPartita = new Partita(Ash, mappa.get(posizioneMC).getTamaSelvatico(), numElementiUsati, nPietre, equilibrioMappa);
                    do {
                        nuovaPartita.scontroTamaSelvatico();
                    } while (Ash.hasGolem() && mappa.get(posizioneMC).getTamaSelvatico().getVita() > 0); //finche il mc ha golem e il tamaselvatico è ancora vivo
                }      
            }

            if (mappa.get(posizioneMC) instanceof NodoFinale) { //blocco per lo scontro finale
                System.out.println(IOStream.BOSS);
                System.out.println(IOStream.BENVENUTO_CAPOPALESTRA);
                int sconfitteNPC = 0;
                NodoFinale nodoFinale = (NodoFinale) mappa.get(posizioneMC);
                Partita scontroFinale = new Partita(Ash, nodoFinale.getCapoPalestra(), numElementiUsati, nPietre, equilibrioMappa);
                do {
                    sconfitteNPC = scontroFinale.scontro(sconfitteNPC);
                } while (!scontroFinale.fineScontro());
                if (!nodoFinale.getCapoPalestra().hasGolem()) break;
            }

            if (!Ash.hasGolem()) { //se finisce i golems Ash muore
                System.out.println(IOStream.MORTE);
                return false;
            }

            posizioneMC = IOStream.sceltaStrada(mappa, posizioneMC);
        } while (posizioneMC < mappa.size());
        System.out.println(IOStream.VITTORIA);
        return true;
    }
}
