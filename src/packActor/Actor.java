package packActor;

import java.util.ArrayList;

import packPeliculas.ListaPeliculas;
import packPeliculas.Pelicula;

public class Actor implements Comparable<Actor> {
	private String nombre;
	private ListaPeliculas susPeliculas;
	private Double pR;

	public Actor(String pNom) {
		this.nombre = pNom;
		this.susPeliculas = new ListaPeliculas();
		this.pR = 0.0;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setPR(Double pPR) {
		this.pR = pPR;
	}
	
	public Double getPR() {
		return this.pR;
	}

	public void anadirPelicula(Pelicula p) {
		if ((p != null) && (!susPeliculas.esta(p))) {
			susPeliculas.anadirPelicula(p);
		}
	}

	public ListaPeliculas devolverPeliculas() {
		return susPeliculas;
	}

	public void imprimirse() {
		System.out.println(this.nombre);
	}

	public void imprimirPeliculas() {
		this.imprimirse();
		if (susPeliculas.obtenerNumPeliculas() > 0) {
			System.out.println("Ha aparecido en las siguientes películas");
			this.susPeliculas.imprimirPeliculas();
		} else
			System.out.println("No ha aparecido en ninguna película");

	}

	 public ArrayList<String> peliculasString() {
		return susPeliculas.toArrayString();
	}
	 
	 public int comparePR(Actor a) {
		return this.pR.compareTo(a.pR);
	}
	
	@Override
	public int compareTo(Actor a) {
		return this.nombre.compareTo(a.nombre);
	}
	
	@Override
	  public String toString() {
	    return nombre.toString();
	  }
}
