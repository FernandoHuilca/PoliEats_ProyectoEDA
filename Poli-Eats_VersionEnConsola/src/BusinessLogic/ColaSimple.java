package BusinessLogic;

public class ColaSimple<T> {
    // Atributos
    private ListaSimple<T> datos;

    public ColaSimple() {
        this.datos = new ListaSimple<>();
    }

    // Método para agregar un pedido al final de la cola
    public boolean agregarDato(T nuevoDato) {
        return datos.agregarALaDerecha(nuevoDato);
    }

    // Método para remover un pedido del frente de la cola
    public T eliminarDato() {
        if (datos.getNúmeroDeDatos() == 0) {
            return null; // La cola está vacía
        }
        T dato = datos.getDato(0);
        datos.eliminarALaDerecha();
        return dato;
    }

    // Método para obtener el número de pedidos en la cola
    public int getNumeroDeDatos() {
        return datos.getNúmeroDeDatos();
    }

    public T getDato(int i) {
        return datos.getDato(i);
    }


}



/*
* public class ColaSimple {
    //Atributos
    private String[] cola1;
    private int frente;
    private int fin;

    public ColaSimple() {
        this(10);  // Valor por defecto si no se especifica dimensión
    }

    public ColaSimple(int dimension) {
        this.cola1 = new String[dimension]; // Tamaño Cola
        this.frente = -1; //Frente nicia en 0
        this.fin = -1; // Fin inicia en 0
    }

    // Obtener la Cola
    public String[] getCola() {
        return cola1;
    }

    // Setear la Cola
    public void setCola(String[] cola) {
        this.cola1 = cola;
    }

    // Método para ingresar un elemento a la Cola desde el fin
    public void insertarCola(String nombre) {
        if (estaLlena()) {
            JOptionPane.showMessageDialog(null, "Cola llena. No se puede insertar el elemento: " + nombre);
        } else {
            cola1[++fin] = nombre;
            if (frente == -1) { // El frente incrementa en 1 si se ingresa un dato por primera vez
                frente = 0;
            }
        }
    }

    // Método para eliminar un elemento a la Cola desde el frente
    public void eliminarCola() {
        if (estaVacia()) {
            JOptionPane.showMessageDialog(null, "Cola vacía. No se puede eliminar ningún elemento.");
        } else {
            cola1[frente] = null;
            if (frente == fin) {  // Si existe un solo elemento en la Cola, la Cola pasa a estar vacía
                frente = -1;
                fin = -1;
            } else {
                frente++;
            }
        }
    }
    
    // Método que indica que la Cola está vacía
    public boolean estaVacia() {
        return frente == -1;
    }

    // Método que indica que la Cola está llena
    public boolean estaLlena() {
        return (fin + 1 == frente) || (frente == 0 && fin == cola1.length - 1);
    }
    
    // Método que imprime los elementos de la Cola
    @Override
    public String toString() {
        if (estaVacia()) {
            return "Cola vacía";
        }
        StringBuilder salida = new StringBuilder();
        int i = frente;
        // Guarda los elementos de la Cola en la variable salida
        while (true) {
            salida.append(cola1[i]).append(" | ");
            if (i == fin) {
                break;
            }
            i = (i + 1) % cola1.length;
        }
        return salida.toString();
    }
}

* */