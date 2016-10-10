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
		System.out.println("Nombre de la pel�cula: " + this.nombre);
		System.out.println();
		System.out.println("Recaudaci�n de la pel�cula: " + this.recaudacion);
		System.out.println();
		System.out.println("Actores del reparto: ");
		actores.imprimirActores();
		}
	
	public void imprimirActores() {
		actores.imprimirActores();
	}
}
