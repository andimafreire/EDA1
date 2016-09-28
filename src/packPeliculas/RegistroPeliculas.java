package packPeliculas;

public class RegistroPeliculas {
	private ListaPeliculas pelis;
	private RegistroPeliculas elRegistroPeliculas;
	
	private RegistroPeliculas() {}
	
	public RegistroPeliculas getRegistroPeliculas() {
		if (elRegistroPeliculas == null) elRegistroPeliculas = new RegistroPeliculas();
		return elRegistroPeliculas;
	}
	
	public Pelicula buscarActor(String pNombre) {
		return pelis.buscarPelicula(pNombre);		
	}

}
