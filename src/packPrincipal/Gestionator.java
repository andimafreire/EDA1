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
	
	private Gestionator() {}
	
	public static void main(String[] args) {
		System.out.println("seleccione operacion:");
		System.out.println();
		System.out.println(" 1. cargar fichero ");
		System.out.println(" 2. buscar un actor ");
		System.out.println(" 3. insertar un actor ");
		System.out.println(" 4. mostrar peliculas rodadas por un actor ");
		System.out.println(" 5. mostrar actores de una pelicula ");
		System.out.println(" 6. incrementar la recaudacion de una pelicula ");
		System.out.println(" 7. borrar actor ");
		System.out.println(" 8. exportar registro de peliculas a fichero ");
		System.out.println(" 9. obtener lista ordenada de actores ");
		System.out.println();
		System.out.println("------------------------------------------------------------");
		int seleccion;
		Scanner sc = new Scanner(System.in);
		seleccion=sc.nextInt();
		
		
		
	}
	
	public Gestionator getGestionator() {
		if (elGestionator == null) elGestionator = new Gestionator();
		return elGestionator;			
	}
	
	public void cargarLista(String nomF){
		Pelicula peli;
		Actor act;
		String nombre,apellido;
		RegistroActores regAct = RegistroActores.getRegistroActores();
		RegistroPeliculas regPeli = RegistroPeliculas.getRegistroPeliculas();
		String[] sepPeliDeActor, listaActores, nombreCompleto;
		   try{
			  BufferedReader buff = new BufferedReader(new FileReader(nomF));
		      String linea = buff.readLine();
		      Pattern patt1 = Pattern.compile("\\s+--->\\s+");
		      Pattern patt2 = Pattern.compile("\\s+&&&\\s+");
		      Pattern patt3 = Pattern.compile("\\s+,\\s+");
		      while (linea!=null) {
		    	  sepPeliDeActor = patt1.split(linea);
		    	  peli = new Pelicula(sepPeliDeActor[0]);
		    	  regPeli.anadirPelicula(peli);
		    	  listaActores = patt2.split(sepPeliDeActor[1]);
		    	  for (int i = 0; i < listaActores.length; i++) {
		    		  nombreCompleto = patt3.split(listaActores[i]);
		    		  apellido = nombreCompleto[0];
		    		  nombre = nombreCompleto[1];
		    		  act = regAct.buscarActor(apellido+nombre);
		    		  if (act==null) {
		    			  act = new Actor(apellido, nombre);
		    			  regAct.anadirActor(act);
		    		  }
		    		  peli.anadirActor(act);
		    		  act.anadirPelicula(peli);  
		    	  }    
		      }
		      buff.close();
		   }
		   catch (FileNotFoundException e){
			   System.out.println("El fichero no ha sido encontrado");
		   }
		   catch(IOException e) {e.printStackTrace();}
		}
	
	private void exportarLista() {
		RegistroPeliculas regPelis = RegistroPeliculas.getRegistroPeliculas();
		try { 
			BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
		}
	}
	
	private void buscarActor() {
		System.out.println("introduce el nombre del actor (apellido y despues nombre sin espacios");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		Actor a = RegistroActores.getRegistroActores().buscarActor(apeNom);
		if (a!=null) System.out.println("el actor ha sido encontrado");
		else System.out.println("el actor no ha sido encontrado");
	}

	private void insertarActor() {
		System.out.println("introduce el apellido del actor");
		Scanner sc = new Scanner(System.in);
		String ape = sc.nextLine();
		System.out.println("introduce el nombre del actor");
		String nom = sc.nextLine();
		Actor a = new Actor(ape,nom);
		RegistroActores.getRegistroActores().anadirActor(a);
	}
	
	private void borrarActor() {
		System.out.println("introduce el nombre del actor (apellido y despues nombre sin espacios");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		RegistroActores.getRegistroActores().eliminarActor(apeNom);
	}
	
	private void escribirPeliculasActor() {
		System.out.println("introduce el nombre del actor (apellido y despues nombre sin espacios");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		Actor a = RegistroActores.getRegistroActores().buscarActor(apeNom);
		if (a!=null) a.imprimirPeliculas();
		else System.out.println("el actor no ha sido encontrado");
	}
	
	private void imprimirActoresPelicula() {
		System.out.println("introduce el nombre de la pelicula");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		Pelicula peli = RegistroPeliculas.getRegistroPeliculas().buscarPelicula(nombre);
		if (peli!=null) peli.imprimirActores();	
		else System.out.println("la pelicula no ha sido encontrada");
	}
	
	private void incrementarRecaudacion() {
		System.out.println("introduce el nombre de la pelicula");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		Pelicula peli = RegistroPeliculas.getRegistroPeliculas().buscarPelicula(nombre);	
		if (peli!=null) {
			System.out.println("introduce la recaudacion a incrementar");
			Double recau = sc.nextDouble();
			peli.incrementarRecaudacion(recau);
		}
		else System.out.println("la pelicula no ha sido encontrada");
	}
	
	private void obtenerListaOrdenadaActores() {
		RegistroActores.getRegistroActores().ListaOrdenadaActores();
	}
}
