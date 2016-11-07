package packListasEnlazadas;

import java.util.ArrayList;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
	// Añade un elemento al comienzo
		// COSTE: Constante O(1)
		Node<T> nuevo = new Node<T>(elem);
		if(last==null) {
			last = nuevo;
			nuevo.next = nuevo;
		}
		else {
			nuevo.next = this.last.next;
			this.last.next = nuevo;
		}
		count++;
	}

	public void addToRear(T elem) {
	// Añade un elemento al final 
		// COSTE: Constante O(1)
		Node<T> nuevo = new Node<T>(elem);
		if (this.last == null) {
			this.last = nuevo;
			nuevo.next = nuevo;
		}
		else {
			nuevo.next = this.last.next;
			this.last.next = nuevo;
			this.last=nuevo;	
		}
		count++;
	}
	
	public void addAfter(T elem, T target) {
	// Añade elem detrás de otro elemento concreto, target, que ya se encuentra en la lista
		// ¡COMPLETAR OPCIONAL!
		// COSTE: Lineal O(n)
		Node<T> nuevo = new Node<T>(elem);
		Node<T> act = this.last;
		boolean insertado = false;
		while (!insertado){
			if (act.data.equals(target)){
				nuevo.next = act.next;
				act.next = nuevo;
				insertado = true;
			}
			else act = act.next;
		}
		count++;
	}
	
	public ArrayList<String> toArrayString() {
		//Pre: La lista no es vacia porque la hemos cargado nosotros
			// Solo funciona con actores
		ArrayList<String> lista = new ArrayList<String>();
		Node<T> act = this.last.next;
		while (act!=this.last) {
			lista.add(act.data.toString());
			act=act.next;
		}
		lista.add(last.data.toString());
		return lista;
	}
	
	public ArrayList<T> arrayActores() {
		ArrayList<T> lista = new ArrayList<T>();
		Node<T> act = this.last.next;
		while (act!=last) {
			lista.add(act.data);
			act=act.next;
		}
		lista.add(last.data);
		return lista;
	}
}
