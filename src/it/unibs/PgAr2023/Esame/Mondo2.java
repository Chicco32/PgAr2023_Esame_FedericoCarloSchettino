package it.unibs.PgAr2023.Esame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.stream.XMLStreamException;


public class Mondo2 {
    
    private static final int NMONDO = 2;
    private static final int TASSE = 2200;


    public static boolean muovitiNellaMappa (MCharacter main) {
        ArrayList<Nodo> mappa = new ArrayList<>();
        ArrayList<Persona> listaPersone = new ArrayList<>();
        Random random = new Random();
        if (random.nextBoolean()) mappa = CreatoreMappe.inizializzaMappaRandom(NMONDO);
        else mappa = CreatoreMappe.inizializzaMappaBase(NMONDO);
        int posizioneMC = 0;
        LocalDate datainizio = LocalDate.parse("2023-06-07");
        int stipendiobase = 1100;
        try {
            listaPersone = XMLStream.leggiPersone();
        } catch (XMLStreamException e) {
            System.out.println(XMLStream.ERRORE_LETTORE);
            return false;
        }
        //inizializzo la mappa
        for (Nodo nodo : mappa) nodo.setPersona(listaPersone.get(random.nextInt(0, listaPersone.size())));


        do { //inizio del viaggio

            mappa.get(posizioneMC).setVisitato();
            String codiceValido = Persona.generaCodiceFiscale(mappa.get(posizioneMC).getPersona());
            int  decisione = IOStream.menuMondo2(mappa.get(posizioneMC).getPersona(), stipendiobase, datainizio, posizioneMC, main);
            switch (decisione) {
                case 1:
                if (codiceValido.equalsIgnoreCase(mappa.get(posizioneMC).getPersona().getCodiceFiscale()) == false || Mondo2.isCodiceScaduto(mappa.get(posizioneMC).getPersona(), datainizio)) { //uno dei dati non va bene
                    System.out.println(IOStream.PENALITA);
                    stipendiobase = stipendiobase - 300;
                }
                break;
                case 2:
                if (codiceValido.equalsIgnoreCase(mappa.get(posizioneMC).getPersona().getCodiceFiscale()) == false || Mondo2.isCodiceScaduto(mappa.get(posizioneMC).getPersona(), datainizio)) {
                    stipendiobase = stipendiobase + corrompi(mappa.get(posizioneMC).getPersona());
                }
                else {
                    System.out.println(IOStream.PENALITA);
                    stipendiobase = stipendiobase - 300;
                }
                break;
            }
            datainizio = datainizio.plusDays(1);
            posizioneMC = IOStream.sceltaStrada(mappa, posizioneMC);
            

            if (mappa.get(posizioneMC) instanceof NodoFinale) {
                System.out.println(IOStream.TASSE);
                if (stipendiobase < TASSE) {
                    System.out.println(IOStream.MORTE);
                    return false;
                }
                else break;
            }
        } while (posizioneMC < mappa.size());
        System.out.println(IOStream.VITTORIA);
        return true;
    }


    private static boolean isCodiceScaduto (Persona persona, LocalDate oggi) {
        if (persona.getScadenzaID().isAfter(oggi)) return false;
        else return true;
    }

    private static int corrompi(Persona persona) {
        Random random = new Random();
        int corruzione = random.nextInt(250, 551);
        int decisione = IOStream.menuCorruzione(corruzione);
        if (decisione == 0) return 0;
        else {
            if (persona.isPolizziotto()) {
                System.out.println(IOStream.POLIZZIOTTO);
                return -300;
            }
            else return corruzione;
        }
    }
}
