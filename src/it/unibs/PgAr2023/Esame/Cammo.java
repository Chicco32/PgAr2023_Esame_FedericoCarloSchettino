package it.unibs.PgAr2023.Esame;

public class Cammo extends Mostro {
    
    private static final int VITA_BASE = 18;
    private static final int ATTACCO_BASE = 4;


    public Cammo() {
        super(VITA_BASE, ATTACCO_BASE);
        this.setNome("Cammo");
    }

    
    public String toString() {

        StringBuffer str = new StringBuffer();
        str.append("Cammo: Attacco: ");
        str.append(this.getAttacco());
        str.append(" Vita: ");
        str.append(this.getVita());
        return str.toString();

    }

    


}
