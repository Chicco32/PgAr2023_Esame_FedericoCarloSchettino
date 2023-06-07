package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class IOStream {
    
    public static final String BENVENUTO = "Benvenuto nel mondo ARNALDOVERSE";
    public static final String INDIETRO = "Devi completare i mo ndi pèrecedenti";
    public static final String SELEZIONE_MONDO = "ecco i mondi disponibili";
    public static final String[] MONDI = {"Mondo base", "Patente e libbretto"};
    public static final String TENTATIVI = "Hai ancora %d tentativi";
    public static final String PERSONAGGIO = "Crea un nuovo protagonista";
    public static final String NOME = "Che nome ha\n";
    public static final String UIVIAGGIO = "nome: %s                        nodo: %d";
    public static final String UIVIAGGIO2 = "vita: %d                       attacco:%d";
    public static final String STRADE = "puoi andare a:";
    public static final String EFFETTO = "Qui non ci sono mostri";
    public static final String BUON_EFFETTO_VITA = "La fata radiosa direttamente da ZeldaTM ti dona parte della sua energia e quadagni %d";
    public static final String BUON_EFFETTO_ATTACCO = "Trovi una pozione della forza che ti rende piu macho, guadagni %d";
    public static final String CATTIVO_EFFETTO_VITA = "Fai uno schwerzo a una megera e quella ti toglie %d alla vita";
    public static final String CATTIVO_EFFETTO_ATTACCO = "calpesti una trappola per orsi caduta dal cielo e perdi %d nell'attacco";
    public static final String INIZIOSCONTRO = "inizia lo scontro";
    public static final String FINESCONTRO = "Finisce lo scontro";
    public static final String MOSTRO = "Incontri un mostro sul tuo cammino";
    public static final String ATTACCO = "%s fa un attacco e infligge %d di danno";
    public static final String BOSS = "Sei arrivato alla fine, resta solo il boss finale da sconfiggere";
    public static final String MORTE = "YOU DIED *musica di dark soul in sottofondo";
    public static final String VITTORIA = "LEVEL COMPLETED *muscia di Super Mario di fine livello";
    public static final String COMPLETATO = "hai completato il mondo e ottenuto 110 punti (e una stellina)";



    public static void pausaDiSistema() {
		System.out.println("Premi il tasto invio per continuare");
		String nextLine = new java.util.Scanner(System.in).nextLine();
	}

    public static MCharacter creazioenMC() {
        System.out.println(PERSONAGGIO);
        String nome = InputDati.leggiStringaNonVuota(NOME);
        return new MCharacter(nome);
    }

    public static void mostraPosizione(int posizioneMC, MCharacter main) {
        System.out.println(String.format(UIVIAGGIO, main.getNome(), posizioneMC));
        System.out.println(String.format(UIVIAGGIO2, main.getVita(), main.getAttacco()));
    }

    public static int sceltaStrada(ArrayList<Nodo> mappa, int posizioneMC) {
        System.out.println(STRADE);
        int passo;
        for (int i=0; i<mappa.get(posizioneMC).getNodiConnessi().size(); i++) { //stampa dei nodi disponibili
            if (!mappa.get(posizioneMC).getNodiConnessi().get(i).isVisitato()) System.out.print(String.format("%d\t", mappa.get(posizioneMC).getNodiConnessi().get(i).getHashCode()));
        }
        System.out.println("\n");
        boolean valido  = false;
        do { //blocco di codice per ottenere lo spostamento
        passo = InputDati.leggiIntero("Dove vuoi andare? immetti il numero, sappi che non puoi andare indietro \n", 0, mappa.size());
            for (Nodo nodo : mappa.get(posizioneMC).getNodiConnessi()) {
                if (passo == nodo.getHashCode()) {
                    if (mappa.get(passo).isVisitato() == false) {
                        valido = true; //aggiorna la posizione dle MC
                    }
                }
            }
        } while (!valido);
        return passo;
    }

    public static void HUDScontro(Mostro mostro, MCharacter main) {
        System.out.println(mostro.getNome());
        System.out.println(String.format(UIVIAGGIO2, mostro.getVita(), mostro.getAttacco()));
        System.out.println(String.format("\n%s", main.getNome()));
        System.out.println(String.format(UIVIAGGIO2, main.getVita(), main.getAttacco()));
        IOStream.pausaDiSistema();
    }

    public static int menuScelta (boolean[] mondifiniti) {
        int mondipossili = 0;
        MyMenu mondoMenu = new MyMenu(SELEZIONE_MONDO, MONDI);
        for (Boolean mondo : mondifiniti) {
            if (mondo == false) break;
            else mondipossili ++;
        }
        int scelta;
        mondipossili ++; //gli aggiungiamo che puo fare almeno l'ultimo non finito
        do {
            scelta = mondoMenu.scegli();
            if (scelta > mondipossili) System.out.println(INDIETRO);
        } while (scelta > mondipossili || scelta == 0); //+1 erche deve poter almeno giocare al ulmino non finito
        return scelta;
    }
}
