import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebidas;
import BusinessLogic.ProductoDeVenta;

// Author: Fernando Huilca
public class Main {
    public static void main(String[] args) {

        AppBarPoliEats appBarPoliEats = new AppBarPoliEats();

        String nombre = "Fernando Huilca";
        String correo = "fernandohuilca3@gmail.com";
        String contraseña = "1234";


        System.out.println("CASO 1. Agregar Usuario a la app __________________________");
        appBarPoliEats.agregarUsuario(nombre, correo, contraseña);

        System.out.println("CASO 2. Agregar un producto al inventario __________________________");
        ProductoDeVenta nuevoProducto = new Bebidas("CocaCola",0.25);
        appBarPoliEats.agregarProducto(nuevoProducto);

    }
}