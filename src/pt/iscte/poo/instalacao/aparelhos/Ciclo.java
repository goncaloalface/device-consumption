package pt.iscte.poo.instalacao.aparelhos;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Ciclo {
	private ArrayList<Integer> tempos = new ArrayList<Integer>();
	private ArrayList<Integer> potencias = new ArrayList<Integer>();
	
	public Ciclo(JSONArray ciclos) {
		for(Object o : ciclos) {
			JSONObject cycle = (JSONObject) o;
			long duracao = (long)cycle.get("duracao");
			double potencia = (double)cycle.get("potencia");
			tempos.add((int)duracao);
			potencias.add((int)potencia);
		}
	}
	
	public ArrayList<Integer> getTimes() {
		return tempos;
	}
	
	public ArrayList<Integer> getPotencias() {
		return potencias;
	}
}
