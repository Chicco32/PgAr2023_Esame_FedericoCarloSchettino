package it.unibs.PgAr2023.Esame;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Giocatore extends MCharacter {
    
    private int nGolem;
    private int maxgolems;
    private Tamagolem golemAttivo;

    public Giocatore(String nome, int nGolem) {
        super(nome);
        this.nGolem = nGolem;
        this.maxgolems = nGolem;
    }
    
    public Giocatore(MCharacter giocatore, int nGolem) {
        super(giocatore.getNome());
        this.nGolem = nGolem;
        this.maxgolems = nGolem;
    }

    public int getnGolem() {
    	return this.nGolem;
    }

    public void setGolemAttivo(Tamagolem golemAttivo) {
        this.golemAttivo = golemAttivo;
    }

    public Tamagolem getGolemAttivo() {
        return this.golemAttivo;
    }

    /**
     * Rimuove il golem attivo e diminuisce di uno nGolem ad indicare che quel golem è rimosso dal gioco.
     * La funzione inoltre segnala se il giocatore è ancora possibilitato a giocare o meno.
     * Se nGOlem arriva a zero significa che il giocatore corrispondente non ha piu golem da evocare.
     * @return true se il giocatore ha ancora golem da poter evocare, false altrimenti
         */
    public void uccidiGolem() {
        this.golemAttivo = null;
        this.nGolem--;
    }

    public void resurrezioneTamagolems() {
        this.nGolem = this.maxgolems;
    }

    /**
     * Controlla se il giocatore ha ancora tamagolem da evocare.
     * @return true se il giocatore ha ancora tamagolem, false altrimenti
     */
    public boolean hasGolem() {
        if (this.nGolem > 0)
            return true;
        return false;
    }

    public static Queue<Elemento.TipoElemento>  scegliMigliorMossa (List<Coppia> sacchetto, int pietrePerGolem, Equilibrio equilibrio, int tentativi) {
        Queue<Elemento.TipoElemento> nuovoSlot = new ArrayDeque<>();
        String nomeElemento;
        for (int i= 0; i < pietrePerGolem; i++) {

            boolean valido;
            do { //blocco per la ricerca di un inserimento valido
                valido = true;
                nomeElemento = equilibrio.cercaMigliorDanno(tentativi).toString();
                if (sacchetto.get(Elemento.indiceElemento(nomeElemento)).getQuantita() == 0) {
                    tentativi++;
                    valido = false;
                }
            } while (valido == false);

            //aggiunta allo slot
            nuovoSlot.add(Elemento.TipoElemento.valueOf(nomeElemento));
			sacchetto.get(Elemento.indiceElemento(nomeElemento)).diminuisciQuantita();
        }

        return nuovoSlot;
    }

    
}
