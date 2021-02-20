package pt.iscte.poo.instalacao.aparelhos;

import java.util.ArrayList;

import pt.iscte.poo.instalacao.Relogio;

public class MaquinaLavarRoupa extends AparelhoPotenciaVariavel {

	private ArrayList<Program> programs = new ArrayList<Program>();
	private Ciclo cycles;
	private Ciclo cycleSave;
	private int t;
	private int updates;
	
	public MaquinaLavarRoupa(String string, int i, ArrayList<Program> programs) {
		super(string, i);
		this.programs = programs;
		updates = 0;
		
	}

	public void setProgram(String id) {
		for(Program prgm : programs) {
			if(prgm.getId().equals(id))
				this.cycles = prgm.getCycle();
			cycleSave = cycles;
		}
	}
	
	@Override
	public void liga() {
		super.liga();
		t = Relogio.getInstanciaUnica().getTempoAtual();
	}
	
	public void updateProgram() {
		if(updates < cycleSave.getPotencias().size()) {
			if(Relogio.getInstanciaUnica().getTempoAtual() - t < cycleSave.getTimes().get(updates)) {
				super.setPotenciaAtual(cycleSave.getPotencias().get(updates));
			}
			if(Relogio.getInstanciaUnica().getTempoAtual() - t == cycleSave.getTimes().get(updates)) {
				t = Relogio.getInstanciaUnica().getTempoAtual();
				cycleSave.getTimes().remove(updates);
				cycleSave.getPotencias().remove(updates);
			}
		if(cycleSave.getTimes().isEmpty()) {
			desliga();
		}
	}
	}
	
	@Override
	public int potenciaAtual() {
		if(super.estaLigado() == false)
			return 0;	
		updateProgram();
		return super.potenciaAtual();
	}
}
