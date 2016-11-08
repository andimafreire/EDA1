package packPrincipal;

import java.util.ArrayList;
import java.util.Scanner;

import packActor.Actor;
import packActor.RegistroActores;
import packPeliculas.Pelicula;
import packPeliculas.RegistroPeliculas;

public class Gestionator {
	private Gestionator elGestionator;
	private static boolean ficheroCargado = false;
	private static boolean grafoCreado = false;
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
		System.out.println(" 10. Crear grafo de actores y pel�culas ");
		System.out.println(" 11. Mirar si dos pel�culas est�n conectadas ");
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println();
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
	    case 10:
	    	if (ficheroCargado && !grafoCreado){
	    		crearGrafo();
	    		System.out.println("Grafo creado");
	    		grafoCreado = true;
		      } else if (!ficheroCargado) {
		    	  System.out.println("El fichero no se ha cargado a�n");
		      } else if (grafoCreado) {
		    	  System.out.println("El grafo ya ha sido creado");
		      }
		      break;
	    case 11:
	    	if (!ficheroCargado){
	    		System.out.println("Debes cargar el fichero primero");}
	    	else if (!grafoCreado) {
	    		System.out.println("Debes crear el grafo primero");}
	    	else if (ficheroCargado && grafoCreado) {
		    	peliculasConectadas();
	    	}
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
	
	private static void cargarLista(){
		RegistroPeliculas.cargarLista(ruta);
	}
	
	private static void exportarLista() {
		RegistroPeliculas.exportarLista(ruta);
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
		ArrayList<Actor> lista = RegistroActores.getRegistroActores().ListaOrdenadaActores();
		RegistroActores.getRegistroActores().imprimirActores(lista);
		System.out.println(sw.elapsedTime());
	}
	
	private static void crearGrafo() {
		StopWatch sw = new StopWatch();
		RegistroPeliculas.getRegistroPeliculas().crearGrafo();   
		
        /*    Hay millones de datos, tarda demasiado en imprimir
            Usar bajo respondasibilidad propia
            Hay que hacer un programa de prueba para probarlo sin imprimir
            
        RegistroPeliculas.getRegistroPeliculas().printGrafo(); */
		System.out.println(sw.elapsedTime());
	}
	
	private static void peliculasConectadas() {
		
		System.out.println("Introduce el nombre de la primera pel�cula");
		Scanner sc1 = new Scanner(System.in);
		String p1 = sc1.nextLine();
		System.out.println("Introduce el nombre de la segunda pel�cula");
		Scanner sc2 = new Scanner(System.in);
		String p2 = sc2.nextLine();
		StopWatch sw = new StopWatch();
		if (RegistroPeliculas.getRegistroPeliculas().estaEnGrafo(p1) &&
			RegistroPeliculas.getRegistroPeliculas().estaEnGrafo(p2)) {
			boolean resultado = RegistroPeliculas.getRegistroPeliculas().estanConectadas(p1, p2);
				if (resultado) {System.out.println("Las pel�culas est�n conectadas");}
				else {System.out.println("Estas dos pel�culas no est�n conectadas");}
		}
		else {System.out.println("Alguna de las pel�culas no est� en el registro");}
		System.out.println(sw.elapsedTime());
	}
}