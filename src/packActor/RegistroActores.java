package packActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class RegistroActores {
	private Map<String, Actor> mapaActores;
	private static RegistroActores elRegistroActores;

	private RegistroActores() {
		mapaActores = new HashMap<>();
	}

	public static RegistroActores getRegistroActores() {
		if (elRegistroActores == null)
			elRegistroActores = new RegistroActores();
		return elRegistroActores;
	}

	public boolean contains(String pActor) {
		return mapaActores.containsKey(pActor);
	}
	
	public Actor buscarActor(String pApeNom) {
		return mapaActores.get(pApeNom);
	}

	public void anadirActor(Actor a) {
		if (!mapaActores.containsKey(a.getNombre())){
			mapaActores.put(a.getNombre(), a);
		}
	}
	
	public void eliminarActor(String pApeNom) {
		if(mapaActores.containsKey(pApeNom)) {
			Actor actor = buscarActor(pApeNom);
			for (int i=0; i<actor.devolverPeliculas().obtenerNumPeliculas();i++) {
				actor.devolverPeliculas().obtenerPeliEnPos(i).borrarActor(actor);}
			mapaActores.remove(pApeNom);
			System.out.println("El actor ha sido eliminado con exito");
		}
		else System.out.println("El actor no se encontraba en la lista");
	}

	public ArrayList<Actor> ListaOrdenadaActores() {
		ArrayList<Actor> lista = new ArrayList<Actor>(mapaActores.values());
		quickSort(lista, 0, lista.size() - 1);
		return lista;
	}

	private void quickSort(ArrayList<Actor> pLista, int pInicio, int pFinal) {
		if (pFinal - pInicio > 0) {
			int indiceParticion = particion(pLista, pInicio, pFinal);
			quickSort(pLista, pInicio, indiceParticion - 1);
			quickSort(pLista, indiceParticion + 1, pFinal);
		}

	}

	private int particion(ArrayList<Actor> pLista, int pInicio, int pFinal) {
		Actor pivote = pLista.get(pInicio);
		int izq = pInicio;
		int der = pFinal;
		while (izq < der) {
			while (pLista.get(izq).compareTo(pivote) <= 0 && izq < der)
				izq++;
			while (pLista.get(der).compareTo(pivote) > 0)
				der--;
			if (izq < der)
				swap(pLista, izq, der);
		}
		pLista.set(pInicio, pLista.get(der));
		pLista.set(der, pivote);
		return der;
	}

	private void swap(ArrayList<Actor> pLista, int pIzq, int pDer) {
		Actor aux = pLista.get(pIzq);
		pLista.set(pIzq, pLista.get(pDer));
		pLista.set(pDer, aux);
	}

	public void imprimirActores(ArrayList<Actor> pLista) {
		Iterator<Actor> itr = pLista.iterator();
		while (itr.hasNext()) {
			itr.next().imprimirse();
		}
	}

	public ArrayList<String> pageRankOrdenado(HashMap<String, Double> pR) {
		ArrayList<Actor> actoresPR = new ArrayList<Actor>();
		ArrayList<String> PRordenado = new ArrayList<String>();
		
		for (String s : pR.keySet()) {
			Actor a = buscarActor(s);
			a.setPR(pR.get(s));
			actoresPR.add(a);
		}
		quickSortPR(actoresPR, 0, actoresPR.size() - 1);
		
		for (Actor a : actoresPR) {
			PRordenado.add(a.getNombre());
		}
		
		return PRordenado;
	}
	
	private void quickSortPR(ArrayList<Actor> pLista, int pInicio, int pFinal) {
		if (pFinal - pInicio > 0) {
			int indiceParticion = particionPR(pLista, pInicio, pFinal);
			quickSortPR(pLista, pInicio, indiceParticion - 1);
			quickSortPR(pLista, indiceParticion + 1, pFinal);
		}

	}

	private int particionPR(ArrayList<Actor> pLista, int pInicio, int pFinal) {
		Actor pivote = pLista.get(pInicio);
		int izq = pInicio;
		int der = pFinal;
		while (izq < der) {
			while (pLista.get(izq).comparePR(pivote) <= 0 && izq < der)
				izq++;
			while (pLista.get(der).comparePR(pivote) > 0)
				der--;
			if (izq < der)
				swap(pLista, izq, der);
		}
		pLista.set(pInicio, pLista.get(der));
		pLista.set(der, pivote);
		return der;
	}

}
