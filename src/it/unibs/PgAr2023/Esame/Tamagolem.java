package it.unibs.PgAr2023.Esame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Tamagolem extends Mostro{

    public static final int VITA_MAX = 10;

    private Queue<Elemento.TipoElemento> pietre;

    public Tamagolem(Queue<Elemento.TipoElemento> pietre) {
        super(VITA_MAX, 0);
        this.pietre = pietre;
        this.setNome("Tamagolem");
    }

    public Tamagolem() { //override per i tamaselvatici
        super(VITA_MAX, 0);
        this.pietre = new ArrayDeque<>();
        this.setNome("Tamagolem");
    }

    public void caricaPietreRandom (int nElementi,int nPietre) {
        Random random = new Random();
        int scortaComune = Math.ceilDiv(2 * nPietre, nElementi) * nElementi;
        List<Coppia> sacchetto = new ArrayList<>();
        Elemento.TipoElemento[] tipi = Elemento.TipoElemento.values();
        for (int i=0; i<nElementi; i++) sacchetto.add(new Coppia(tipi[i], Math.ceilDiv(scortaComune, nElementi)));
        for (int i =0; i<nPietre; i++) {
            int prossimaPietra;
            do {
                prossimaPietra = random.nextInt(0, nElementi);
            } while (sacchetto.get(prossimaPietra).getQuantita() == 0);
            this.pietre.add(sacchetto.get(prossimaPietra).getTipoSacchetto());
            sacchetto.get(prossimaPietra).diminuisciQuantita();
        }
    }

    public Queue<Elemento.TipoElemento> getPietre() {
    	return this.pietre;
    }
    
    public void resetPietre(Queue<Elemento.TipoElemento> pietre) {
    	this.pietre = pietre;
    }
    
    /**
     * Scaglia la pietra in fondo alla coda e la rimette all'inizio.
     * @return la pietra che viene scagliata
     */
    public Elemento.TipoElemento scagliaPietra() {
        Elemento.TipoElemento pietra = pietre.poll();
        pietre.add(pietra);
        return pietra;
    }

    /**
     * Infligge del danno al golem.
     * @param danno quantità di danno inflitta
     * @return true se il golem sopravvive al colpo, false altrimenti
     */
    public boolean subisciDanno(int danno) {
        this.setVita(this.getVita() - danno);
        if (this.getVita() <= 0)
            return false;
        return true;
    }
}
