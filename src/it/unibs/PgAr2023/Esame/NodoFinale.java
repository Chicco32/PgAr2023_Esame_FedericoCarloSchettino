package it.unibs.PgAr2023.Esame;


public class NodoFinale extends Nodo {
    
    public NodoFinale(int hashCode) {
        super(hashCode, tipoNodo.FINE);
        this.setMostro(new Cammo());
    }

    
    
}
