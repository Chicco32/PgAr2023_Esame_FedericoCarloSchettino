package it.unibs.PgAr2023.Esame;

import java.util.Random;

public class Mostro {
    
    private int vita;
    private int attacco;
    private static final int VITA_BASE_MOSTRO = 12;
    private static final int ATTACCO_BASE_MOSTRO = 3;
    private String nome;

    public Mostro() { //con la vita e attacco base del mostro
        this.vita = alteraVita();
        this.attacco = alteraAttacco();
        this.nome = "Boboklin";
    }

    public Mostro(int vitaBase, int attaccoBase) { //ovverride per cammo
        this.vita = alteraVita(vitaBase);
        this.attacco = alteraAttacco(attaccoBase);
        this.nome = "Cammo";
    }

    private int alteraVita() {
        Random random = new Random();
        int variazione = random.nextInt(-5, 6);
        return VITA_BASE_MOSTRO + variazione;
    }

    private int alteraVita(int vita) {
        Random random = new Random();
        int variazione = random.nextInt(-5, 6);
        return vita + variazione;
    }

    private int alteraAttacco() {
        Random random = new Random();
        int variazione = random.nextInt(-2, 3);
        return ATTACCO_BASE_MOSTRO + variazione;
    }

    private int alteraAttacco(int attacco) {
        Random random = new Random();
        int variazione = random.nextInt(-2, 3);
        return attacco + variazione;
    }

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getAttacco() {
        return attacco;
    }

    public void setAttacco(int attacco) {
        this.attacco = attacco;
    }

    public String getNome() {
        return nome;
    }

    public void scontro(MCharacter main) {
            this.setVita(this.getVita() - main.getAttacco()); //l'attacco del MC
            System.out.println(String.format(IOStream.ATTACCO, main.getNome(), main.getAttacco()));
            main.setVita(main.getVita() - this.getAttacco()); //l'attacco del mostro
            System.out.println(String.format(IOStream.ATTACCO, "mostro", this.getAttacco()));//iostream mostrattacco
    }


}
