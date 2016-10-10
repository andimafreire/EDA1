package packActor;

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
	
	public Actor buscarActor(String pNomApe) {
		return mapaActores.get(pNomApe);
	}

}
