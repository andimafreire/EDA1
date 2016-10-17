/** * IndexedListADT defines the interface to an indexed list collection. Elements * are referenced by contiguous numeric indexes.
 * @author Dr. Lewis
 * @author Dr. Chase
 * @version 1.0, 08/13/08
 */
package packListasEnlazadas;public interface IndexedListADT<T> extends ListADT<T>{	   public void add (int index, T element);   	// Inserta el elemento en el índice      public void set (int index, T element);   	// Inserta el elemento en el índice   public void add (T element);   	// Añade el elemento al final de la lista   public T get (int index);   	// Deuelve el elemento en el índice   public int indexOf (T element);   	// Devuelve el índice del elemento      public T remove (int index);   	// Devuelve y elimina el elemento en el índice}
