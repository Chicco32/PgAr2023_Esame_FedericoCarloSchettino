package it.unibs.PgAr2023.Esame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.stream.XMLStreamException;


public class Mondo2 {
    
    private static final int TASSE = 2200;


    public static boolean muovitiNellaMappa(MCharacter main, int tentativiRimasti) {
        ArrayList<Nodo> mappa = new ArrayList<>();
        ArrayList<Persona> listaPersone = new ArrayList<>();
        Random random = new Random();
        if (random.nextBoolean()) mappa = Mondo1.inizializzaMappaRandom();
        else mappa = Mondo1.getMondoBase();
        int posizioneMC = 0;
        LocalDate datainizio = LocalDate.parse("2023-06-07");
        int stipendiobase = 1100;
        try {
            listaPersone = IOStream.leggiPersone();
        } catch (XMLStreamException e) {
            System.out.println(IOStream.ERRORE_LETTORE);
            return false;
        }
        //inizializzo la mappa
        for (Nodo nodo : mappa) nodo.setPersona(listaPersone.get(random.nextInt(0, listaPersone.size())));


        do { //inizio del viaggio
            
            String codiceValido = Persona.generaCodiceFiscale(mappa.get(posizioneMC).getPersona());
            boolean IDvalido = Mondo2.isCodiceScaduto(mappa.get(posizioneMC).getPersona(), datainizio);
            int  decisione = IOStream.menuMondo2(mappa.get(posizioneMC).getPersona());
            switch (decisione) {
                case 1:
                if (codiceValido.equalsIgnoreCase(mappa.get(posizioneMC).getPersona().getCodiceFiscale()) == false || !IDvalido) { //uno dei dati non va bene
                    System.out.println(IOStream.PENALITA);
                    stipendiobase = stipendiobase - 300;
                }
                break;
                case 2:
                if (codiceValido.equalsIgnoreCase(mappa.get(posizioneMC).getPersona().getCodiceFiscale()) == false || !IDvalido) {
                    stipendiobase = stipendiobase + corrompi(mappa.get(posizioneMC).getPersona());
                }
                break;
            }
            posizioneMC = IOStream.sceltaStrada(mappa, posizioneMC);
            datainizio.plusDays(1);

            if (mappa.get(posizioneMC) instanceof NodoFinale) {
                System.out.println(IOStream.TASSE);
                if (stipendiobase < TASSE) {
                    System.out.println(IOStream.MORTE);
                    return false;
                }
            }
        } while (posizioneMC < mappa.size());
        System.out.println(IOStream.VITTORIA);
        return true;
    }


    private static boolean isCodiceScaduto (Persona persona, LocalDate oggi) {
        if (oggi.isAfter(persona.getScadenzaID())) return true;
        else return false;
    }

    private static int corrompi(Persona persona) {
        Random random = new Random();
        int corruzione = random.nextInt(250, 551);
        int decisione = IOStream.menuCorruzione(corruzione);
        if (decisione == 0) return 0;
        else return corruzione;

    }
}
