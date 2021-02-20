package pt.iscte.poo.instalacao;

import pt.iscte.poo.instalacao.aparelhos.Ligavel;

public class Tomada {

	private Ligavel aparelhoNaTomada;

	public Tomada() {
		aparelhoNaTomada = null;
	}

	public void ligarAparelho(Ligavel aparelho) {
		aparelhoNaTomada = aparelho;
	}

	public boolean estaLivre() {
		if (aparelhoNaTomada == null)
			return true;
		return false;
	}

	public double potenciaNaTomada() {
		if(aparelhoNaTomada != null)
			return aparelhoNaTomada.potenciaAtual();
		return 0;
	}

	public String idAparelho() {
		return aparelhoNaTomada.getId();
	}

	public Ligavel getAparelhoNaTomada() {
		return aparelhoNaTomada;
	}
}
