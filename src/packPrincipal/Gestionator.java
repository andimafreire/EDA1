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
	
	public void main(String[] args) {
		menu();		
	}
	
	private void menu() {
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
	      cargarLista();
	      menu();
	      break;
	    case 2:
	      buscarActor();  
	      menu();
	      break;
	    case 3:
	      insertarActor();
	      menu();
	      break;
	    case 4:
	      escribirPeliculasActor();
	      menu();
	      break;
	    case 5:
	      imprimirActoresPelicula();
	      menu();
	      break;
	    case 6:
	      incrementarRecaudacion();
	      menu();
	      break;
	    case 7:
	      borrarActor();
	      menu();
	      break;
	    case 8:
	      exportarLista();
	      menu();
	      break;
	    case 9:
	      obtenerListaOrdenadaActores();
	      menu();
	      break;
	    default:
	      System.out.println("El número introducido no está en el rango");
	      menu();
	      break;
	    }
		sc.close();
	}
	public Gestionator getGestionator() {
		if (elGestionator == null) elGestionator = new Gestionator();
		return elGestionator;			
	}
	
	public void cargarLista(){
		StopWatch sw = new StopWatch();
		Pelicula peli;
		Actor act;
		String nombre,apellido;
		RegistroActores regAct = RegistroActores.getRegistroActores();
		RegistroPeliculas regPeli = RegistroPeliculas.getRegistroPeliculas();
		String[] sepPeliDeActor, listaActores, nombreCompleto;
		   try{
			  BufferedReader buff = new BufferedReader(new FileReader("/EDA1/documentos/archivos"));
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
		sw.elapsedTime();
		}
	
	private void exportarLista() {
		RegistroPeliculas regPelis = RegistroPeliculas.getRegistroPeliculas();
		try { 
			BufferedWriter bw = new BufferedWriter(new FileWriter("/EDA1/documentos/archivos/fichero_resultado"));
			Pelicula peli;
			for (int i = 0; i < regPelis.getPelis().obtenerNumPeliculas(); i++) { 
				peli = regPelis.getPelis().obtenerPeliEnPos(i);
				bw.write(peli.getNombre()+" ---> ");
				for (int j = 0; j < peli.getActores().size()-1; j++) {
					bw.write(peli.getActores().get(j).getApellido()+", "+peli.getActores().get(j).getNombre());
					bw.write(" &&& ");
				}
				bw.write(peli.getActores().get(peli.getActores().size()-1).getApellido()+", "+
						peli.getActores().get(peli.getActores().size()-1).getNombre());
				bw.flush();
				bw.newLine();
			}
			bw.close(); 
			} 
		catch (IOException e) { e.printStackTrace();}
	}
	
	private void buscarActor() {
		StopWatch sw = new StopWatch();
		System.out.println("introduce el nombre del actor (apellido y despues nombre sin espacios");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		Actor a = RegistroActores.getRegistroActores().buscarActor(apeNom);
		if (a!=null) System.out.println("El actor ha sido encontrado");
		else System.out.println("El actor no ha sido encontrado");
		sc.close();
		sw.elapsedTime();
	}

	private void insertarActor() {
		StopWatch sw = new StopWatch();
		System.out.println("Introduce el apellido del actor");
		Scanner sc = new Scanner(System.in);
		String ape = sc.nextLine();
		System.out.println("Introduce el nombre del actor");
		String nom = sc.nextLine();
		Actor a = new Actor(ape,nom);
		RegistroActores.getRegistroActores().anadirActor(a);
		sc.close();
		sw.elapsedTime();
	}
	
	private void borrarActor() {
		StopWatch sw = new StopWatch();
		System.out.println("Introduce el nombre del actor (apellido y después nombre sin espacios");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		RegistroActores.getRegistroActores().eliminarActor(apeNom);
		sc.close();
		sw.elapsedTime();
	}
	
	private void escribirPeliculasActor() {
		StopWatch sw = new StopWatch();
		System.out.println("introduce el nombre del actor (apellido y después nombre sin espacios");
		Scanner sc = new Scanner(System.in);
		String apeNom = sc.nextLine();
		Actor a = RegistroActores.getRegistroActores().buscarActor(apeNom);
		if (a!=null) a.imprimirPeliculas();
		else System.out.println("El actor no ha sido encontrado");
		sc.close();
		sw.elapsedTime();
	}
	
	private void imprimirActoresPelicula() {
		StopWatch sw = new StopWatch();
		System.out.println("introduce el nombre de la pelicula");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		Pelicula peli = RegistroPeliculas.getRegistroPeliculas().buscarPelicula(nombre);
		if (peli!=null) peli.imprimirActores();	
		else System.out.println("la pelicula no ha sido encontrada");
		sc.close();
		sw.elapsedTime();
	}
	
	private void incrementarRecaudacion() {
		StopWatch sw = new StopWatch();
		System.out.println("Introduce el nombre de la pelicula");
		Scanner sc = new Scanner(System.in);
		String nombre = sc.nextLine();
		Pelicula peli = RegistroPeliculas.getRegistroPeliculas().buscarPelicula(nombre);	
		if (peli!=null) {
			System.out.println("Introduce la recaudacion a incrementar");
			Double recau = sc.nextDouble();
			peli.incrementarRecaudacion(recau);
		}
		else System.out.println("La pelicula no ha sido encontrada");
		sc.close();
		sw.elapsedTime();
	}
	
	private void obtenerListaOrdenadaActores() {
		StopWatch sw = new StopWatch();
		RegistroActores.getRegistroActores().ListaOrdenadaActores();
		sw.elapsedTime();
	}
}