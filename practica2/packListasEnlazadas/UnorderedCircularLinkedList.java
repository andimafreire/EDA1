package packListasEnlazadas;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {
	
	public void addToFront(T elem) {
	// Añade un elemento al comienzo
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
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
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
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
}
