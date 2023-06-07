package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;

public abstract class Nodo {
    
    public enum tipoNodo {INIZIO,INTERMEDIO,FINE}

    private ArrayList<Nodo> nodiConnessi;
    private int hashCode;
    private tipoNodo tipo;
    private Mostro mostro;
    private boolean visitato;

    public Nodo(int hashCode, tipoNodo tipo) {
        this.nodiConnessi = new ArrayList<>();
        this.hashCode = hashCode;
        this.tipo = tipo;
        this.mostro = null;
        this.visitato = false;
    }

    public ArrayList<Nodo> getNodiConnessi() {
        return nodiConnessi;
    }

    public int getHashCode() {
        return hashCode;
    }

    public void aggiungiCollegamento(Nodo nodo) {
        this.nodiConnessi.add(nodo);
    }

    public tipoNodo getTipo() {
        return tipo;
    }

    public Mostro getMostro() {
        return mostro;
    }

    public void setMostro(Mostro mostro) {
        this.mostro = mostro;
    }

    public void setMostrovuoto() {
        this.mostro = null;
    }

    public boolean isVisitato() {
        return visitato;
    }

    public void setVisitato() {
        this.visitato = true;
    }
    
    
}
