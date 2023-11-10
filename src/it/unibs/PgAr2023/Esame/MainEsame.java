package it.unibs.PgAr2023.Esame;


public class MainEsame {
    public static void main(String[] args) throws Exception {
        int mondiCompletati = 0;
        mondiCompletati = 2;  //citte per developers

        System.out.println(IOStream.BENVENUTO);
        int tentativiRimasti = 10;
        boolean livelloFinito = false;
        MCharacter main = IOStream.creazioenMC();
        do {
            int scelta = IOStream.menuScelta(mondiCompletati);
            switch (scelta) {
                case 1:
                livelloFinito = Mondo1.muovitiNellaMappa(main);
                break;
                case 2:
                livelloFinito = Mondo2.muovitiNellaMappa(main);
                break;
                case 3:
                livelloFinito = Mondo3.muovitiNellaMappa(main);
                break;
                default:
                break;                
            }

            //aggiornamento del mc o della lista dei mondi
            if (!livelloFinito) { //se muore rigenera il main e toglie un tentativo
                tentativiRimasti --;
                System.out.println(String.format(IOStream.TENTATIVI, tentativiRimasti));
                main = IOStream.creazioenMC(); 
            }
            else { //aggiona la lista di mondi completati
                if (scelta > mondiCompletati) { //se è la prima volta che completa il livello
                    mondiCompletati ++; //lo segna
                    System.out.println(IOStream.COMPLETATO); //e ti avverte
                }
                else System.out.println(IOStream.RICOMPLETATO);
            }
            
        } while (tentativiRimasti > 0);
        System.out.println("addio");

    }
}
