package pt.iscte.poo.instalacao.aparelhos;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Program {

	private Ciclo cycle;
	private String id;
	
	public Program(JSONObject program) {
		this.id = (String) program.get("id");
		JSONArray ciclos = (JSONArray) program.get("ciclos");
		this.cycle = new Ciclo(ciclos);
	}
	
	public String getId() {
		return id;
	}
	
	public Ciclo getCycle() {
		return cycle;
	}
	
}
