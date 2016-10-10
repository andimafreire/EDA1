package packActor;

import packPeliculas.ListaPeliculas;
import packPeliculas.Pelicula;

public class Actor implements Comparable<Actor> {
	private String nombre;
	private String apellido;
	private ListaPeliculas susPeliculas;

	public Actor(String pApel, String pNom) {
		//super was here
		this.apellido = pApel;
		this.nombre = pNom;
		this.susPeliculas = new ListaPeliculas();
	}

	public void anadirPelicula(Pelicula p) {
		if ((p != null) && (susPeliculas.esta(p))) {
			susPeliculas.anadirPelicula(p);
		}
	}

	public ListaPeliculas devolverPeliculas() {
		return susPeliculas;
	}

	public void imprimirse() {
		System.out.println(this.nombre + " " + this.apellido);
	}

	public void imprimirPeliculas() {
		this.imprimirse();
		if (susPeliculas.obtenerNumPeliculas() > 0) {
			this.susPeliculas.imprimirPeliculas();
		} else
			System.out.println("No ha aparecido en ninguna película");

	}

	@Override
	public int compareTo(Actor a) {
		return this.nombre.compareTo(a.nombre) + this.apellido.compareTo(apellido);
	}
}
