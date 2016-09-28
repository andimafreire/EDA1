package packPeliculas;

import java.util.ArrayList;

public class ListaPeliculas {
	private ArrayList<Pelicula> lista;
	
	public ListaPeliculas() {
		this.lista = new ArrayList<Pelicula>();
	}
	
	public Pelicula buscarPelicula(String pNombre) {
		Pelicula peli = new Pelicula(pNombre);
		
		return peli;
	}
	
	public void addPelicula(String pNombre) {

	}
	
	public void rmvPelicula(String pNombre) {
		
	}
	
	public void imprimirPeliculas() {
		
	}
}
