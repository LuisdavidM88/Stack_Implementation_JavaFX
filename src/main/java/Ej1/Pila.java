package Ej1;

import java.util.Stack;

// clase que maneja la logica de la pila de publicaciones
public class Pila {
    // usamos la clase Stack de java para guardar objetos Publicacion
    private final Stack<Publicacion> pila = new Stack<>();

    // revisa si la pila esta vacia
    public boolean estaVacia() { return pila.isEmpty(); }

    // inserta un elemento en la cima
    public void push(Publicacion p) { pila.push(p); }

    // elimina y devuelve el elemento de la cima
    public Publicacion pop() throws Exception {
        if (estaVacia()) throw new Exception("Pila vacia");
        return pila.pop();
    }

    // devuelve el elemento de la cima sin eliminarlo
    public Publicacion peek() throws Exception {
        if (estaVacia()) throw new Exception("Pila vacia");
        return pila.peek();
    }

    // devuelve la cantidad de elementos en la pila
    public int size() { return pila.size(); }

    // devuelve la pila en formato de texto de cima a fondo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Contenido de la pila (el ultimo insertado se muestra primero)\n");
        for (int i = pila.size() - 1; i >= 0; i--) {
            sb.append("• ").append(pila.get(i)).append("\n");
        }
        return sb.toString();
    }
}
