package pt.iscte.poo.eventos;

import pt.iscte.poo.instalacao.Instalacao;
import pt.iscte.poo.instalacao.Linha;
import pt.iscte.poo.instalacao.aparelhos.AparelhoPotenciaVariavel;
import pt.iscte.poo.instalacao.aparelhos.MaquinaLavarRoupa;

public class Evento implements Comparable<Evento>{

	private String tipo;
	private String aparelhoId;
	private int tempo;
	private int potencia;
	private String program;

	public Evento(String tipo, String aparelhoId, int tempo, int potencia,
			String program) {
		this.tipo = tipo;
		this.aparelhoId = aparelhoId;
		this.tempo = tempo;
		this.potencia = potencia;
		this.program = program;
	}

	public void executa() {
		if (this.tipo.equals("LIGA")) {
			for (Linha linha : Instalacao.getInstanciaUnica().getLinhas()) {
				if (linha.findAparelho(aparelhoId) != null) {
					linha.findAparelho(aparelhoId).liga();
				}
			}
		}
		if (this.tipo.equals("DESLIGA")) {
			for (Linha linha1 : Instalacao.getInstanciaUnica().getLinhas()) {
				if (linha1.findAparelho(aparelhoId) != null) {
					linha1.findAparelho(aparelhoId).desliga();
				}
			}
		}
		if (this.tipo.equals("PROGRAMA")) {
			for (Linha linha2 : Instalacao.getInstanciaUnica().getLinhas()) {
				if (linha2.findAparelho(aparelhoId) != null) {
					MaquinaLavarRoupa maq = (MaquinaLavarRoupa) linha2
							.findAparelho(aparelhoId);
					maq.setProgram(program);
				}
			}
		}
		if (this.tipo.equals("AUMENTA")) {
			for (Linha linha3 : Instalacao.getInstanciaUnica().getLinhas()) {
				if (linha3.findAparelho(aparelhoId) != null) {
					AparelhoPotenciaVariavel ap = (AparelhoPotenciaVariavel) linha3
							.findAparelho(aparelhoId);
					ap.aumenta(potencia);
				}
			}
		}
	}

	public int getTime() {
		return tempo;
	}

	@Override
	public int compareTo(Evento arg0) {
		return this.tempo - arg0.tempo;
	}
}
