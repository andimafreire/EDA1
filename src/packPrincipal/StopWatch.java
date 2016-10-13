package packPrincipal;

public class StopWatch { 

    private final long start;

   /** Create a stopwatch object. */
    public StopWatch() {
        start = System.currentTimeMillis();
    } 

   /**
     * Return elapsed time (in seconds) since this object was created.
     */
    public long elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start);
    }
    public void imprimirTiempo(){
    	System.out.println(start);
    }
} 
