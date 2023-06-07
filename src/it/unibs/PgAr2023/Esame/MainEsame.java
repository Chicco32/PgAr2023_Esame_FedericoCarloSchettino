package it.unibs.PgAr2023.Esame;


public class MainEsame {
    public static void main(String[] args) throws Exception {
        boolean mondiCompletati[] = new boolean[2];
        mondiCompletati[0] = false;
        mondiCompletati[1] = false;



        System.out.println(IOStream.BENVENUTO);
        int tentativiRimasti = 10;
        boolean finito = false;
        do {
            MCharacter main = IOStream.creazioenMC();
            System.out.println(String.format(IOStream.TENTATIVI, tentativiRimasti));
            int scelta = IOStream.menuScelta(mondiCompletati);
            switch (scelta) {
                case 1:
                finito = Mondo1.muovitiNellaMappa(main, tentativiRimasti);
                if (finito)  {
                    mondiCompletati[0] = true;
                    System.out.println(IOStream.COMPLETATO);
                }
                else tentativiRimasti--;
                break;
                case 2:
                finito = Mondo2.muovitiNellaMappa(main, tentativiRimasti);
                if (finito)  {
                    mondiCompletati[1] = true;
                    System.out.println(IOStream.COMPLETATO);
                } 
                else tentativiRimasti--;
                default:
                break;
            }
            
        } while (tentativiRimasti > 0);
        
        System.out.println("addio");

    }
}
