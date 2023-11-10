package it.unibs.PgAr2023.Esame;


public class Elemento {
	public static enum TipoElemento {Terra, Aria, Fuoco, Acqua, Etere, Fisico, Veleno, Psiche, Magia, Oscuro};
	public static int NUM_MAX_ELEMENTI = 10;
	private static String[] ListaDiTipi = {"Terra", "Aria", "Fuoco", "Acqua", "Etere", "Fisico", "Veleno", "Psiche", "Magia", "Oscuro"};
	
	/**
	 * Ritorna l'indice del enum elemento secondo l'ordine in cui compaiono.
	 * @param nomeElemento la stringa con il nome dell'elemento dalla posizione ignota
	 * @return l'indice dell'elemento se esiste, altrimenti il numero massimo di elementi ad indicare che quel nome non è nell'enum
	 */
	public static int indiceElemento(String nomeElemento) {
		for (int i=0; i<Elemento.NUM_MAX_ELEMENTI; i++) {
			if (ListaDiTipi[i].equalsIgnoreCase(nomeElemento)) return i; 
		}
		return Elemento.NUM_MAX_ELEMENTI;
	}
}
