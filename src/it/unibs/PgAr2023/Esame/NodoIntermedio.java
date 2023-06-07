package it.unibs.PgAr2023.Esame;

import java.util.Random;

public class NodoIntermedio extends Nodo {
    

    public NodoIntermedio(int hashCode) {
        super(hashCode, tipoNodo.INTERMEDIO);
        if (ciSaràMostro()) this.setMostro(new Mostro());
        else this.setMostrovuoto();
    }


    private boolean ciSaràMostro() {
        Random random = new Random();
        return random.nextBoolean();
    }

    
    public static void effettoStatistiche (MCharacter main) {
        Random random = new Random();
        int cambio = random.nextInt(-3, 4);
        if (random.nextBoolean()) { //se da true modifica l'attacco
            main.setAttacco(main.getAttacco() + cambio);
            if (cambio > 0) System.out.println(String.format(IOStream.BUON_EFFETTO_ATTACCO, cambio));
            else System.out.println(String.format(IOStream.CATTIVO_EFFETTO_ATTACCO, cambio));
        }
        else { //se da false modifica la vita
            main.setVita(main.getVita() + cambio);
            if (cambio > 0) System.out.println(String.format(IOStream.BUON_EFFETTO_VITA, cambio));
            else System.out.println(String.format(IOStream.CATTIVO_EFFETTO_VITA, cambio));
        }
    }
    
}
