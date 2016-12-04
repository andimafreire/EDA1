package packListasEnlazadas;

public class OrderedCircularLinkedList<T> extends CircularLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){
		// lista ordenada de menor a mayor. 
		// coste: debido a que se recorre la lista hasta una media de n/2 podemos decir que tiene coste lineal O(n).
		Node<T> nuevo = new Node<T>(elem);
		if(last==null){
			last = nuevo;
			nuevo.next=last;
			count++;
		}
		else{
			boolean anadido = false;
			Node<T> act = this.last.next;
			Node<T> ant = this.last;
			while (act!=this.last && !anadido){
				if (act.data.toString().compareTo(elem.toString())>0){ //si act es mayor que elem introduce elem delante.
					ant.next = nuevo;
					nuevo.next = act;
					count++;
					anadido = true;
				}
				else {
					ant = act;
					act = act.next;
				}
			}
			if (!anadido){
				if(act.next.data.toString().compareTo(elem.toString())>0){ //si act es mayor que elem introduce elem delante.
					ant.next = nuevo;
					nuevo.next = act;
					count++;
				}
				else{
					nuevo.next = last.next;
					last.next = nuevo;
					last = nuevo;
					count++;
				}
			}		
		}	
	}
}
