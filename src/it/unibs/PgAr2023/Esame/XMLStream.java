package it.unibs.PgAr2023.Esame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.xml.stream.*;

public class XMLStream {
    
    //filepath
    public static final String comuniPath = "C:\\Users\\info\\VS-Java_workspace\\EsameArnaldo\\input\\TestFiles\\Papers_Please\\Comuni.xml";
    public static final String inputPersonePath = "C:\\Users\\info\\VS-Java_workspace\\EsameArnaldo\\input\\TestFiles\\Papers_Please\\PersoneID.xml";
    public static final String mappePath = "C:\\Users\\info\\VS-Java_workspace\\EsameArnaldo\\input\\TestFiles\\Mappe.xml";
    public static final String ERRORE_LETTORE = "problemi a leggere i files nello stream";
    public static final int MAX_NODI = 16; //numero massimo di nodi per mappa
    public static final int NUMERO_MAPPE = 2; //quante mappe sono registrate nel file


    private static XMLStreamReader inzializzaReader(String path) {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
            System.out.println(e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
        return xmlr;
    }

    public static ArrayList<Comune> leggiComuni()  throws XMLStreamException {
        ArrayList<Comune> comuni = new ArrayList<>();
        String lastNome = "", lastCodice = "";
        boolean activeNome = false, activeCodice = false;
        XMLStreamReader xmlr = inzializzaReader(comuniPath);
        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                if (xmlr.getLocalName().equalsIgnoreCase("comune")) {
                    lastNome = null;
                    lastCodice = null;
                    activeCodice = activeNome = false;
                } else if (xmlr.getLocalName().equalsIgnoreCase("codice")) activeCodice = true;
                else if (xmlr.getLocalName().equalsIgnoreCase("nome")) activeNome = true;
                break;
                case XMLStreamConstants.CHARACTERS:
                if (activeCodice) {
                    lastCodice = xmlr.getText();
                    activeCodice = false;
                }
                else if (activeNome) {
                    lastNome = xmlr.getText();
                    activeNome = false;
                }
                break;
                case XMLStreamConstants.END_ELEMENT:
                if (xmlr.getLocalName().equalsIgnoreCase("comune")) comuni.add(new Comune(lastNome, lastCodice));
                break;
            }
        xmlr.next();
        }
        xmlr.close();
        return comuni;
    }

    public static ArrayList<Persona> leggiPersone () throws XMLStreamException {
        ArrayList<Persona> persone = new ArrayList<>();
        XMLStreamReader xmlr = inzializzaReader(inputPersonePath);
        String lastNome = null, lastCognome = null, lastComune = null, lastCodice = null;
        LocalDate lastDate = LocalDate.MIN;
        LocalDate lastID = LocalDate.MIN;
        char lastSesso = '0';
        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                switch (xmlr.getLocalName()) {
                    case "nome":
                    lastNome = xmlr.getElementText();
                    break;
                    case "cognome":
                    lastCognome = xmlr.getElementText();
                    break;
                    case "sesso":
                    lastSesso = xmlr.getElementText().charAt(0);
                    break;
                    case "comune_nascita":
                    lastComune = xmlr.getElementText();
                    break;
                    case "data_nascita":
                    lastDate = LocalDate.parse(xmlr.getElementText());
                    break;
                    case "codice_fiscale":
                    lastCodice = xmlr.getElementText();
                    break;
                    case "data_scadenza_id":
                    lastID = LocalDate.parse(xmlr.getElementText());
                    break;
                    default:
                    break;
                }
                break;
                case XMLStreamConstants.END_ELEMENT:
                if (xmlr.getLocalName().equals("persona")) persone.add(new Persona(persone.size(), lastSesso, lastNome, lastCognome, lastComune, lastDate,lastCodice, lastID));
                break;
            }
        xmlr.next();
        }
        xmlr.close();
        return persone;
    }

    public static int[][] leggiMappe () throws XMLStreamException {
        int[][] mappa = new int[MAX_NODI][MAX_NODI];
        for (int[] is : mappa) Arrays.fill(is, 0);
        XMLStreamReader xmlr = inzializzaReader(mappePath);
        Random random = new Random();
        int prossimaMappa = random.nextInt(1, NUMERO_MAPPE + 1);
        int inizio = 0;
        int x = 0, y = 0;

        while (xmlr.hasNext()) {
            if (inizio == prossimaMappa) {
                switch (xmlr.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                    if (xmlr.getLocalName().equalsIgnoreCase("COLLEGAMENTO")) { //la y sara il nodo collegato
                        y = Integer.parseInt(xmlr.getElementText());
                        //sempre perche il file parte da 1 mentre la tabella da zero 
                        mappa[x - 1][y - 1] = 1;
                        mappa[y - 1][x - 1] = 1;
                    }
                    if (xmlr.getLocalName().equalsIgnoreCase("NODO")) x++; //la x sara il nodo di partenza
                    break;
                    case XMLStreamConstants.CHARACTERS:
                    if (xmlr.getText().equalsIgnoreCase("FINE")) inizio++; //lo aumenta cosi finisce la mappa
                    break;
                }
            }
            else {
                switch (xmlr.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                    if(xmlr.getLocalName().equalsIgnoreCase("MAPPA")) inizio++; //lo aumenta cosi inizia la lettura della mappa
                    break;
                }
            }
            if(inizio > prossimaMappa) break; //se finisce la lettura esce dal ciclo
            xmlr.next();
        }
        xmlr.close();
        return mappa;
    }

}
