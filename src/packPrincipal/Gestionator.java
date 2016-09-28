package packPrincipal;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Gestionator {
	private Gestionator elGestionator;
	
	private Gestionator() {}
	
	public static void main(String[] args) {}
	
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
