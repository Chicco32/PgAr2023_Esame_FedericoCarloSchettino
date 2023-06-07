package it.unibs.PgAr2023.Esame;

public class Comune {
    
    private String nome;
    private String codice;

    public Comune(String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String toString () {
        StringBuffer descrizione = new StringBuffer();
        descrizione.append("Nome: ");
        descrizione.append(nome);
        descrizione.append(" codice: ");
        descrizione.append(codice);
        descrizione.append("\n");
        return descrizione.toString();
    }
    
}
