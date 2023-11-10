package it.unibs.PgAr2023.Esame;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class Partita {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private Tamagolem tamaSelvatico;
    private List<Coppia> sacchetto;
    private int nPietre;
    private Equilibrio equilibrio;
    private int nIterazioni;
    
    public Equilibrio getEquilibrio() {
        return this.equilibrio;
    }
    
    /**
     * Il costruttore di partita comprende in se a prima fase dello scontro in quanto setta tutte le dinamiche fra equilibrio, giocatori e golem.
     * Prima si calcola tutti i parametri variabili, poi crea i golem dei nuovi giocatori settanto Giocatore e infine genera un nuovo equilibrio col costruttore della classe Equilibrio.
     * @param nomeG1 String nome del giocatore 1
     * @param nomeG2 String nome del giocatore 1
     * @param nElementi il numero di elementi selezionati per quella partita
     */
    public Partita(Giocatore G1, Giocatore G2, int nElementi, int nPietre, Equilibrio equilibrio)  {
        int nGolem = G1.getnGolem() + G2.getnGolem();
        this.nPietre = nPietre;
        int scortaComune = Math.ceilDiv(nGolem * this.nPietre, nElementi) * nElementi; //formule date dalle slides
        this.giocatore1 = G1;
        this.giocatore2 = G2;
        this.equilibrio = equilibrio;        
        this.caricaSacchetto(nElementi, scortaComune);
        this.nIterazioni = 0;
        this.tamaSelvatico = null;
    }

    public Partita(Giocatore G1, Tamagolem tamaSelvatico, int nElementi, int nPietre, Equilibrio equilibrio)  { //override per gli scontri selvatici
        int nGolem = G1.getnGolem();
        this.nPietre = nPietre;
        int scortaComune = Math.ceilDiv(nGolem * this.nPietre, nElementi) * nElementi; //la scorta è calcolata solo per il golem del main perche il tamaselvatico è gia carico
        this.giocatore1 = G1;
        this.giocatore2 = null;
        this.equilibrio = equilibrio;        
        this.caricaSacchetto(nElementi, scortaComune);
        this.nIterazioni = 0;
        this.tamaSelvatico = tamaSelvatico;
    }



    /**
     * Riempie il sacchetto con le pietre distribuite equamente fra i vari elementi.
     * @param nElementi elementi usati nella partita
     * @param nscortaComune la quantità massima S di pietre nel sacchetto comune
     * @return il sacchetto pieno di pietre
     */
    private void caricaSacchetto(int nElementi, int nscortaComune) {
        List<Coppia> nuovoSacchetto = new ArrayList<>();
        Elemento.TipoElemento[] tipi = Elemento.TipoElemento.values();
        for (int i=0; i<nElementi; i++) {
            nuovoSacchetto.add(new Coppia(tipi[i], Math.ceilDiv(nscortaComune, nElementi)));
        }
        this.sacchetto = nuovoSacchetto;
    }
    /**
     * La funzione scontro regola tutto cio che effettivamente avviene dietro le quinte in uno scontro nella seconda fase, risettando i golems e gestendo i danni e gli attacchi.
     * Essa non ha paramentri ne in ingresso ne in uscita perchè è strettamente collegata all'oggetto partita regolandone gli attributi senza l'ausilio esterno.
     * Ogni chiamata alla funzione scontro rappresenta uno e uno sol lancio di pietre fra golem, quindi va inserita in un ciclo che ne regola la quantità di chiamate.
     * Per mostare a video ciò che sta succedendo essa richiama la controparte IOStream mostraDanni ed eventualmente segnala la morte di uno dei due golem attivi nello scontro.
     */
    public int scontro(int sconfitteNPC) {
        if(this.giocatore1.getGolemAttivo() == null) {
            this.creaGolem(giocatore1);
            if(this.nIterazioni > 0 && this.pariInserimentiSlotTamagolem()) this.evitaPareggioInfinito(this.giocatore1); //dalla seconda evocazione in poi controlla che anche G1 non ricarda nel loop infinito
        }
        if(this.giocatore2.getGolemAttivo() == null) {
            if (sconfitteNPC == 1) System.out.println(IOStream.RABBIA_CAPOPALESTRA);
            else if (sconfitteNPC > 1) System.out.println(IOStream.RABBIA2_CAPOPALESTRA);
            System.out.println(String.format(IOStream.ELABORAZIONE_CAPOPALESTRA , giocatore2.getNome()));
            sconfitteNPC++;
            this.creaNPCGolem(giocatore2);
            if(this.pariInserimentiSlotTamagolem()) this.evitaPareggioInfinito(); //controlla che la partita non cada in un loop infinto di pareggi
        }
        Elemento.TipoElemento pietraG1 = this.giocatore1.getGolemAttivo().scagliaPietra();
        Elemento.TipoElemento pietraG2 = this.giocatore2.getGolemAttivo().scagliaPietra();
        int danno = this.equilibrio.getDannoSubito(pietraG1.toString(), pietraG2.toString());
        IOStream.mostraTestataBattaglia(giocatore1, giocatore2);
        IOStream.mostraDanni(this.giocatore1.getNome(), this.giocatore2.getNome(), pietraG1.toString(), pietraG2.toString(), danno);
        if(danno > 0) {
            if(!this.giocatore2.getGolemAttivo().subisciDanno(danno)) {
                giocatore2.uccidiGolem();
                IOStream.mostraGolemMorto(giocatore2.getNome());
            }
        }
        else if(danno < 0) {
            if(!this.giocatore1.getGolemAttivo().subisciDanno(-danno)) {
                giocatore1.uccidiGolem();
                IOStream.mostraGolemMorto(giocatore1.getNome());
            }
        }
        this.nIterazioni ++;
        IOStream.pausaDiSistema();
        return sconfitteNPC;
    }

    /**
     * la funzione che sostituisce scontro per i tamagolem selvatici, ancora da scrivere
     */
    public void scontroTamaSelvatico() {
        if(this.giocatore1.getGolemAttivo() == null) {
            this.creaGolem(giocatore1);
            if(this.pariInserimentiSlotTamagolem(this.giocatore1)) this.evitaPareggioInfinito(this.giocatore1); //dalla seconda evocazione in poi controlla che anche G1 non ricarda nel loop infinito
        }
        Elemento.TipoElemento pietraG1 = this.giocatore1.getGolemAttivo().scagliaPietra();
        Elemento.TipoElemento pietraG2 = this.tamaSelvatico.scagliaPietra();
        int danno = this.equilibrio.getDannoSubito(pietraG1.toString(), pietraG2.toString());
        IOStream.mostraTestataBattaglia(giocatore1, tamaSelvatico);
        IOStream.mostraDanni(this.giocatore1.getNome(), pietraG1.toString(), pietraG2.toString(), danno);
        if(danno > 0) {
            if(!this.tamaSelvatico.subisciDanno(danno)) {
                IOStream.mostraGolemSelvaticoMorto();
            }
        }
        else if(danno < 0) {
            if(!this.giocatore1.getGolemAttivo().subisciDanno(-danno)) {
                giocatore1.uccidiGolem();
                IOStream.mostraGolemMorto(giocatore1.getNome());
            }
        }
        this.nIterazioni ++;
        IOStream.pausaDiSistema();
    }


    /**
     * Evoca un nuovo golem, chiamando la funzione di IOStream caricaSlotPietre.
     * @param giocatore giocatore a cui appartiene il golem
     */
    private void creaGolem(Giocatore giocatore) {
        Queue<Elemento.TipoElemento> pietre = IOStream.caricaSlotPietre(this.sacchetto, this.equilibrio.getNumElementiUsati(), this.nPietre, giocatore.getNome());
        Tamagolem golem = new Tamagolem(pietre);
        giocatore.setGolemAttivo(golem);
        IOStream.pausaDiSistema();
    }

    private void creaNPCGolem(Giocatore giocatore) {
        Queue<Elemento.TipoElemento> pietre = Giocatore.scegliMigliorMossa(this.sacchetto, this.nPietre, this.equilibrio, 1);
        Tamagolem golem = new Tamagolem(pietre);
        giocatore.setGolemAttivo(golem);
        IOStream.pausaDiSistema();
    }

    /**
     * La funzione fine scontro rappresenta effettivamente la terza fase dello scontro e contemporaneamente il controllo sul ciclo facendo da ponte fra prima e seconda fase. 
     * @return true se uno dei due giocatori ha finito i golem e decreta vincitore l'altro giocatore, false altrimenti
     */
    public boolean fineScontro() {
        if(!this.giocatore1.hasGolem()) {
            return true;
        }
        else if(!this.giocatore2.hasGolem()) {
            System.out.println(IOStream.CAPOPALESTRA_BATTUTO);
            return true;
        }
        return false;
    }
    
    
    //parte di codice dedicata all resetting in caso di pareggio infinito
    /**
     * confronta se i due giocatori hanno messo le stesse pietre nello stesso ordine
     * @return true se hanno messo le stesse pietre nello stesso ordine, false altrimenti
     */
    private boolean pariInserimentiSlotTamagolem() {
    	Elemento.TipoElemento slotDiG1, slotDiG2;
    	boolean pariInserimento = true;
    	for (int i = 0; i<this.nPietre; i++) { //estrae a due a due gli elementi, li confronta, e poi li rimette in fondo in modo da avere alla fine le stesse queue che si avevano all'inizio del confronto
    		slotDiG1 = this.giocatore1.getGolemAttivo().getPietre().poll();
    		slotDiG2 = this.giocatore2.getGolemAttivo().getPietre().poll();
    		if (slotDiG1.compareTo(slotDiG2) != 0) pariInserimento = false; //passa ogni elemnto dei due slot in rassegna e li confronta, se almeno una coppia presenta elementi diversi il compare non darà 0 e i due inserimenti saranno diversi
    		this.giocatore1.getGolemAttivo().getPietre().add(slotDiG1);
    		this.giocatore2.getGolemAttivo().getPietre().add(slotDiG2);
    	} 
    	return pariInserimento;
    }

    private boolean pariInserimentiSlotTamagolem(Giocatore g1) {
    	Elemento.TipoElemento slotDiG1, slotDiG2;
    	boolean pariInserimento = true;
    	for (int i = 0; i<this.nPietre; i++) { //estrae a due a due gli elementi, li confronta, e poi li rimette in fondo in modo da avere alla fine le stesse queue che si avevano all'inizio del confronto
    		slotDiG1 = g1.getGolemAttivo().getPietre().poll();
    		slotDiG2 = this.tamaSelvatico.getPietre().poll();
    		if (slotDiG1.compareTo(slotDiG2) != 0) pariInserimento = false; //passa ogni elemnto dei due slot in rassegna e li confronta, se almeno una coppia presenta elementi diversi il compare non darà 0 e i due inserimenti saranno diversi
    		this.giocatore1.getGolemAttivo().getPietre().add(slotDiG1);
    		this.tamaSelvatico.getPietre().add(slotDiG2);
    	} 
    	return pariInserimento;
    }
    
    private void reimmissioneNelSacchetto() {
    	Elemento.TipoElemento elementoEstratto = this.giocatore2.getGolemAttivo().getPietre().poll(); //estrare dallo slot di G2 l'ultimo elemento
    	for (int i=0; i<this.equilibrio.getNumElementiUsati(); i++) {
    		if (this.sacchetto.get(i).getTipoSacchetto().compareTo(elementoEstratto) == 0) { //confronta gli enum del sacchetto fino a trovare la corrispondenza fra tipi di elementi
    			this.sacchetto.get(i).aumentaQuantità(); //lo rimette nel sacchetto
    		}
    	}
    }

    private void reimmissioneNelSacchetto(Giocatore g1) {
    	Elemento.TipoElemento elementoEstratto = g1.getGolemAttivo().getPietre().poll(); //estrare dallo slot di G2 l'ultimo elemento
    	for (int i=0; i<this.equilibrio.getNumElementiUsati(); i++) {
    		if (this.sacchetto.get(i).getTipoSacchetto().compareTo(elementoEstratto) == 0) { //confronta gli enum del sacchetto fino a trovare la corrispondenza fra tipi di elementi
    			this.sacchetto.get(i).aumentaQuantità(); //lo rimette nel sacchetto
    		}
    	}
    }

    /**
     * avvisa che l'utente ha inserito a inizo partita le stesse pietre dell'altro, e glie le fa reinserire
     */
    private void evitaPareggioInfinito () {
    	do {
    		for (int i=0; i < this.nPietre; i++) {
    			this.reimmissioneNelSacchetto(); //fa il contrario di IOStream caricaSlotPietre, rimuove le pietre dallo slot di G2 e le rimette nel sacchetto
    		}
    		this.giocatore2.getGolemAttivo().resetPietre(Giocatore.scegliMigliorMossa(sacchetto, nPietre, equilibrio, 2)); //richiede all'utente di riempire lo slot nuovamente e lo ripassa al golem
    	} while(this.pariInserimentiSlotTamagolem()); //nel caso sia particolarmente testardo e di nuovo ri immete la stessa sequenza di prima
    }

    private void evitaPareggioInfinito (Giocatore g1) {
    	do {
    		IOStream.avvertiPareggioInfinito();
    		for (int i=0; i < this.nPietre; i++) {
    			this.reimmissioneNelSacchetto(g1); //fa il contrario di IOStream caricaSlotPietre, rimuove le pietre dallo slot di G2 e le rimette nel sacchetto
    		}
    		g1.getGolemAttivo().resetPietre(IOStream.caricaSlotPietre(this.sacchetto, this.equilibrio.getNumElementiUsati(), this.nPietre, g1.getNome())); //richiede all'utente di riempire lo slot nuovamente e lo ripassa al golem
    	} while(this.pariInserimentiSlotTamagolem()); //nel caso sia particolarmente testardo e di nuovo ri immete la stessa sequenza di prima
    }
}

