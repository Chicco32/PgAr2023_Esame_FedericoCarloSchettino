package it.unibs.PgAr2023.Esame;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class IOStream {
    
    public static final String BENVENUTO = "Benvenuto nel mondo ARNALDOVERSE";
    public static final String INDIETRO = "Devi prima completare i mondi precedenti!";
    public static final String SELEZIONE_MONDO = "ecco i mondi disponibili";
    public static final String[] MONDI = {"Mondo base", "Patente e libbretto", "Tamagolem Rubino e Ghiacciolo"};
    public static final String TENTATIVI = "Hai ancora %d tentativi";
    public static final String PERSONAGGIO = "Crea un nuovo protagonista";
    public static final String NOME = "Che nome ha\n";
    public static final String PASSO = "Dove vuoi andare? immetti il numero, sappi che non puoi andare indietro \n";
    public static final String UIVIAGGIO = "nome: %s                             nodo: %d";
    public static final String UIVIAGGIO2 = "vita: %d                            attacco:%d";
    public static final String CALENDARIO = "Calendario: %s                 Portafoglio %d £";
    public static final String STRADE = "puoi andare a:";
    public static final String EFFETTO = "Qui non ci sono mostri";
    public static final String BUON_EFFETTO_VITA = "La fata radiosa direttamente da ZeldaTM ti dona parte della sua energia e quadagni %d";
    public static final String BUON_EFFETTO_ATTACCO = "Trovi una pozione della forza che ti rende piu macho, guadagni %d";
    public static final String CATTIVO_EFFETTO_VITA = "Fai uno scherzo a una megera e quella ti toglie %d alla vita";
    public static final String CATTIVO_EFFETTO_ATTACCO = "calpesti una trappola per orsi caduta dal cielo e perdi %d nell'attacco";
    public static final String INIZIOSCONTRO = "inizia lo scontro";
    public static final String FINESCONTRO = "Finisce lo scontro";
    public static final String MOSTRO = "Incontri un mostro sul tuo cammino";
    public static final String ATTACCO = "%s fa un attacco e infligge %d di danno";
    public static final String BOSS = "Sei arrivato alla fine, resta solo il boss finale da sconfiggere";
    public static final String MORTE = "YOU DIED *musica di dark soul in sottofondo";
    public static final String VITTORIA = "LEVEL COMPLETED *muscia di Super Mario di fine livello";
    public static final String COMPLETATO = "Hai completato il mondo e ottenuto 110 punti (e una SuperstellaTM)";
    public static final String RICOMPLETATO = "Bravo, hai completato il mondo di nuovo! Purtroppo stavolta niente punti (e nemmeno la SuperstellaTM sadly)";
    public static final String PERSONA = "Ti si presenta un tizio davanti";
    public static final String MULTE[] = {"Lascia andare", "Multala"};
    public static final String PENALITA = "Eh no, male! Sono via 300 sbleuri!";
    public static final String POLIZZIOTTO = "*Tira fuori la carta reverso di uno,  eh no! sono un poliziotto in controllizzazione della verificazione in borghesizzazione. Mi dia 300 sbleuri";
    public static final String PROPOSTA_CORRUZIONE = "Senti amicU, posso offrirti ben %d sbleuri sonanti sonanti se mi lasci andare";
    public static final String ACCETTA[] = {"Accetta"};
    public static final String TASSE = "Ding Ding Ding e' l'ora di pagare le tasse altrimenti muori"; 
    public static final String PAREGGIO_INFINITO = "Giocatore attento! Se metti una combinazione uguale al tuo avversario i golem si annichileranno all'infinito! Prova una combinazione diversa!";
    public static final String STESSO_NOME = "\nNon puoi chimarti come il tuo avversario! Scegli un altro nome."; 
    public static final String GOLEM_MORTO = "\n%s il tuo golem e' morto!"; 
    public static final String GOLEM_SELVATICO_MORTO = "Hai sconfitto il golem selvatico! (no, non puoi catturarlo mi spiace)"; 
    public static final String HUD_SCONTROGOLEM1 = "\t%s: %d PS \t\t\t %s: %d PS"; 
    public static final String HUD_SCONTROGOLEM2 = "\tGolems: %d \t\t\t Golems: %d";
    public static final String GOLEM_RIMASTI1 = "\tGolems: %d \t\t\t Stato: rabbioso";
    public static final String GOLEM_RIMASTI2 = "\tGolems: %d \t\t\t Stato: giocoso";
    public static final String GOLEM_RIMASTI3 = "\tGolems: %d \t\t\t Stato: curioso";
    public static final String GOLEM_RIMASTI4 = "\tGolems: %d \t\t\t Stato: scherzoso";
    public static final String INSERIMENTO_INVALIDO = "Spiacenti, l'inserimento dato non e' valido";
    public static final String LISTA_ELEMENTI = "La lista di possibili elementi in gioco:";
    public static final String ATTACCO_GOLEMS1 = "Il golem di %s lancia %s";
    public static final String ATTACCO_GOLEMS2 = " mentre il golem di %s lancia %s\n";
    public static final String ATTACCO_GOLEMS3 = " mentre il golem selvatico risponde con %s\n";
    public static final String ANNICHILIAMENTO = "Essendo gli stessi elementi si annichiliscono!";
    public static final String DANNI_GOLEM = "Il golem di %s subisce %d danni";
    public static final String DANNI_GOLEM_SELVATICO = "Il golem selvatico subisce %d danni";
    public static final String CARICA_PIETRE = "%s Ecco le pietre che puoi caricare"; 
    public static final String PIETRE_DISPONIBILI = "Pietre Disponibili (%d)"; 
    public static final String SCELTA_ELEMENTO = "Inserisci il nome dell'elemento: ";
    public static final String EFFETTO_UCCIDI_GOLEM1 = "Mentre passeggiate per i ridenti boschi della regione del TamaKanto, un fulmine colpisce in pieno il tuo tamagolem e lo frigge";
    public static final String EFFETTO_UCCIDI_GOLEM2 = "vai a fae snorkeling con i tuoi amati tamagolems, ma appena il primo entra in acqua affonda (è di pietra). TI guarda con occhi tristi e annega";
    public static final String EFFETTO_UCCIDI_GOLEM3 = "Un tamaRapace scambia uno dei tuoi tamagolems per un suo uovo e al volo lo acchiappa e poi vola via";
    public static final String EFFETTO_UCCIDI_GOLEM4 = "Fate un tour sul TamaFuji ma il tuo tamagolem inciampa e cade nella bocca del vulcano";
    public static final String EFFETTO_RESUSCITA_GOLEMS = "-The world open itself before those with noble hearts- dice una strana statua con sopra una divinità, dopo uno strano bagliore azzurrino tutti i tuoi tamagolem recuperano la salute";
    public static final String EFFETTO_ENHANCE_EQUILIBRIO1 = "Senti la terrra tremare sotto i tuoi piedi, poi improvvisamente tutte le pietre emanano un forte bagliore. Ora tutti i danni degli elementi sono raddoppiatti!";
    public static final String EFFETTO_MAGIA_PIETRE_GROTTA = "Trovi una grotta sospetta e ci entri spinto dalla tua curiosità e dal poco senso di autoconservazione";
    public static final String AVVICINATI[] = {"Avvicinati"};
    public static final String EFFETTO_MAGIA_PIETRE_ALTARE = "trovi un altare con simboli simpatici";
    public static final String EFFETTO_MAGIA_PIETRE_SLOT = "L'altare ha due incavi che paiono perfetti per due pietre dei tamagolem";
    public static final String EFFETTO_MAGIA_PIETRE_RIPETIZIONE = "Il tuo tamagolem ti guarda perplesso. Osservi attentamente l'altare e ti accorgi che quella pietra l'hai gia inserita nel primo incavo";
    public static final String EFFETTO_MAGIA_PIETRE_FINALE = "Una voce tuona dall'alto: -STOLTO! HAI MODIFICATO IRREVOCABILMENTE L'EQUILIBRIO NATURALE DEGLI ELEMENTI, ORA NE SUBIRAI LE CONSEGUENZE BWAHAH-";
    public static final String ELABORAZIONE_CAPOPALESTRA = "%s sta pensando a cosa fare...Uhmm...mumble mumble";
    public static final String BENVENUTO_CAPOPALESTRA = "Bravo, hai raggiunto la mia palestra, adesso tocca scontrarci! Preparati perche non la passerai liscia!";
    public static final String RABBIA_CAPOPALESTRA = "Maledetto! Mi hai sconfitto il tamagole! grr non la passerai liscia! *si volta di scatto e pensa a come procedere";
    public static final String RABBIA2_CAPOPALESTRA = "Ancora!? Basta! *si volta di scatto e pensa a come procedere";
    public static final String CAPOPALESTRA_BATTUTO = "*cade in ginocchio -Basta! Basta! hai vinto! Prendi questa medaglia e sparisci di qui!- inizia a piangere";


    //sezione generale
    public static void pausaDiSistema() {
		System.out.println("Premi il tasto invio per continuare");
		String nextLine = new java.util.Scanner(System.in).nextLine();
	}

    public static MCharacter creazioenMC() {
        System.out.println(PERSONAGGIO);
        String nome = InputDati.leggiStringaNonVuota(NOME);
        return new MCharacter(nome);
    }

    public static void mostraPosizione(int posizioneMC, MCharacter main) {
        System.out.println(String.format(UIVIAGGIO, main.getNome(), posizioneMC));
        System.out.println(String.format(UIVIAGGIO2, main.getVita(), main.getAttacco()));
    }

    public static int sceltaStrada(ArrayList<Nodo> mappa, int posizioneMC) {
        System.out.println(STRADE);
        int passo;
        for (int i=0; i<mappa.get(posizioneMC).getNodiConnessi().size(); i++) { //stampa dei nodi disponibili
            if (!mappa.get(posizioneMC).getNodiConnessi().get(i).isVisitato()) System.out.print(String.format("%d\t", mappa.get(posizioneMC).getNodiConnessi().get(i).getHashCode()));
        }
        System.out.println("\n");
        boolean valido  = false;
        do { //blocco di codice per ottenere lo spostamento
        passo = InputDati.leggiIntero(PASSO, 0, mappa.size());
            for (Nodo nodo : mappa.get(posizioneMC).getNodiConnessi()) {
                if (passo == nodo.getHashCode()) {
                    if (mappa.get(passo).isVisitato() == false) {
                        valido = true; //aggiorna la posizione dle MC
                    }
                }
            }
        } while (!valido);
        return passo;
    }

    public static void HUDScontro(Mostro mostro, MCharacter main) {
        System.out.println(mostro.getNome());
        System.out.println(String.format(UIVIAGGIO2, mostro.getVita(), mostro.getAttacco()));
        System.out.println(String.format("\n%s", main.getNome()));
        System.out.println(String.format(UIVIAGGIO2, main.getVita(), main.getAttacco()));
        IOStream.pausaDiSistema();
    }

    public static int menuScelta (int mondifiniti) {
        MyMenu mondoMenu = new MyMenu(SELEZIONE_MONDO, MONDI);
        int scelta;
        mondifiniti ++; //gli aggiungiamo che puo fare almeno l'ultimo non finito
        do {
            scelta = mondoMenu.scegli();
            if (scelta > mondifiniti) System.out.println(INDIETRO);
        } while (scelta > mondifiniti || scelta == 0); 
        return scelta;
    }

    //sezione per il mondo 2
    public static int menuMondo2 (Persona persona, int soldi, LocalDate data, int posizioneMC, MCharacter main) {
        System.out.println(String.format(UIVIAGGIO, main.getNome(), posizioneMC));
        System.out.println(String.format(CALENDARIO, data.toString(), soldi));
        System.out.println("\n");
        System.out.println(persona.toString());
        MyMenu personaMenu = new MyMenu(PERSONA, MULTE);
        int scelta;
        do {
            scelta = personaMenu.scegli();
        } while (scelta == 0);
        return scelta;
    }

    public static int menuCorruzione (int cifra) {
        MyMenu corruzioneMenu = new MyMenu(String.format(PROPOSTA_CORRUZIONE, cifra), ACCETTA);
        int scelta;
        scelta = corruzioneMenu.scegli();
        return scelta;
    }

    //sezione per il mondo 3
    public static void avvertiPareggioInfinito () {
		System.out.println(PAREGGIO_INFINITO);
	}
	
	public static void avvertiStessoNome () {
		System.out.println(STESSO_NOME);
	}

	public static void mostraGolemMorto (String nomeGiocatore) {
		System.out.println(String.format(GOLEM_MORTO,nomeGiocatore));
	}

    public static void mostraGolemSelvaticoMorto () {
		System.out.println(IOStream.GOLEM_SELVATICO_MORTO);
	}
	
	public static void mostraTestataBattaglia(Giocatore G1, Giocatore G2) {
		System.out.println(String.format(HUD_SCONTROGOLEM1,G1.getNome(), G1.getGolemAttivo().getVita(), G2.getNome(), G2.getGolemAttivo().getVita()));
		System.out.println(String.format(HUD_SCONTROGOLEM2, G1.getnGolem(), G2.getnGolem()));
	}

    public static void mostraTestataBattaglia(Giocatore G1, Tamagolem tamaSelvatico) {
		System.out.println(String.format(HUD_SCONTROGOLEM1,G1.getNome(), G1.getGolemAttivo().getVita(), tamaSelvatico.getNome(), tamaSelvatico.getVita()));
        Random random = new Random();
        if (tamaSelvatico.getVita() < G1.getGolemAttivo().getVita()) System.out.println(String.format(IOStream.GOLEM_RIMASTI1, G1.getnGolem()));
        else {
            switch (random.nextInt(2, 5)) {
                case 2: System.out.println(String.format(IOStream.GOLEM_RIMASTI2, G1.getnGolem()));
                break;
                case 3: System.out.println(String.format(IOStream.GOLEM_RIMASTI3, G1.getnGolem()));
                break;
                case 4: System.out.println(String.format(IOStream.GOLEM_RIMASTI4, G1.getnGolem()));
                break;
            }
        }
    }
	
	public static void inserimentoInvalido() {
		System.out.println(INSERIMENTO_INVALIDO);
	}
	
	/**
	 * Mostra quante pietre ci sono nel sacchetto comune dei giocatori. 
	 * @param sacchetto la lista di coppie pietra e numero di quella pietra
	 */
	public static void mostraSacchetto (List<Coppia> sacchetto) {
		System.out.println(LISTA_ELEMENTI);
		for (Coppia coppia: sacchetto) {
			if (coppia.getQuantita()>0)System.out.print(coppia.toString());
		}
	}
	
	/**
	 * Mostra ai due giocatori cosa effettivamente sta succedendo durante uno scontro fra tamagolem.
	 * La funzione a seconda del segno di danno mostra quale golem ha subito dei danni nello scontro.
	 * @param g1 il nome del giocatore attaccante
	 * @param g2 il nome del giocatore difendente
	 * @param elementoDiG1 il nome dell'elemento scagliato da g1
	 * @param elementoDiG2 il nome dell'elemento scagliato da g2
	 * @param danno il valore positivo negativo o zero del danno di quello scontro come intero
	 */
	public static void mostraDanni (String g1, String g2, String elementoDiG1, String elementoDiG2, int danno) {
		System.out.print(String.format(ATTACCO_GOLEMS1, g1, elementoDiG1));
		System.out.print(String.format(ATTACCO_GOLEMS2, g2, elementoDiG2));
		if (danno == 0) System.out.println(ANNICHILIAMENTO);
		else if (danno > 0) System.out.println(String.format(DANNI_GOLEM, g2, danno));
		else if (danno < 0) System.out.println(String.format(DANNI_GOLEM, g1, -danno));
	}
	
    public static void mostraDanni (String g1, String elementoDiG1, String elementoDiG2, int danno) {
		System.out.print(String.format(ATTACCO_GOLEMS1, g1, elementoDiG1));
		System.out.print(String.format(ATTACCO_GOLEMS3, elementoDiG2));
		if (danno == 0) System.out.println(ANNICHILIAMENTO);
		else if (danno > 0) System.out.println(String.format(DANNI_GOLEM_SELVATICO, danno));
		else if (danno < 0) System.out.println(String.format(DANNI_GOLEM, g1, -danno));
	}

	/**
	 * L'interfaccia da evocare ogni volta che si carica di pietre un nuovo tamagolem quando viene evocato.
	 * La funzione aggiorna in autonomia il sacchetto di pietre dei giocatori.
	 * @param sacchetto deve riceve il sacchetto comune per mostarrlo
	 * @param nElementi int del numero di elementi usati nella partita
	 * @param pietrePerGolem il numero P di pietre che ogni golem deve mangiare
	 * @param nomeGiocatore il nome di colui che ha evocato il golem e deve dargli in pasto le pietre
	 * @return una Queue<Elemento.TipoElemento> carica di pietre 
	 */
	public static Queue<Elemento.TipoElemento> caricaSlotPietre (List<Coppia> sacchetto, int nElementi, int pietrePerGolem, String nomeGiocatore) {
		Queue<Elemento.TipoElemento> nuovoSlot = new ArrayDeque<>();
		String nomeElemento;
		//mostra le pietre disponibili all'utente
		System.out.println(String.format(CARICA_PIETRE, nomeGiocatore));
		for (int i= 0; i < pietrePerGolem; i++) {
			System.out.println(String.format(PIETRE_DISPONIBILI, pietrePerGolem - i)); //mostra quante pietre ha ancora da caricare
			IOStream.mostraSacchetto(sacchetto);
			System.out.println("\n");
			nomeElemento = inserimentoElemento(sacchetto, nElementi);
			//aggiunta allo slot golem e rimozione dal sacchetto
			nuovoSlot.add(Elemento.TipoElemento.valueOf(nomeElemento));
			sacchetto.get(Elemento.indiceElemento(nomeElemento)).diminuisciQuantita();
		}
		//System.out.print("\033[H\033[2J"); evocazione demoniaca che pulisce lo schermo
		return nuovoSlot;
	}

    private static String inserimentoElemento(List<Coppia> sacchetto, int nElementi) {
        String nomeElemento;
        boolean valido = false;
        do {
        	nomeElemento = InputDati.leggiStringaNonVuota(SCELTA_ELEMENTO);
            //normalizzazione a prima lettera maiuscola
        	nomeElemento = normalizzazioneNomeElemento(nomeElemento);
        	//controllo della validità dell'inserimento
        	if (Elemento.indiceElemento(nomeElemento) >= nElementi) IOStream.inserimentoInvalido(); // se mette un elemento che è oltre quelli disponibili (tipo magia con 3 elementi) o sbaglia a scrivere lo scarta
        	else if (sacchetto.get(Elemento.indiceElemento(nomeElemento)).getQuantita() == 0) IOStream.inserimentoInvalido(); // se mette un elemento tra quelli disponibili ma la cui quantità è zero va scartato (son finite le pietre)
        	else valido = true;
        } while (!valido);
        return nomeElemento;
    }

    private static String normalizzazioneNomeElemento (String nomeElemento) {
        //normalizzazione a prima lettera maiuscola
    	String iniziale = nomeElemento.substring(0,1).toUpperCase(); //setta la prima lettera maiscola
    	String corpo = nomeElemento.substring(1).toLowerCase(); //setta il corpo minuscolo
        nomeElemento = iniziale + corpo; //ricompone la parola 	
        return nomeElemento;
    }

    public static Map<Character, Integer> chiediElementiPerEffetto (int numElementiUsati) {
        HashMap<Character, Integer> risposta = new HashMap<>();
        int[] indici = new int[2];
        int i = 0;
        System.out.println(EFFETTO_MAGIA_PIETRE_SLOT);
        for (int j = 0; j < numElementiUsati; j++) System.out.print(String.format("\t %s", Elemento.TipoElemento.values()[j]));
        System.out.println("");
        while (i<2) {
            boolean valido;
            String nomeElemento;
            do {
                valido = true;
                System.out.println(String.format(PIETRE_DISPONIBILI, 2 - i));
                nomeElemento = InputDati.leggiStringaNonVuota(String.format(SCELTA_ELEMENTO));
                nomeElemento = normalizzazioneNomeElemento(nomeElemento);
                if (Elemento.indiceElemento(nomeElemento) > numElementiUsati)  {
                    valido = false; //se immette un elemento non presente nella partita o se scrive cose a caso
                    IOStream.inserimentoInvalido();   
                }
                if (i > 0 && Elemento.indiceElemento(nomeElemento) == indici [0]) {
                    valido = false; //se ripete l'elemento di prima
                    System.out.println(EFFETTO_MAGIA_PIETRE_RIPETIZIONE);
                }
            } while (!valido);
            indici[i] = Elemento.indiceElemento(nomeElemento);
            i++;
        }
        System.out.println(EFFETTO_MAGIA_PIETRE_FINALE);
        risposta.put('x', indici[0]);
        risposta.put('y', indici[1]);
        return risposta;
    }

    public static int MenuCavernaTamagolem() {
        System.out.println(EFFETTO_MAGIA_PIETRE_GROTTA);
        IOStream.pausaDiSistema();
        MyMenu m1 = new MyMenu(EFFETTO_MAGIA_PIETRE_ALTARE, AVVICINATI);
        return m1.scegli();
    }
}
