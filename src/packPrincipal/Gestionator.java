package packPrincipal;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
		   try{
		      Scanner entrada = new Scanner(new FileReader(nomF));
		      String linea;
		      while (entrada.hasNext()) {
		         linea = entrada.nextLine();
		         //TODO Lo que se implemente
		      }
		      entrada.close();
		   }
		   catch(IOException e) {e.printStackTrace();}
		}
	
	private void exportarLista() {
		
	}
	
	private void buscarActor() {
		
	}

	private void insertarActor() {
		
	}
	
	private void borrarActor() {
		
	}
	
	private void escribirPeliculasActor() {
		
	}
	
	private void imprimirActoresPelicula() {
		
	}
	
	private void incrementarRecaudacion() {
		
	}
	
	private void obtenerListaOrdenadaActores() {
		
	}
}
