package app;

import java.text.Normalizer;
import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

/**
 * Programa principal: organizacion jerarquica de departamentos universitarios.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Seleccione una opcion: ");
            System.out.println();

            switch (opcion) {
                case 1:
                    arbol = construirArbolInicial();
                    System.out.println("Arbol inicial creado correctamente.");
                    break;
                case 2:
                    System.out.println("ESTRUCTURA DEL ARBOL:");
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Numero de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Numero de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura del arbol (en aristas): " + arbol.altura());
                    break;
                case 6:
                    consultarGrado(sc, arbol);
                    break;
                case 7:
                    consultarHoja(sc, arbol);
                    break;
                case 8:
                    ejecutarCasosDePrueba();
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ------------------------------------------------------------------
    // Menu y operaciones interactivas
    // ------------------------------------------------------------------

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("   ARBOL BINARIO - DEPARTAMENTOS UNIVERSIDAD");
        System.out.println("=============================================");
        System.out.println("1. Crear el arbol inicial");
        System.out.println("2. Visualizar el arbol");
        System.out.println("3. Consultar el numero de nodos");
        System.out.println("4. Consultar el numero de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Ejecutar casos de prueba obligatorios");
        System.out.println("0. Salir");
    }

    private static void consultarGrado(Scanner sc, ArbolBinario<String> arbol) {
        Nodo<String> nodo = pedirNodo(sc, arbol);
        if (nodo != null) {
            System.out.println("El grado de " + nodo.getDato() + " es: " + nodo.grado());
        }
    }

    private static void consultarHoja(Scanner sc, ArbolBinario<String> arbol) {
        Nodo<String> nodo = pedirNodo(sc, arbol);
        if (nodo != null) {
            if (nodo.esHoja()) {
                System.out.println(nodo.getDato() + " ES una hoja.");
            } else {
                System.out.println(nodo.getDato() + " NO es una hoja (grado " + nodo.grado() + ").");
            }
        }
    }

    /** Pide el nombre de un nodo y lo busca en el arbol (sin distinguir mayusculas ni tildes). */
    private static Nodo<String> pedirNodo(Scanner sc, ArbolBinario<String> arbol) {
        if (arbol.esVacio()) {
            System.out.println("El arbol esta vacio. Cree primero el arbol (opcion 1).");
            return null;
        }
        System.out.print("Nombre del nodo: ");
        String entrada = normalizar(sc.nextLine());
        Nodo<String> nodo = arbol.buscar(dato -> normalizar(dato).equals(entrada));
        if (nodo == null) {
            System.out.println("No existe un nodo llamado \"" + entrada + "\".");
        }
        return nodo;
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // se tratara como opcion invalida
        }
    }

    private static String normalizar(String texto) {
        String sinTildes = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return sinTildes.trim().toUpperCase();
    }

    // ------------------------------------------------------------------
    // Arbol inicial
    // ------------------------------------------------------------------

    /**
     * Construye el arbol del enunciado (9 nodos, 5 hojas, altura 3):
     *
     *              UNIVERSIDAD
     *              /         \
     *        FISEI             DIDE
     *        /   \             /   \
     *  SOFTWARE   TI     INVEST.  VINCULACION
     *   /   \
     * NIVEL1 NIVEL3
     */
    private static ArbolBinario<String> construirArbolInicial() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        Nodo<String> universidad = arbol.crearRaiz("UNIVERSIDAD");

        Nodo<String> fisei = arbol.agregarIzquierdo(universidad, "FISEI");
        Nodo<String> dide = arbol.agregarDerecho(universidad, "DIDE");

        Nodo<String> software = arbol.agregarIzquierdo(fisei, "SOFTWARE");
        arbol.agregarDerecho(fisei, "TI");

        arbol.agregarIzquierdo(dide, "INVEST.");
        arbol.agregarDerecho(dide, "VINCULACION");

        arbol.agregarIzquierdo(software, "NIVEL1");
        arbol.agregarDerecho(software, "NIVEL3");
        return arbol;
    }

    // ------------------------------------------------------------------
    // Casos de prueba obligatorios
    // ------------------------------------------------------------------

    private static void ejecutarCasosDePrueba() {
        System.out.println("########## CASOS DE PRUEBA OBLIGATORIOS ##########");

        // Caso 1: arbol vacio
        System.out.println("\nCASO 1: Arbol vacio");
        ArbolBinario<String> vacio = new ArbolBinario<>();
        verificar("Nodos", 0, vacio.contarNodos());
        verificar("Altura", -1, vacio.altura());

        // Caso 2: arbol con un solo nodo
        System.out.println("\nCASO 2: Arbol con un solo nodo");
        ArbolBinario<String> unico = new ArbolBinario<>();
        unico.crearRaiz("UNIVERSIDAD");
        verificar("Nodos", 1, unico.contarNodos());
        verificar("Hojas", 1, unico.contarHojas());
        verificar("Altura", 0, unico.altura());

        // Caso 3: arbol inicial
        System.out.println("\nCASO 3: Arbol inicial");
        ArbolBinario<String> inicial = construirArbolInicial();
        inicial.mostrarArbol();
        verificar("Nodos", 9, inicial.contarNodos());
        verificar("Hojas", 5, inicial.contarHojas());
        verificar("Altura", 3, inicial.altura());

        // Caso 4: insertar en una posicion ocupada
        System.out.println("\nCASO 4: Insertar un hijo en una posicion ocupada");
        Nodo<String> fisei = inicial.buscar(d -> d.equals("FISEI"));
        Nodo<String> resultado = inicial.agregarIzquierdo(fisei, "NUEVO");
        if (resultado == null) {
            System.out.println("  Intento de agregar \"NUEVO\" como hijo izquierdo de FISEI -> RECHAZADO"
                    + " (posicion ocupada por " + fisei.getIzquierdo().getDato() + ").  [OK]");
        } else {
            System.out.println("  ERROR: se inserto en una posicion ocupada.  [FALLO]");
        }
        verificar("Nodos tras el intento", 9, inicial.contarNodos());

        // Caso 5: grado de FISEI y SOFTWARE
        System.out.println("\nCASO 5: Grado de FISEI y SOFTWARE");
        Nodo<String> software = inicial.buscar(d -> d.equals("SOFTWARE"));
        verificar("Grado de FISEI", 2, fisei.grado());
        verificar("Grado de SOFTWARE", 2, software.grado());

        System.out.println("\n########## FIN DE LAS PRUEBAS ##########");
    }

    private static void verificar(String descripcion, int esperado, int obtenido) {
        String estado = (esperado == obtenido) ? "[OK]" : "[FALLO]";
        System.out.println("  " + descripcion + ": esperado = " + esperado
                + ", obtenido = " + obtenido + "  " + estado);
    }
}
