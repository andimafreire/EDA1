package packPeliculas;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaPeliculas {
	private ArrayList<Pelicula> lista;
	
	public ListaPeliculas() {
		this.lista = new ArrayList<Pelicula>();
	}
	
	private Iterator<Pelicula> getIterador() {return lista.iterator();}
	
	public Pelicula buscarPelicula(String pNombre) {
		Pelicula p = null;
		Boolean esta = false;
		Iterator<Pelicula> itr = this.getIterador();
		while (itr.hasNext() && !esta) {
			p = itr.next();
			if (p.equals(pNombre))
				esta=true;
		}
		return p;
	}
	
	public boolean esta(Pelicula pPeli) {
		return lista.contains(pPeli);
	}
	
	public void anadirPelicula(Pelicula pPeli) {
		lista.add(pPeli);
	}
	
	public void imprimirPeliculas() {
		Pelicula p;
		Iterator<Pelicula> itr = this.getIterador();
		while (itr.hasNext()) {
			p = itr.next();
			p.imprimirse();
		}
	}

	public int obtenerNumPeliculas() {return lista.size();}
	public Pelicula obtenerPeliEnPos(int pPos) {return lista.get(pPos);}
	
}
