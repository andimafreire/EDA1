package packActor;

import java.util.ArrayList;

public class ListaActores {
	private ArrayList<Actor> lista;
	
	public ListaActores(){
		this.lista = new ArrayList<Actor>();
	}
	
	public Actor buscarActor(String pNom, String pApel, int pNum){
		Actor actor = new Actor(pNom,pApel,pNum);
		
		return actor;
	}
	
	public void addActor(String pNom, String pApel, int pNum) {
		
	}
	
	public void rmvActor(String pNom, String pApel, int pNum) {
		
	}
	
	public void imprimirActores() {
		
	}
}
