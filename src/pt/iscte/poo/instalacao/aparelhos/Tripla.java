package pt.iscte.poo.instalacao.aparelhos;

import pt.iscte.poo.instalacao.Aparelho;
import pt.iscte.poo.instalacao.aparelhos.Ligavel;
import pt.iscte.poo.instalacao.Tomada;

public class Tripla extends AparelhoPotenciaVariavel {
	
	private Tomada[] tomadas;

	public Tripla(String string, int pMax, int nTomadas) {
		super(string, pMax);
		this.tomadas = new Tomada[nTomadas];
		for(int i = 0; i < tomadas.length; i++)
			tomadas[i] = new Tomada();
		super.liga();
	}

	public void ligaAparelho(Ligavel aparelho) {	
		for (Tomada tomada : tomadas) {
			if (tomada.estaLivre()) {
				tomada.ligarAparelho((Aparelho) aparelho);
				return;
			}
		}
	}
	
	public Ligavel lookUpAparelho(String id) {
		for(Tomada tomada : tomadas) {
			if(!tomada.estaLivre() && tomada.idAparelho().equals(id))
				return tomada.getAparelhoNaTomada();
		}
		return null;
	}
	
	@Override
	public int potenciaAtual() {
		int currP = 0;
		for (Tomada tomada : tomadas) {
			if (tomada != null )
				currP += tomada.potenciaNaTomada();
		}
		if(currP < super.potenciaMaxima())
			return currP;
		throw new IllegalStateException("Potencia ultrapassa a potencia máxima do Aparelho");
	}
}
