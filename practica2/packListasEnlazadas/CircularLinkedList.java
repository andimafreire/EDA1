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
		// Precondición: la lista tiene al menos un elemento
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		Node<T> aux = last.next;
		if (last != last.next) {
			last.next = aux.next;
		} else {
			last = null;
		}
		return (aux.data);

	}

	public T removeLast() {
		// Elimina el último elemento de la lista
		// Precondición: la lista tiene al menos un elemento
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
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
		return (aux.data);

	}

	public T remove(T elem) {
		// Elimina un elemento concreto de la lista
		// coste: O(n)
		Node<T> act = this.last;
		while (act.next!=this.last){
			if (act.next.data.equals(elem)){
				act.next = act.next.next;
				return elem;
			}
		}
		if (act.next.data.equals(elem)){
			act.next = act.next.next;
			return elem;
		}
		return null;
		
	}

	public T first() {
		// Da acceso al primer elemento de la lista
		if (isEmpty())
			return null;
		else
			return last.next.data;
	}

	public T last() {
		// Da acceso al �ltimo elemento de la lista
		if (isEmpty())
			return null;
		else
			return last.data;
	}

	public boolean contains(T elem) {
		Node<T> act = this.last;
		while (act.next!=this.last){
			if (act.next.data.equals(elem)){
				return true;
			}
		}
		if (act.next.data.equals(elem)){
			return true;
		}
		return false;
	}

	public T find(T elem) {
		// Determina si la lista contiene un elemento concreto, y develve su
		// referencia, null en caso de que no est�
		// COMPLETAR EL C�DIGO Y CALCULAR EL COSTE
		Node<T> act = this.last;
		while (act.next!=this.last){
			if (act.next.data.equals(elem)){
				return act.next.data;
			}
		}
		if (act.next.data.equals(elem)){
			return act.next.data;
		}
		return null;
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

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		

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
