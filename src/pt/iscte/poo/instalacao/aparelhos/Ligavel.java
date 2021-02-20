package pt.iscte.poo.instalacao.aparelhos;

public interface Ligavel {
	
	public void liga();
	public void desliga();
	public int potenciaMaxima();
	public int potenciaAtual();
	public String getId();
	public boolean estaLigado();
}
