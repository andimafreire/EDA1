package packActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RegistroActores {
	private Map<String,Actor> mapaActores;
	private RegistroActores elRegistroActores;
	
	private RegistroActores() {
		mapaActores = new HashMap<>();
	}
	
	public RegistroActores getRegistroActores() {
		if (elRegistroActores == null) elRegistroActores = new RegistroActores();
		return elRegistroActores;
	}
	
	public Actor buscarActor(String pApeNom) {
		return mapaActores.get(pApeNom);
	}

	public void anadirActor(Actor a) {
		String nombre = a.getApellido() +  a.getNombre();
		mapaActores.put(nombre, a);
	}
	
	public void hashToArrayOrdenada () {
		ArrayList<Actor> actoresOrdenada = new ArrayList<Actor>(mapaActores.values());
		//TODO Ordenar la lista
	}
	public void imprimirActores() {
		
	}
}
