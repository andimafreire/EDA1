package packPeliculas;

import packActor.ListaActores;

public class Pelicula {
	private String nombre;
	private ListaActores actores;
	private int recaudacion;
	
	public Pelicula(String pNombre) {
		this.nombre=pNombre;
	}
	
	public void incrementarRecaudacion(int pRec) {
		this.recaudacion = this.recaudacion + pRec;
	}
	
	public void imprimirse() {

	}
	
	public void imprimirActores() {

	}
}
