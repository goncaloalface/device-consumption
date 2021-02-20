package pt.iscte.poo.instalacao.aparelhos;

import java.util.Random;

public class Computador extends AparelhoPotenciaVariavel {
	
	public Computador(String string, int i) {
		super(string, i);
		Random r = new Random();
		super.setPotenciaAtual(r.nextInt(i));
	}

	@Override
	public int potenciaAtual() {
		if(super.estaLigado() == false)
			return 0;
		Random r = new Random();
		return r.nextInt(super.potenciaMaxima());
	}
}
