package modelo;

/**
 * Nodo generico de un arbol binario.
 * Cada nodo almacena un dato y tiene como maximo dos hijos:
 * uno izquierdo y uno derecho.
 *
 * @param <T> tipo de dato almacenado en el nodo
 */
public class Nodo<T> {

    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    /** Crea un nodo sin hijos (hoja) con el dato indicado. */
    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    /** Un nodo es hoja cuando no tiene ningun hijo. */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /** Grado del nodo: numero de hijos (0, 1 o 2). */
    public int grado() {
        int grado = 0;
        if (izquierdo != null) {
            grado++;
        }
        if (derecho != null) {
            grado++;
        }
        return grado;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return String.valueOf(dato);
    }
}
