package it.unibs.PgAr2023.Esame;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Persona {
    
    
    private static final int CIN_DIVISION_CONST = 26;
    private static final double PROB_CONST = 0.2;

    private int id;
    private char sesso;
    private String nome;
    private String cognome;
    private String comuneNascita;
    private LocalDate dataNascita;
    private String codiceFiscale;
    private LocalDate scadenzaID;
    private boolean polizziotto; 
    
    public Persona(int id, char sesso, String nome, String cognome, String comuneNascita, LocalDate dataNascita, String codiceFiscale, LocalDate scadenzaID) {
        this.id = id;
        this.sesso = sesso;
        this.nome = nome;
        this.cognome = cognome;
        this.comuneNascita = comuneNascita;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.scadenzaID = scadenzaID;
        this.polizziotto = setPolizziotto();
    }


    public static String generaCodiceFiscale (Persona persona) {
        StringBuffer codiceFiscale = new StringBuffer();
        codiceFiscale.append(generaParteCognome(persona));
        codiceFiscale.append(generaParteNome(persona));
        codiceFiscale.append(String.format("%02d",persona.dataNascita.getYear()%100)); //%100 per avere solo le prime due cifre
        codiceFiscale.append(Tabelle.mappaMesi.get(persona.dataNascita.getMonthValue()));
        codiceFiscale.append(String.format("%02d", generaGiornoNormalizzato(persona)));
        codiceFiscale.append(generaCodiceComune(persona));
        codiceFiscale.append(generaCIN(codiceFiscale.toString()));
        return codiceFiscale.toString();
    }

    public static StringBuffer generaParteNome(Persona persona) {
        StringBuffer str = new StringBuffer();
        char lista[] = persona.nome.toCharArray();
        ArrayList<Character> consonanti = new ArrayList<>();
        ArrayList<Character> vocali = new ArrayList<>();
        for (char c : lista) {
            if (Character.toUpperCase(c) == 'A' || Character.toUpperCase(c) == 'E'|| Character.toUpperCase(c) == 'I' || Character.toUpperCase(c) == 'O' || Character.toUpperCase(c) == 'U') {
                vocali.add(Character.valueOf(c));
            }
            else {
                consonanti.add(Character.valueOf(c));
            }
        }
        if(vocali.size() < 3) {
            vocali.add('X');
            vocali.add('X');
        }
        if (consonanti.size() >= 4) {
            str.append(consonanti.get(0));
            str.append(consonanti.get(2));
            str.append(consonanti.get(3));
        } 
        else if (consonanti.size() == 3 ) {
            str.append(consonanti.get(0));
            str.append(consonanti.get(2));
            str.append(vocali.get(0));
        }
        else if (consonanti.size() == 2 || consonanti.size() == 1) {
            str.append(consonanti.get(0));
            str.append(vocali.get(0));
            str.append(vocali.get(1));
        }
        else {
            str.append(vocali.get(0));
            str.append(vocali.get(1));
            str.append(vocali.get(2));
        }
        return str;
    }

    public static StringBuffer generaParteCognome(Persona persona) {
        StringBuffer str = new StringBuffer();
        char lista[] = persona.cognome.toCharArray();
        ArrayList<Character> consonanti = new ArrayList<>();
        ArrayList<Character> vocali = new ArrayList<>();
        for (char c : lista) {
            if (Character.toUpperCase(c) == 'A' || Character.toUpperCase(c) == 'E'|| Character.toUpperCase(c) == 'I' || Character.toUpperCase(c) == 'O' || Character.toUpperCase(c) == 'U') {
                vocali.add(Character.valueOf(c));
            }
            else {
                consonanti.add(Character.valueOf(c));
            }
        }
        while (vocali.size() < 3) vocali.add('X');
        if (consonanti.size() >= 3) {
            str.append(consonanti.get(0));
            str.append(consonanti.get(1));
            str.append(consonanti.get(2));
        } 
        else if (consonanti.size() == 2 ) {
            str.append(consonanti.get(0));
            str.append(consonanti.get(1));
            str.append(vocali.get(0));
        }
        else if (consonanti.size() == 1 ) {
            str.append(consonanti.get(0));
            str.append(vocali.get(0));
            str.append(vocali.get(1));
        }
        else {
            str.append(vocali.get(0));
            str.append(vocali.get(1));
            str.append(vocali.get(2));
        }
        return str;
    }

    public static int generaGiornoNormalizzato (Persona persona) {
        if (persona.sesso == 'M') return persona.dataNascita.getDayOfMonth();
        else return persona.dataNascita.getDayOfMonth() + 40;
    }

    public static StringBuffer generaCodiceComune(Persona persona) {
        StringBuffer str = new StringBuffer();
        try {
            ArrayList<Comune> listaComuni = XMLStream.leggiComuni();
            for (Comune comune : listaComuni) {
                if (persona.comuneNascita.equalsIgnoreCase(comune.getNome())) str.append(comune.getCodice());
        }
        } catch (Exception e) {
            System.out.println("Problemi nella generazione codice fiscale");
            System.out.println(e.getMessage());
        }
        return str;
    }

    public static char generaCIN(String input) {
        char chars[] = input.toCharArray();
        int somma = 0;
        for (int i = 1; i <= chars.length; i++) {
            if (i%2 == 0)  somma = somma + Tabelle.mappaCINPari.get(chars[i -1]);
            else somma = somma + Tabelle.mappaCINDispari.get(chars[i -1]);
        }
        return Tabelle.mappaResti.get(somma % CIN_DIVISION_CONST);
        
    }

    private boolean setPolizziotto() {
        Random random = new Random();
        if (random.nextDouble() > PROB_CONST) return false;
        else return true;
    }

    public int getId() {
        return id;
    }
    public char getSesso() {
        return sesso;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getComuneNascita() {
        return comuneNascita;
    }
    public LocalDate getDataNascita() {
        return dataNascita;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public boolean isPolizziotto() {
        return polizziotto;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setComuneNascita(String comuneNascita) {
        this.comuneNascita = comuneNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
    
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    public LocalDate getScadenzaID() {
        return scadenzaID;
    }

    public void setScadenzaID(LocalDate scadenzaID) {
        this.scadenzaID = scadenzaID;
    }
    
    public String toString() {
        StringBuffer descrizione = new StringBuffer();
        descrizione.append(String.format("Nome: %s Cognome: %s \n",this.nome, this.cognome));
        descrizione.append(String.format("Sesso: %c \n", this.sesso));
        descrizione.append(String.format("data di nascita: %s  comune di nascita: %s\n",this.dataNascita.toString(), this.comuneNascita));
        descrizione.append(String.format("Codice Fiscale: %s \n", this.codiceFiscale));
        descrizione.append(String.format("Scadenza ID: %s", this.scadenzaID.toString()));
        return descrizione.toString();
    }


}
