package BusinessLogic;

public class Postre extends ProductoDeVenta {
    public Postre(String nombre, double precio, int stock, String rutaImagen) {
        super(nombre, precio, stock, rutaImagen);
    }

    @Override
    public Categoría getCategoría() {
        return Categoría.POSTRE;
    }
}
