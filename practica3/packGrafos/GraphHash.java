package packGrafos;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

import packActor.Actor;
import packActor.RegistroActores;
import packPeliculas.ListaPeliculas;
import packPeliculas.Pelicula;
import packPeliculas.RegistroPeliculas;

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
	
	public HashMap<String,Double> pageRank() {
		Double d = 0.85;
		Double PR = 0.0;
		
		//Contiene el numero total de enlaces de cada Actor
		HashMap<String,Double> nodos = new HashMap<String,Double>();
			for (String s: g.keySet()) {
				nodos.put(s,(double)g.get(s).size());
			}
			
		//Contiene 1/N como probabilidad inicial
		HashMap<String,Double> hs = new HashMap<String,Double>();
			for (String s: g.keySet()) {
				hs.put(s,(double)1/g.size());
			}
			
		//Contiene la probabilidad anterior
		HashMap<String,Double> PRs = new HashMap<String,Double>();
		PRs = hs;
		
		//Lo usaremos de auxiliar
		HashMap<String,Double> aux = new HashMap<String,Double>();
		
		//Primera iteracion
		for (String s: hs.keySet()) {
			for (String k: g.get(s)) {
				PR = PR + PRs.get(k);
			}
		hs.put(s,(((1.0-d)/g.size())+d*(PR/nodos.get(s))));
		PR = 0.0;
		}	
	
		//Proceso
		while (diferencia(hs,PRs)>0.0001) {
		aux = hs;
			for (String s: hs.keySet()) {
				for (String k: g.get(s)) {
					PR = PR + PRs.get(k);
				}
			hs.put(s,(((1.0-d)/g.size())+d*(PR/nodos.get(s))));
			PR = 0.0;
			}
		PRs = aux;
		}
		
		HashMap<String,Double> hs2 = new HashMap<String,Double>();
		
		for (String s: hs.keySet()) {
			if (RegistroActores.getRegistroActores().contains(s)){
				hs2.put(s,hs.get(s));
			}
		}
		
		return hs2;
	}
	
	private Double diferencia(HashMap<String,Double> hs,HashMap<String,Double> PRs) {
		Double diferencia = 0.0;		
		for (String s: hs.keySet()) {
			diferencia = diferencia + Math.abs(hs.get(s)-PRs.get(s));
		}		
		return diferencia;
	}
}