package pt.iscte.poo.instalacao;

public class Relogio {

	private static Relogio instance;
	private int t;
	
    private Relogio() {
    	this.t = 0;
    }
    
    public static Relogio getInstanciaUnica(){
          if(instance == null) {
               instance = new Relogio();
          }
          return instance;
    }

	public void tique() {
		t++;	
	}
	public int getTempoAtual() {
		return t;
	}

}
