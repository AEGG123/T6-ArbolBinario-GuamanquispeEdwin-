package negocio;

import java.util.function.Predicate;
import modelo.Nodo;

/**
 * Arbol binario generico implementado con nodos enlazados.
 * Las operaciones de conteo y altura se resuelven con recursividad.
 * No se usan TreeSet, TreeMap, PriorityQueue, NavigableSet ni NavigableMap.
 *
 * @param <T> tipo de dato almacenado en los nodos
 */
public class ArbolBinario<T> {

    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    // ------------------------------------------------------------------
    // Construccion
    // ------------------------------------------------------------------

    /**
     * Crea el nodo raiz.
     *
     * @return el nodo raiz creado
     * @throws IllegalStateException si el arbol ya tiene raiz
     */
    public Nodo<T> crearRaiz(T dato) {
        if (raiz != null) {
            throw new IllegalStateException("El arbol ya tiene una raiz.");
        }
        raiz = new Nodo<>(dato);
        return raiz;
    }

    /**
     * Agrega un hijo izquierdo al nodo padre.
     *
     * @return el nodo creado, o null si la posicion izquierda ya esta ocupada
     */
    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getIzquierdo() != null) {
            return null; // posicion ocupada
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setIzquierdo(nuevo);
        return nuevo;
    }

    /**
     * Agrega un hijo derecho al nodo padre.
     *
     * @return el nodo creado, o null si la posicion derecha ya esta ocupada
     */
    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getDerecho() != null) {
            return null; // posicion ocupada
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setDerecho(nuevo);
        return nuevo;
    }

    private void validarPadre(Nodo<T> padre) {
        if (padre == null) {
            throw new IllegalArgumentException("El nodo padre no puede ser null.");
        }
    }

    // ------------------------------------------------------------------
    // Consultas (recursivas)
    // ------------------------------------------------------------------

    /** Indica si el arbol no tiene nodos. */
    public boolean esVacio() {
        return raiz == null;
    }

    /** Numero total de nodos. */
    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) {
            return 0; // caso base
        }
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    /** Numero de hojas (nodos sin hijos). */
    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) {
            return 0; // caso base: no hay nodo
        }
        if (nodo.esHoja()) {
            return 1; // caso base: hoja
        }
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    /**
     * Altura del arbol medida en aristas.
     * Arbol vacio = -1, arbol con un solo nodo = 0.
     */
    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) {
            return -1; // caso base
        }
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    /**
     * Busca (recorrido en preorden) el primer nodo que cumpla el criterio.
     *
     * @return el nodo encontrado o null si no existe
     */
    public Nodo<T> buscar(Predicate<T> criterio) {
        return buscar(raiz, criterio);
    }

    private Nodo<T> buscar(Nodo<T> nodo, Predicate<T> criterio) {
        if (nodo == null) {
            return null;
        }
        if (criterio.test(nodo.getDato())) {
            return nodo;
        }
        Nodo<T> enIzquierdo = buscar(nodo.getIzquierdo(), criterio);
        if (enIzquierdo != null) {
            return enIzquierdo;
        }
        return buscar(nodo.getDerecho(), criterio);
    }

    // ------------------------------------------------------------------
    // Visualizacion
    // ------------------------------------------------------------------

    /** Muestra la estructura jerarquica del arbol en consola. */
    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("(arbol vacio)");
            return;
        }
        System.out.println(raiz.getDato() + " (raiz)");
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo<T> nodo, String prefijo) {
        Nodo<T> izq = nodo.getIzquierdo();
        Nodo<T> der = nodo.getDerecho();

        if (izq != null) {
            boolean esUltimo = (der == null);
            System.out.println(prefijo + (esUltimo ? "`-- " : "|-- ") + "[I] " + izq.getDato());
            mostrarHijos(izq, prefijo + (esUltimo ? "    " : "|   "));
        }
        if (der != null) {
            System.out.println(prefijo + "`-- [D] " + der.getDato());
            mostrarHijos(der, prefijo + "    ");
        }
    }
}
