package pt.iscte.poo.instalacao;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pt.iscte.poo.instalacao.aparelhos.Computador;
import pt.iscte.poo.instalacao.aparelhos.Frigorifico;
import pt.iscte.poo.instalacao.aparelhos.Lampada;
import pt.iscte.poo.instalacao.aparelhos.LampadaVariavel;
import pt.iscte.poo.instalacao.aparelhos.Ligavel;
import pt.iscte.poo.instalacao.aparelhos.MaquinaLavarRoupa;
import pt.iscte.poo.instalacao.aparelhos.MicroOndas;
import pt.iscte.poo.instalacao.aparelhos.Program;
import pt.iscte.poo.instalacao.aparelhos.Torradeira;
import pt.iscte.poo.instalacao.aparelhos.Tripla;

public abstract class Aparelho implements Ligavel {
	private String id;
	private int pMax;
	private boolean isOn;

	public Aparelho(String string, int i) {
		this.id = string;
		this.pMax = i;
		this.isOn = false;
	}

	public void liga() {
		this.isOn = true;
	}

	public void desliga() {
		isOn = false;
	}

	public String getId() {
		return id;
	}

	public int potenciaMaxima() {
		return pMax;
	}

	public int potenciaAtual() {
		if (isOn == false)
			return 0;
		return pMax;
	}

	public boolean estaLigado() {
		return isOn;
	}

	public static Ligavel novoAparelho(JSONObject obj) {
		String object = (String) obj.get("tipo");
		if (object.equals("frigorifico")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potencia");
			return new Frigorifico(name, (int) power);
		}
		if (object.equals("lampada")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potencia");
			return new Lampada(name, (int) power);
		}
		if (object.equals("computador")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potenciaMaxima");
			return new Computador(name, (int) power);
		}
		if (object.equals("torradeira")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potencia");
			return new Torradeira(name, (int) power);
		}
		if (object.equals("microOndas")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potenciaMaxima");
			return new MicroOndas(name, (int) power);
		}
		if (object.equals("lampadaVariavel")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potenciaMaxima");
			return new LampadaVariavel(name, (int) power);
		}
		if (object.equals("tripla")) {
			String name = (String) obj.get("id");
			double power = (double) obj.get("potenciaMaxima");
			long tomadas = (long) obj.get("nTomadas");
			return new Tripla(name, (int) power, (int) tomadas);
		}
		if (object.equals("maqLavarRoupa")) {
			ArrayList<Program> programs = new ArrayList<Program>();
			JSONArray programass = (JSONArray) obj.get("programas");
			for(Object o : programass) {
				JSONObject program = (JSONObject) o;
				programs.add(new Program(program));
			}
			String name = (String) obj.get("id");
			double power = (double) obj.get("potenciaMaxima");
			return new MaquinaLavarRoupa(name, (int) power, programs);
		}

		else
			return null;
	}
}
