package it.unibs.PgAr2023.Esame;


public class NodoFinale extends Nodo {
    
    private Giocatore capoPalestra;
    private static final int NGOLEMBOSS = 2;

    public NodoFinale(int hashCode, int livello) {
        super(hashCode, tipoNodo.FINE);
        this.setPersonavuota();
        switch (livello) {
            //mondo 1
            case 1:
            this.setMostro(new Cammo());
            this.setPersonavuota();
            this.setCapoPalestraVuoto();
            break;
            //mondo 2
            case 2:
            this.setMostrovuoto();
            this.setPersonavuota();
            this.setCapoPalestraVuoto();
            break;
            //mondo 3
            case 3:
            this.setMostrovuoto();
            this.setPersonavuota();
            this.setCapoPalestra(new Giocatore("Ki-Boh", NGOLEMBOSS));
            break;
            default:
            break;
        }  
    }

    public Giocatore getCapoPalestra() {
        return capoPalestra;
    }

    public void setCapoPalestra(Giocatore capoPalestra) {
        this.capoPalestra = capoPalestra;
    }


    public void setCapoPalestraVuoto() {
        this.capoPalestra = null;
    }
    
}
