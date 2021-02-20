package pt.iscte.poo.instalacao;

import pt.iscte.poo.instalacao.aparelhos.Ligavel;
import pt.iscte.poo.instalacao.aparelhos.Tripla;

/**
 * Esta classe contem tomadas onde serão ligados os aparelhos.
 * @author André Sousa e Gonçalo Alface
 * @version Final
 * 
 */

public class Linha {

	private Tomada[] tomadas;
	private String nome;
	
	/**
	 * Cria uma uma linha tomadas.
	 * @param nome : o nome da linha.
	 * @param nLinhas : o numero de tomadas por linha.
	 */
	
	public Linha(String nome, int nLinhas) {
		tomadas = new Tomada[nLinhas];
		for (int i = 0; i < tomadas.length; i++)
			tomadas[i] = new Tomada();
		this.nome = nome;
	}
	
	/**
	 * Devolve o nome da linha de tomada.
	 * @return : o nome de essa linha.
	 */
	
	public String getName() {
		return nome;
	}
	
	/**
	 * Devolve uma tomada que esteja livre.
	 * @return : devolve a tomada livre.
	 */
	
	public Tomada getTomadaLivre() {
		for (int i = 0; i < tomadas.length; i++) {
			if (tomadas[i].estaLivre())
				return tomadas[i];
		}
		return null;
	}
	
	/**
	 * Liga um aparelho a uma tomada livre.
	 * @param aparelho : um aparelho para ligar à tomada.
	 */
	
	public void ligaAparelho(Ligavel aparelho) {
		boolean isConnected = false;
		for (int i = 0; i < tomadas.length; i++) {
			if (tomadas[i].estaLivre()) {
				tomadas[i].ligarAparelho(aparelho);
				isConnected = true;
				return;
			}
		}
		
		if(isConnected == false) {
			for (int i = 0; i < tomadas.length; i++) {
					if(!tomadas[i].estaLivre() && tomadas[i].getAparelhoNaTomada() instanceof Tripla) {
				((Tripla)tomadas[i].getAparelhoNaTomada()).ligaAparelho(aparelho);
				return;
				}
			}
		}
	}
	
	/**
	 * Devolve a potência da linha.
	 * @return : devolve a potência de essa linha.
	 */
	
	public double potenciaLinha() {
		double p = 0;
		for (int i = 0; i < tomadas.length; i++) {
			if (!tomadas[i].estaLivre()) {
				try {
					p = p + tomadas[i].potenciaNaTomada();
				}
			catch(IllegalStateException e) {
				}
			}
		}
		return p;
	}
	
	/**
	 * Devolve o aparelho que se procura.
	 * @param string : nome do aparelho que queremos encontrar.
	 * @return : devolve o aparelho com o nome dado, caso exista.
	 */
	
	public Ligavel findAparelho(String string) {
			boolean isFound = false;
			for (int i = 0; i < tomadas.length; i++) {
				if (!tomadas[i].estaLivre())
					if (tomadas[i].idAparelho().equals(string)) {
						isFound = true;
						return tomadas[i].getAparelhoNaTomada();
					}
			}
			if(isFound == false) {
				for (int i = 0; i < tomadas.length; i++) {
					if(!tomadas[i].estaLivre() && tomadas[i].getAparelhoNaTomada() instanceof Tripla) {
						return ((Tripla)tomadas[i].getAparelhoNaTomada()).lookUpAparelho(string);
					}
				}
			}
			return null;
	}
}
