package BusinessLogic;

public class Snack extends ProductoDeVenta{
    public Snack(String nombre, double precio, int stock, String rutaImagen) {
        super(nombre, precio, stock, rutaImagen);
    }

    @Override
    public Categoría getCategoría() {
        return Categoría.SNACK;
    }
}
