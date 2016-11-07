package packGrafos;

import java.util.ArrayList;
import java.util.HashMap;

import packActor.Actor;
import packPeliculas.ListaPeliculas;
import packPeliculas.Pelicula;

public class GraphHash {

	HashMap<String, ArrayList<String>> g;
	
	public void crearGrafo(ListaPeliculas lPeliculas) {
		// Post: Crea el grafo desde la lista de películas
		//       Los nodos son nombres de actores y títulos de películas 		
		ArrayList<Pelicula> lista = lPeliculas.getListaPeliculas();
		g = new HashMap<String, ArrayList<String>>();
        for (Pelicula p:lista) {
        	if (!g.containsKey(p.getNombre())) {
        		g.put(p.getNombre(), p.actoresString());
        	
        		for (Actor a:p.getArrayActores()) {
        			if (!g.containsKey(a.getNombre())) {
        				g.put(a.getNombre(), a.peliculasString());
        			}
        		}
        	}
        }
	}

	public void print() {
		// Imprime por cada actor, sus peliculas
		int i = 1;
		for (String s: g.keySet()) {
			System.out.print("Element: " + i++ + " " + s + " --> ");
			for (String k: g.get(s)) {
				System.out.print(k + " ### ");
			}
			System.out.println();
		}
	}
	
	public boolean estanConectadas(String p1, String p2) {
		boolean esta = false;
    
		return esta;
	}
	
}