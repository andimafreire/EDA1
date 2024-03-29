package packListasEnlazadas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements ListADT<T> {

	// Atributos
	protected Node<T> last; // Apuntador al �ltimo
	protected String descr; // Descripci�n
	protected int count;

	// Constructor
	public CircularLinkedList() {
		last = null;
		descr = "";
		count = 0;
	}

	public void setDescr(String nom) {
		descr = nom;
	}

	public String getDescr() {
		return descr;
	}

	public T removeFirst() {
		// Elimina el primer elemento de la lista
		// Precondicion: la lista tiene al menos un elemento
		// COSTE: Constante O(1)
		Node<T> aux = last.next;
		if (last != last.next) {
			last.next = aux.next;
		} else {
			last = null;
		}
		count--;
		return (aux.data);

	}

	public T removeLast() {
		// Elimina el último elemento de la lista
		// Precondición: la lista tiene al menos un elemento
		// COSTE: Lineal O(n)
		Node<T> aux = last;
		if (last.next != last) {
			Node<T> act = last.next;

			while (last != act.next) {
				act = act.next;
			}
			act.next = last.next;
			this.last = act;
		} else {
			this.last = null;
		}
		count--;
		return (aux.data);

	}

	public T remove(T elem) {
		// Elimina un elemento concreto de la lista
		// COSTE: Lineal O(n)
		Node<T> act = this.last;
		while (act.next!=this.last){
			if (act.next.data.equals(elem)){
				act.next = act.next.next;
				count--;
				return elem;
			}
			else act = act.next;
		}
		if (act.next.data.equals(elem)){
			act.next = act.next.next;
			count--;
			last = act;
			return elem;
		}
		return null;
		
	}

	public T first() {
		// Da acceso al primer elemento de la lista
		if (isEmpty())return null;
		else return last.next.data;
	}

	public T last() {
		// Da acceso al �ltimo elemento de la lista
		if (isEmpty())return null;
		else return last.data;
	}

	public boolean contains(T elem) {
		if (last==null)return false;
		Node<T> act = this.last;
		while (act.next!=this.last){
			if (act.next.data.equals(elem)){
				return true;
			}
			else act = act.next;
		}
		if (act.next.data.equals(elem)){
			return true;
		}
		return false;
	}

	public T find(T elem) {
		// Determina si la lista contiene un elemento concreto, y develve su
		// referencia, null en caso de que no est�
		// COSTE: Lineal O(n)
		if (last==null) {return null;}
		else {
		Node<T> act = this.last;
		while (act.next!=this.last){
			if (act.next.data.equals(elem)){
				return act.next.data;
			}
			else act=act.next;
		}
		if (act.next.data.equals(elem)){
			return act.next.data;
		}
		return null;}
	}

	public boolean isEmpty() { // Determina si la lista est� vac�a
		return last == null;
	}

	public int size() { // Determina el n�mero de elementos de la lista
		return count;
	}

	public Iterator<T> iterator() {
		return new ListIterator();
	}

	// An iterator doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		private Node<T> current = last;
		private int cont = count;
		@Override
		public boolean hasNext() {
			if (cont<=0)return false;
			else return true;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			T item = current.next.data;
			current = current.next;
			cont--;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();	
		}
		

	} // private class

	public void visualizarNodos() {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		String result = new String();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T elem = it.next();
			result = result + "[" + elem.toString() + "] \n";
		}
		return "SimpleLinkedList " + result + "]";
	}

}
