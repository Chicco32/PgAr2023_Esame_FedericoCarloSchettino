package it.unibs.PgAr2023.Esame;

public class MCharacter {
    
    private String nome;
    private int vita;
    private int attacco;

    private static final int VITA_BASE = 20;
    private static final int ATTACCO_BASE = 5;

    public MCharacter(String nome) {
        this.nome = nome;
        this.vita = VITA_BASE;
        this.attacco = ATTACCO_BASE;
    }

    public String getNome() {
        return nome;
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

    
}
