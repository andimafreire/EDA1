package packPeliculas;

import java.util.ArrayList;
import java.util.Iterator;

import packActor.Actor;

public class Pelicula {
	private String nombre;
	private ArrayList<Actor> actores;
	private int recaudacion;
	
	public Pelicula(String pNombre) {
		this.nombre=pNombre;
		this.recaudacion=0;
		this.actores = new ArrayList<Actor>();
	}
	
	public String getNombre() {return this.nombre;}
	public ArrayList<Actor> getActores() {return this.actores;}
	public void incrementarRecaudacion(int pRec) {
		this.recaudacion = this.recaudacion + pRec;
		System.out.println("Recaudacion incrementada a "+this.recaudacion+"€");
	}
	
	public void anadirActor (Actor a) {
		if (!actores.contains(a))
			actores.add(a);
	}
	public void imprimirse() {
		System.out.println("Nombre de la película: " + this.nombre);
	}
	public void imprimirseCompleto() {
		System.out.println("Nombre de la película: " + this.nombre);
		System.out.println();
		System.out.println("Recaudación de la película: " + this.recaudacion+"€");
		System.out.println();
		System.out.println("Actores del reparto: ");
		imprimirActores();
		}
	
	
	private Iterator<Actor> getIterador() {return actores.iterator();}
	
	public void imprimirActores() {
		Actor a;
		Iterator<Actor> itr = this.getIterador();
		while (itr.hasNext()) {
			a = itr.next();
			a.imprimirse();
		}
	}

	public int compareTo(Pelicula peli) {
		return this.nombre.compareTo(peli.nombre);
	}
}
