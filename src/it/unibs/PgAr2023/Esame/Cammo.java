package it.unibs.PgAr2023.Esame;

public class Cammo extends Mostro {
    
    private static final int VITA_BASE = 18;
    private static final int ATTACCO_BASE = 4;


    public Cammo() {
        super(VITA_BASE, ATTACCO_BASE);
    }


    public static int getVitaBase() {
        return VITA_BASE;
    }


    public static int getAttaccoBase() {
        return ATTACCO_BASE;
    }


    


}
