package packActor;

public class RegistroActores {
	private ListaActores actores;
	private RegistroActores elRegistroActores;
	
	private RegistroActores() {}
	
	public RegistroActores getRegistroActores() {
		if (elRegistroActores == null) elRegistroActores = new RegistroActores();
		return elRegistroActores;
	}
	
	public Actor buscarActor(String pApel, String pNom) {
		return actores.buscarActor(pApel, pNom);		
	}

}
