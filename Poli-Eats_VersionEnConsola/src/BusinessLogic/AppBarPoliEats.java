package BusinessLogic;

import java.util.HashMap;
import java.util.LinkedList;

public class AppBarPoliEats {
    private Usuario[] usuarios;
    public static final int MAX_USUARIOS = 500;
    private int contadorDeUsuarios; 
    public AppBarPoliEats(){
        this.usuarios = new Usuario[MAX_USUARIOS];
        this.contadorDeUsuarios = 0 ; 
    }
    
    public void agregarUsuario(String nombre, String correo, String contraseña) {
    usuarios[contadorDeUsuarios] = new Usuario(nombre, correo, contraseña);
    contadorDeUsuarios++;
    }

    private HashMap<String, LinkedList<ProductoDeVenta>> categories;

    public PoliEats() {
        categories = new HashMap<>();
        categories.put("Bebidas", new LinkedList<>());
        categories.put("Snacks", new LinkedList<>());
        categories.put("Comida Rápida", new LinkedList<>());
        categories.put("Postres", new LinkedList<>());
        categories.put("Bocadillos", new LinkedList<>());
    }

    public void agregarProducto(String category, ProductoDeVenta producto) {
        categories.get(category).add(producto);
    }
}
