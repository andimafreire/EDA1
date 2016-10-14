package packPrincipal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import packActor.Actor;
import packActor.RegistroActores;
import packPeliculas.Pelicula;
import packPeliculas.RegistroPeliculas;

public class Gestionator {
	private Gestionator elGestionator;
	private static boolean ficheroCargado = false;
	private static String ruta;
	
	private Gestionator() {}
	
	public static void main(String[] args) {
		menu();
	}
	
	public static void obtenerRuta() {
		System.out.println("Inserte la ruta de su archivo (Recuerde que windows pone los slashes al rev�s)");
		System.out.println("Por ejempo: C:/workspaceEclipse/EDA1/documentos/archivos/FilmsActors20162017Small.txt");
		System.out.println("Si introduce mal la ruta el programa no funcionar� y tendr� que reiniciar la aplicaci�n");
		Scanner sc = new Scanner(System.in);
		ruta = sc.nextLine();
	}	
	
	private static void menu() {
		System.out.println("Seleccione operacion:");
		System.out.println();
		System.out.println(" 1. Cargar fichero ");
		System.out.println(" 2. Buscar un actor ");
		System.out.println(" 3. Insertar un actor ");
		System.out.println(" 4. Mostrar peliculas rodadas por un actor ");
		System.out.println(" 5. Mostrar actores de una pelicula ");
		System.out.println(" 6. Incrementar la recaudacion de una pelicula ");
		System.out.println(" 7. Borrar actor ");
		System.out.println(" 8. Exportar registro de peliculas a fichero ");
		System.out.println(" 9. Obtener lista ordenada de actores ");
		System.out.println();
		System.out.println("------------------------------------------------------------");
		int seleccion;
		Scanner sc = new Scanner(System.in);
		seleccion=sc.nextInt();
		
		switch (seleccion) {
	    
	    case 1:
	      if (!ficheroCargado){
		       obtenerRuta();
	    	   cargarLista();
	    	   System.out.println("Fichero cargado");
	    	   ficheroCargado = true;
	      } else System.out.println("El fichero ya hab�a sido cargado");
	      break;
	    case 2:
	      buscarActor();  
	      break;
	    case 3:
	      insertarActor();
	      break;
	    case 4:
	      escribirPeliculasActor();
	      break;
	    case 5:
	      imprimirActoresPelicula();
	      break;
	    case 6:
	      incrementarRecaudacion();
	      break;
	    case 7:
	      borrarActor();
	      break;
	    case 8:
	      obtenerRuta();
	      exportarLista();
	      break;
	    case 9:
	      obtenerListaOrdenadaActores();
	      break;
	    default:
	      System.out.println("El n�mero introducido no est� en el rango");
	      break;
	    }
		menu();
	}
	public Gestionator getGestionator() {
		if (elGestionator == null) elGestionator = new Gestionator();
		return elGestionator;			
	}
	
	public static void cargarLista(){
		StopWatch sw = new StopWatch();
		Pelicula peli;
		Actor act;
		RegistroActores regAct = RegistroActores.getRegistroActores();
		RegistroPeliculas regPeli = RegistroPeliculas.getRegistroPeliculas();
		String[] sepPeliDeActor, listaActores;
		   try{
			  BufferedReader buff = new BufferedReader(new FileReader(ruta));
		      String linea = buff.readLine();
		      Pattern patt1 = Pattern.compile("\\s+--->\\s+");
		      Pattern patt2 = Pattern.compile("\\s+&&&\\s+");
		      while (linea!=null) {
		    	  sepPeliDeActor = patt1.split(linea);
		    	  peli = new Pelicula(sepPeliDeActor[0]);
		    	  regPeli.anadirPelicula(peli);
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
	
	private static void exportarLista() {
		StopWatch sw = new StopWatch();
		RegistroPeliculas regPelis = RegistroPeliculas.getRegistroPeliculas();
		try { 
			BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
			Pelicula peli;
			for (int i = 0; i < regPelis.getPelis().obtenerNumPeliculas(); i++) { 
				peli = regPelis.getPelis().obtenerPeliEnPos(i);
				bw.write(peli.getNombre()+" ---> ");
				for (int j = 0; j < peli.getActores().size()-1; j++) {
					bw.write(peli.getActores().get(j).getNombre());
					bw.write(" &&& ");
				}
				bw.write(peli.getActores().get(peli.getActores().size()-1).getNombre());
				bw.flush();
				bw.newLine();
			}
			bw.close(); 
			} 
		catch (IOException e) { e.printStackTrace();}
		System.out.println(sw.elapsedTime());
	}
	
	private static void buscarActor() {
		System.out.println("Introduce el nombre completo del actor");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		StopWatch sw = new StopWatch();
		Actor a = RegistroActores.getRegistroActores().buscarActor(apeNom);
		if (a!=null) System.out.println("El actor ha sido encontrado");
		else System.out.println("El actor no ha sido encontrado");
		System.out.println(sw.elapsedTime());
	}

	private static void insertarActor() {
		System.out.println("Introduce el nombre completo del actor");
		Scanner sc = new Scanner(System.in);
		String nom = sc.nextLine();
		StopWatch sw = new StopWatch();
		Actor a = new Actor(nom);
		RegistroActores.getRegistroActores().anadirActor(a);
		System.out.println(sw.elapsedTime());
	}
	
	private static void borrarActor() {
		System.out.println("Introduce el nombre completo del actor");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		StopWatch sw = new StopWatch();
		RegistroActores.getRegistroActores().eliminarActor(apeNom);
		System.out.println(sw.elapsedTime());
	}
	
	private static void escribirPeliculasActor() {
		System.out.println("Introduce el nombre completo del actor");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		StopWatch sw = new StopWatch();
		Actor a = RegistroActores.getRegistroActores().buscarActor(apeNom);
		if (a!=null) a.imprimirPeliculas();
		else System.out.println("El actor no ha sido encontrado");
		System.out.println(sw.elapsedTime());
	}
	
	private static void imprimirActoresPelicula() {
		System.out.println("Introduce el nombre de la pel�cula");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		StopWatch sw = new StopWatch();
		Pelicula peli = RegistroPeliculas.getRegistroPeliculas().buscarPelicula(nombre);
		if (peli!=null) peli.imprimirseCompleto();	
		else System.out.println("La pelicula no ha sido encontrada");
		System.out.println(sw.elapsedTime());
	}
	
	private static void incrementarRecaudacion() {
		System.out.println("Introduce el nombre de la pel�cula");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		StopWatch sw = new StopWatch();
		Pelicula peli = RegistroPeliculas.getRegistroPeliculas().buscarPelicula(nombre);	
		System.out.println(sw.elapsedTime());
		if (peli!=null) {
			System.out.println("Introduce la recaudaci�n a incrementar");
			int recau = sc.nextInt();
			peli.incrementarRecaudacion(recau);
		}
		else System.out.println("La pel�cula no ha sido encontrada");
		
	}
	
	private static void obtenerListaOrdenadaActores() {
		StopWatch sw = new StopWatch();
		RegistroActores.getRegistroActores().ListaOrdenadaActores();
		System.out.println(sw.elapsedTime());
	}
}