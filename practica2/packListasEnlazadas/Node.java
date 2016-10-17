package packListasEnlazadas;

	public class Node<T> {
		public T data; 			// Dato del nodo
		public Node<T> next; 	// Puntero al siguiente nodo de la lista

		public Node(T pData) 	// Constructor
		{
			data = pData;
			next = null;

		}
	}


