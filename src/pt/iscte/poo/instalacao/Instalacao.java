package pt.iscte.poo.instalacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.PriorityQueue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pt.iscte.poo.eventos.Evento;
import pt.iscte.poo.instalacao.aparelhos.Ligavel;

public class Instalacao extends Observable {

	private static Instalacao instance;
	private ArrayList<Linha> linhas = new ArrayList<Linha>();
	private PriorityQueue<Evento> eventList = new PriorityQueue<Evento>();

	private Instalacao() {

	}

	public static Instalacao getInstanciaUnica() {
		if (instance == null) {
			instance = new Instalacao();
		}
		return instance;
	}

	public void novaLinha(String string, int i) {
		linhas.add(new Linha(string, i));
	}

	public ArrayList<Linha> getLinhas() {
		return linhas;
	}

	public void ligaAparelhoATomadaLivre(String string, Ligavel aparelho) {
		for (Linha linha : linhas) {
			if (linha.getName().equals(string))
				linha.ligaAparelho(aparelho);
		}
	}

	public double potenciaNaLinha(String string) {
		double p = 0;
		for (Linha linha : linhas) {
			if (linha.getName().equals(string))
				p = linha.potenciaLinha();
		}
		return p;
	}

	public void removeTodasAsLinhas() {
		linhas.removeAll(linhas);
	}

	public Tomada getTomadaLivre(String string) {
		for (Linha linha : linhas) {
			if (linha.getName().equals(string))
				return linha.getTomadaLivre();
		}
		return null;
	}

	public Ligavel getAparelho(String string) {
		for (Linha linha : linhas) {
			if (linha.findAparelho(string) != null)
				return linha.findAparelho(string);
		}
		return null;
	}

	@Override
	public String toString() {
		String out = "";
		out = "t = " + Relogio.getInstanciaUnica().getTempoAtual() + "\n";
		for (Linha linha : linhas) {
			out += linha.getName() + " " + potenciaNaLinha(linha.getName())
					+ "W" + "\n";
		}
		return out;
	}

	public Instalacao init(JSONArray objectos) {
		Instalacao inst = Instalacao.getInstanciaUnica();
		for (Object obj : objectos) {
			JSONObject linha = (JSONObject) obj;
			String name = (String) linha.get("nome");
			long nTomadas = (long) linha.get("tomadas");
			inst.novaLinha(name, (int) nTomadas);
		}
		return inst;

	}

	public List<Ligavel> lerAparelhos(JSONArray listaAparelhos) {
		List<Ligavel> list = new ArrayList<Ligavel>();
		for (Object obj : listaAparelhos) {
			JSONObject aparelho = (JSONObject) obj;
			Ligavel ap = Aparelho.novoAparelho(aparelho);
			list.add(ap);
		}
		return list;
	}

	public void lerLigacoes(JSONArray listaLigacoes, List<Ligavel> aparelhos) {
		for (Object obj : listaLigacoes) {
			JSONObject aparelho = (JSONObject) obj;
			String name = (String) aparelho.get("aparelho");
			for (Ligavel ligavel : aparelhos) {
				if (ligavel.getId().equals(name))
					instance.ligaAparelhoATomadaLivre(
							(String) aparelho.get("ligadoA"), ligavel);
			}
		}

	}

	public void lerEventos(JSONArray listaEventos) {
		for (Object obj : listaEventos) {
			JSONObject evento = (JSONObject) obj;
			String accao = (String) evento.get("accao");
			long tempo = (long) evento.get("tempo");
			String aparelhoId = (String) evento.get("idAparelho");
			double potencia = 0;
			String program = null;
			if (accao.equals("AUMENTA"))
				potencia = (double) evento.get("valor");
			if (accao.equals("PROGRAMA"))
				program = (String) evento.get("programa");
			eventList.add(new Evento(accao, aparelhoId, (int) tempo,
					(int) potencia, program));
		}
	}


	public void simula(long fim) {
		Map<String, Double> mapa = new HashMap<String, Double>();
		while (Relogio.getInstanciaUnica().getTempoAtual() != fim) {
			while (eventList.size() != 0 && eventList.peek().getTime() == Relogio.getInstanciaUnica()
					.getTempoAtual())
				eventList.poll().executa();
			Relogio.getInstanciaUnica().tique();
			for (Linha linha : linhas)
				mapa.put(linha.getName(),
						instance.potenciaNaLinha(linha.getName()));
			setChanged();
			notifyObservers(mapa);
		}

	}
}
