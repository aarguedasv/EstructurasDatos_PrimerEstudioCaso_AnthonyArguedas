public class PilaOperador {

    private NodoOperador cima;

    public boolean estaVacia() {
        return cima == null;
    }

    public void push(char dato) {
        NodoOperador nuevo = new NodoOperador(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public char pop() {
        if (estaVacia()) {
            throw new RuntimeException("Pop en pila de operadores vacía");
        }

        char dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public char peek() {
        if (estaVacia()) {
            throw new RuntimeException("Peek en pila de operadores vacía");
        }

        return cima.dato;
    }
}
