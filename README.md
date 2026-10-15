# T6-ArbolBinario-GuamanquispeEdwin

**Asignatura:** Estructura de Datos · **Nivel:** 3ro · Ingeniería de Software (UTA – FISEI, paralelo "B")
**Estudiante:** Guamanquispe Guaman Edwin David · **Docente:** Ing. José Caiza, Mg.
**Herramientas:** Java, Visual Studio Code y GitHub

## 1. Problema
La Universidad necesita representar la organización de sus dependencias académicas mediante un **árbol binario**. Cada nodo guarda el nombre de una dependencia y tiene como máximo dos hijos. La aplicación debe construir, visualizar y analizar esta estructura usando programación orientada a objetos y recursividad.

## 2. Estructura del árbol

```
             UNIVERSIDAD
             /         \
       FISEI             DIDE
       /   \             /   \
   SOFTWARE  TI     INVEST.  VINCULACION
    /   \
  NIVEL1 NIVEL3
```

| Métrica | Valor |
|---|---|
| Nodos | 9 |
| Hojas | 5 (TI, INVEST., VINCULACION, NIVEL1, NIVEL3) |
| Altura | 3 aristas |

> Nota: en el código se escribe `VINCULACION` sin tilde para evitar problemas de codificación en la consola de Windows. Las búsquedas del menú ignoran mayúsculas y tildes.

## 3. Organización del proyecto

```
src/
 ├── modelo/
 │    └── Nodo.java            -> Nodo<T>: dato, izquierdo, derecho, esHoja(), grado()
 ├── negocio/
 │    └── ArbolBinario.java    -> crearRaiz, agregarIzquierdo/Derecho, contarNodos,
 │                                contarHojas, altura, esVacio, mostrarArbol, buscar
 └── app/
      └── Main.java            -> menú interactivo con Scanner y casos de prueba
```

**Restricciones cumplidas:** no se usa `TreeSet`, `TreeMap`, `PriorityQueue`, `NavigableSet` ni `NavigableMap`; `contarNodos`, `contarHojas` y `altura` son **recursivos**.

## 4. Instrucciones de ejecución

Desde la carpeta raíz del proyecto:

```bash
# Compilar
javac -d out src/modelo/Nodo.java src/negocio/ArbolBinario.java src/app/Main.java

# Ejecutar
java -cp out app.Main
```

En Visual Studio Code: abrir la carpeta, abrir `src/app/Main.java` y pulsar **Run** sobre el método `main`.

## 5. Menú

```
1. Crear el arbol inicial
2. Visualizar el arbol
3. Consultar el numero de nodos
4. Consultar el numero de hojas
5. Consultar la altura
6. Consultar el grado de un nodo
7. Verificar si un nodo es hoja
8. Ejecutar casos de prueba obligatorios
0. Salir
```

## 6. Resultados (casos de prueba obligatorios)

| Caso | Esperado | Obtenido |
|---|---|---|
| Árbol vacío | 0 nodos, altura -1 | 0 nodos, altura -1 ✔ |
| Un solo nodo | 1 nodo, 1 hoja, altura 0 | 1 nodo, 1 hoja, altura 0 ✔ |
| Árbol inicial | 9 nodos, 5 hojas, altura 3 | 9 nodos, 5 hojas, altura 3 ✔ |
| Insertar en posición ocupada | Rechazado, árbol sin cambios | Rechazado (FISEI.izquierdo = SOFTWARE), 9 nodos ✔ |
| Grado de FISEI y SOFTWARE | 2 y 2 | 2 y 2 ✔ |

Salida de la visualización:

```
UNIVERSIDAD (raiz)
|-- [I] FISEI
|   |-- [I] SOFTWARE
|   |   |-- [I] NIVEL1
|   |   `-- [D] NIVEL3
|   `-- [D] TI
`-- [D] DIDE
    |-- [I] INVEST.
    `-- [D] VINCULACION
```

## 7. Capturas de pantalla
_Agregar aquí las capturas de la ejecución del menú y de los casos de prueba (carpeta `capturas/`)._

## 8. Commits sugeridos
1. `feat: clase Nodo<T> con esHoja() y grado()`
2. `feat: ArbolBinario con insercion y operaciones recursivas`
3. `feat: menu interactivo, visualizacion y casos de prueba`
4. `docs: README con problema, estructura y resultados`
