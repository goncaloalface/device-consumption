package pt.iscte.poo.instalacao.aparelhos;

import pt.iscte.poo.instalacao.Aparelho;

public abstract class AparelhoPotenciaVariavel extends Aparelho{
	
	private int pCurr;

	
	public AparelhoPotenciaVariavel(String string, int i) {
		super(string, i);
		this.pCurr = 0;
	}

	public void setPotenciaAtual(int i) {
		this.pCurr = i;
	}

	@Override
	public int potenciaAtual() {
		if(super.estaLigado() == false)
			return 0;
		return pCurr;
	}
	
	public boolean estaLigado() {
		return super.estaLigado();
	}
	
	public int aumenta(int i) {
		if(pCurr + i <= super.potenciaMaxima())
			pCurr = pCurr + i;
		else
			pCurr = super.potenciaMaxima();
		return pCurr;
	}

}
