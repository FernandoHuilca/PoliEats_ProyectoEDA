package BusinessLogic;

public class ListaSimple<T> {
    private NodoListaSimple<T> nodoDeInicio;
    private int contadorDeNodos;

    public ListaSimple() {
        this.nodoDeInicio = null;
        this.contadorDeNodos = 0;
    }

    public boolean agregarALaIzquierda(T dato) {
        NodoListaSimple<T> nuevoNodo = new NodoListaSimple<>();
        nuevoNodo.setDato(dato);

        if (nodoDeInicio == null) {
            nodoDeInicio = nuevoNodo;
        } else {
            nuevoNodo.setLiga(nodoDeInicio);
            nodoDeInicio = nuevoNodo;
        }
        contadorDeNodos++;
        return true;
    }

    public boolean agregarALaDerecha(T dato) {
        NodoListaSimple<T> nuevoNodo = new NodoListaSimple<>();
        nuevoNodo.setDato(dato);

        if (nodoDeInicio == null) {
            nodoDeInicio = nuevoNodo;
        } else {
            NodoListaSimple<T> actual = nodoDeInicio;
            while (actual.getLiga() != null) {
                actual = actual.getLiga();
            }
            actual.setLiga(nuevoNodo);
        }
        contadorDeNodos++;
        return true;
    }

    public int getNúmeroDeDatos() {
        return contadorDeNodos;
    }

    public T getDato(int i) {
        if (i < 0 || i >= contadorDeNodos) {
            return null; //TODO: Ver una mejor solución
        }

        NodoListaSimple<T> actual = nodoDeInicio;
        for (int j = 0; j < i; j++) {
            actual = actual.getLiga();
        }

        return actual.getDato();
    }

    public boolean eliminarALaIzquierda() {
        if (nodoDeInicio == null) {
            return false;
        }

        nodoDeInicio = nodoDeInicio.getLiga();
        contadorDeNodos--;
        return true;
    }

    public boolean eliminarALaDerecha() {
        if (nodoDeInicio == null) {
            return false;
        }

        if (nodoDeInicio.getLiga() == null) {
            nodoDeInicio = null;
        } else {
            NodoListaSimple<T> actual = nodoDeInicio;
            while (actual.getLiga().getLiga() != null) {
                actual = actual.getLiga();
            }
            actual.setLiga(null);
        }
        contadorDeNodos--;
        return true;
    }

    public boolean eliminarEnIndice(int i) {
        if (i < 0 || i >= contadorDeNodos) {
            return false;
        }

        if (i == 0) {
            return eliminarALaIzquierda();
        }

        NodoListaSimple<T> actual = nodoDeInicio;
        for (int j = 0; j < i - 1; j++) {
            actual = actual.getLiga();
        }
        actual.setLiga(actual.getLiga().getLiga());
        contadorDeNodos--;
        return true;
    }

}

