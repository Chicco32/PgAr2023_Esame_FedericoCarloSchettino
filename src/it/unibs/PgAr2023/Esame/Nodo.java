package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;

public abstract class Nodo {
    
    public enum tipoNodo {INIZIO,INTERMEDIO,FINE}

    private ArrayList<Nodo> nodiConnessi;
    private int hashCode;
    private tipoNodo tipo;
    private boolean visitato;

    private Mostro mostro;
    private Persona persona;

    public Nodo(int hashCode, tipoNodo tipo) {
        this.nodiConnessi = new ArrayList<>();
        this.hashCode = hashCode;
        this.tipo = tipo;
        this.mostro = null;
        this.visitato = false;
        this.persona = null;
    }

    public ArrayList<Nodo> getNodiConnessi() {
        return nodiConnessi;
    }

    public int getHashCode() {
        return hashCode;
    }

    /**
     * prende la lista di nodi interna al nodo da cui è chiamata e ci aggiunge il nodo in ingresso
     * @param nodo il nodo destinazione da inserire
     */
    public void aggiungiCollegamento(Nodo nodo) {
        this.nodiConnessi.add(nodo);
    }

    public tipoNodo getTipo() {
        return tipo;
    }

    public Mostro getMostro() {
        return mostro;
    }

    public Tamagolem getTamaSelvatico () {
        if (this.mostro instanceof Tamagolem) return (Tamagolem) this.mostro;
        else return null;
    }

    public void setMostro(Mostro mostro) {
        this.mostro = mostro;
    }

    public void setMostrovuoto() {
        this.mostro = null;
    }
    public void setPersonavuota() {
        this.persona = null;
    }

    public boolean isVisitato() {
        return visitato;
    }

    public void setVisitato() {
        this.visitato = true;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
}
