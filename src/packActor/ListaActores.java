package packActor;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaActores {
	private ArrayList<Actor> lista;

	public ListaActores() {
		this.lista = new ArrayList<Actor>();
	}
	private Iterator<Actor> getIterador() {return this.lista.iterator();}
	
	public Actor buscarActor(String pApel, String pNom) {
		Actor actor = new Actor(pApel, pNom);
		Actor a;
		Iterator<Actor> itr = this.getIterador();
		while (itr.hasNext()) {
			a = itr.next();
			if (a.compareTo(actor)==0)
				return a;
		}
		return null;
	}

	//TODO: preguntar si es necesario comprobar si el actor esta antes de añadir o eliminar
	public void addActor(Actor a) {
		lista.add(a);
	}

	public void rmvActor(Actor a) {

	}

	public void imprimirActores() {
		Iterator<Actor> itr = this.getIterador();
		while (itr.hasNext()) {
			itr.next().imprimirse();
		}
	}
}
