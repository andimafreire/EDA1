package packPeliculas;

public class RegistroPeliculas {
	private ListaPeliculas pelis;
	private static RegistroPeliculas elRegistroPeliculas;
	
	private RegistroPeliculas() {}
	
	public static RegistroPeliculas getRegistroPeliculas() {
		if (elRegistroPeliculas == null) elRegistroPeliculas = new RegistroPeliculas();
		return elRegistroPeliculas;
	}
	
	public Pelicula buscarPelicula(String pNombre) {
		return pelis.buscarPelicula(pNombre);		
	}

}
