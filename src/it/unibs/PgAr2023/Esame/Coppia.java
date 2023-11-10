package it.unibs.PgAr2023.Esame;

public class Coppia {
    	private Elemento.TipoElemento tipoSacchetto;
    	private int quantita;
    	
    	public Coppia (Elemento.TipoElemento tipoSacchetto, int quantita) {
    		this.quantita = quantita;
    		this.tipoSacchetto = tipoSacchetto;
    	}

		public Elemento.TipoElemento getTipoSacchetto() {
			return tipoSacchetto;
		}

		public int getQuantita() {
			return quantita;
		}

		public void diminuisciQuantita() {
			this.quantita--;
		} 
		
		public void aumentaQuantità() {
			this.quantita++;
		}
		
		public String toString() {
			StringBuffer str = new StringBuffer();
			str.append("\nTipo di Elemento: ");
			str.append(this.tipoSacchetto.toString());
			str.append(" (");
			str.append(this.quantita);
			str.append(")");
			return str.toString();
		}
}
