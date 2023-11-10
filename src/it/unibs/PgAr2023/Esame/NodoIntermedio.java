package it.unibs.PgAr2023.Esame;

import java.util.Random;

public class NodoIntermedio extends Nodo {
    
    private static double COSTANE_PRESENZA_MOSTRI = 0.4; //percentuale di presenza dei mostri, dato che gli effetti sono anche negativi oltre che positivi conviene che siano leggermente piu probabili

    /**
     * crea un nuovo nodo intermedio
     * @param hashCode il ID del nodo
     * @param livello se è 1 metterà un mostro da livello 1, se 3 mettera un golem
     */
    public NodoIntermedio(int hashCode, int livello) {
        super(hashCode, tipoNodo.INTERMEDIO);
        switch (livello) {
            
            //mondo 1
            case 1:
            if (ciSaràMostro()) this.setMostro(new Mostro());
            else this.setMostrovuoto();
            this.setPersonavuota();
            break;
            //mondo 2
            case 2:
            this.setMostrovuoto();
            break;
            //mondo 3
            case 3:
            if (ciSaràMostro()) this.setMostro(new Tamagolem());
            else this.setMostrovuoto();
            this.setPersonavuota();
            break;
            default:
            break;
        }
        
    }


    private boolean ciSaràMostro() {
        Random random = new Random();
        if (random.nextDouble() <= COSTANE_PRESENZA_MOSTRI) return true;
        else return false;
    }

    
    public static void effettoStatistiche (MCharacter main) {
        Random random = new Random();
        int cambio = random.nextInt(-3, 4);

        if (random.nextBoolean()) { //se da true modifica l'attacco
            if (cambio > 0) System.out.println(String.format(IOStream.BUON_EFFETTO_ATTACCO, cambio));
            else {
                if (main.getAttacco() + cambio < 1) cambio = (main.getAttacco() - 1) * (-1); //se porta l'attacco a zero il cambio viene rimoulato per lasciare almeno 1 di attacco
                System.out.println(String.format(IOStream.CATTIVO_EFFETTO_ATTACCO, cambio));
            }
            main.setAttacco(main.getAttacco() + cambio);
        }
        else { //se da false modifica la vita
            if (cambio > 0) System.out.println(String.format(IOStream.BUON_EFFETTO_VITA, cambio));
            else {
                if (main.getVita() + cambio < 1) cambio = (main.getVita() - 1) * (-1); //se porta la vita a zero il cambio viene rimoulato per lasciare almeno 1 di vita
                System.out.println(String.format(IOStream.CATTIVO_EFFETTO_VITA, cambio));
            }
            main.setVita(main.getVita() + cambio);
        }
    }

    public static void effettoTamagolem (Giocatore g1, Equilibrio equilibrio) {
        Random random = new Random();
        switch (random.nextInt(0, 5) ) {
            case 0:
            g1.resurrezioneTamagolems();
            System.out.println(IOStream.EFFETTO_RESUSCITA_GOLEMS);
            break;
            case 1:
            g1.uccidiGolem();
            switch (random.nextInt(1, 5)) {
                case 1:
                System.out.println(IOStream.EFFETTO_UCCIDI_GOLEM1);
                break;
                case 2:
                System.out.println(IOStream.EFFETTO_UCCIDI_GOLEM2);
                break;
                case 3:
                System.out.println(IOStream.EFFETTO_UCCIDI_GOLEM3);
                break;
                case 4:
                System.out.println(IOStream.EFFETTO_UCCIDI_GOLEM4);
                break;
            }
            break;
            case 2:
            equilibrio.enhanceAll();
            System.out.println(IOStream.EFFETTO_ENHANCE_EQUILIBRIO1);
            break;
            case 3:
            if (IOStream.MenuCavernaTamagolem() == 1) equilibrio.effettoPietreMagico(IOStream.chiediElementiPerEffetto(equilibrio.getNumElementiUsati()));
            break;
            case 4:
            break;
        }
    } //da scrivere
    
}
