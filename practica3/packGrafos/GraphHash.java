package packGrafos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

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
		boolean conectadas = false;
		if(!g.containsKey(p1) || !g.containsKey(p2)) {return conectadas;}
        else {
        	String actual;
    		Deque<String> paraComprobar = new ArrayDeque<String>();
    		HashSet<String> comprobados = new HashSet<String>();
    		paraComprobar.push(p1);
    		comprobados.add(p1);
    		
    		while(!conectadas && !paraComprobar.isEmpty()) {
                actual = paraComprobar.pop();
                if(actual.equals(p2)) {conectadas = true;}
                else {
                    for (String x: g.get(actual)) {
                        if(!comprobados.contains(x)) {
                            paraComprobar.push(x);
                            comprobados.add(x);
                        }
                    }
                }
    		}
        }
		return conectadas;
	}
	
	public boolean contains(String p1) {
		return g.containsKey(p1);
	}	
}