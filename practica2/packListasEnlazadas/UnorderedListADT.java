package packListasEnlazadas;

public interface UnorderedListADT<T> extends ListADT<T> {
	
	public void addToFront(T elem);
	// Añade un elemento al comienzo 

	public void addToRear(T elem);
	// Añade un elemento al final 
	
	public void addAfter(T elem, T target);
	// Añade elem detrás de otro elemento concreto, target, que ya se encuentra en la lista

}
