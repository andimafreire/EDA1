package packPeliculas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Pattern;

import packActor.Actor;
import packActor.RegistroActores;
import packGrafos.GraphHash;
import packPrincipal.StopWatch;

public class RegistroPeliculas {
	private ListaPeliculas pelis;
	private static RegistroPeliculas elRegistroPeliculas;
	private GraphHash grafo ;
	
	private RegistroPeliculas() {
		this.pelis = new ListaPeliculas();
	}
	
	public static RegistroPeliculas getRegistroPeliculas() {
		if (elRegistroPeliculas == null) elRegistroPeliculas = new RegistroPeliculas();
		return elRegistroPeliculas;
	}
	
	public Pelicula buscarPelicula(String pNombre) {
		return pelis.buscarPelicula(pNombre);		
	}
	
	private void anadirPelicula (Pelicula pPeli) {
		pelis.anadirPelicula(pPeli);
	}
	public ListaPeliculas getPelis() {
		return pelis;
	}
	public static void cargarLista(String ruta){
		StopWatch sw = new StopWatch();
		Pelicula peli;
		Actor act;
		RegistroActores regAct = RegistroActores.getRegistroActores();
		String[] sepPeliDeActor, listaActores;
		   try{
			  BufferedReader buff = new BufferedReader(new FileReader(ruta));
		      String linea = buff.readLine();
		      Pattern patt1 = Pattern.compile("\\s+--->\\s+");
		      Pattern patt2 = Pattern.compile("\\s+&&&\\s+");
		      while (linea!=null) {
		    	  sepPeliDeActor = patt1.split(linea);
		    	  peli = new Pelicula(sepPeliDeActor[0]);
		    	  RegistroPeliculas.getRegistroPeliculas().anadirPelicula(peli);
		    	  listaActores = patt2.split(sepPeliDeActor[1]);
		    	  for (int i = 0; i < listaActores.length; i++) {
		    		  act = regAct.buscarActor(listaActores[i]);
		    		  if (act==null) {
		    			  act = new Actor(listaActores[i]);
		    			  regAct.anadirActor(act);
		    		  }
		    		  peli.anadirActor(act);
		    		  act.anadirPelicula(peli);  
		    	  }
		    	  linea = buff.readLine();
		      }
		      buff.close();
		   }
		   catch (FileNotFoundException e){
			   System.out.println("El fichero no ha sido encontrado");
		   }
		   catch(IOException e) {e.printStackTrace();}
		System.out.println(sw.elapsedTime());
	}
	public static void exportarLista(String ruta) {
		StopWatch sw = new StopWatch();
		RegistroPeliculas regPelis = RegistroPeliculas.getRegistroPeliculas();
		try { 
			BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
			Pelicula peli;
			for (int i = 0; i < regPelis.getPelis().obtenerNumPeliculas(); i++) { 
				peli = regPelis.getPelis().obtenerPeliEnPos(i);
				bw.write(peli.getNombre()+" ---> ");
				Iterator<Actor> itr = peli.getActores().iterator();
				while (itr.hasNext()) {
					bw.write(itr.next().getNombre());
					if(itr.hasNext()){
						bw.write(" &&& ");
					}
				}
				bw.flush();
				bw.newLine();
			}
			bw.close(); 
			} 
		catch (IOException e) { e.printStackTrace();}
		System.out.println(sw.elapsedTime());
	}
	
	public void crearGrafo() {
		this.grafo = new GraphHash();
		grafo.crearGrafo(pelis);
	}
	
	public void printGrafo() {
		grafo.print();
	}
	
	public boolean estanConectadas(String p1, String p2) {
		return grafo.estanConectadas(p1, p2);
	}
	
	public boolean estaEnGrafo(String p1) {
		return grafo.contains(p1);
	}
	
}
