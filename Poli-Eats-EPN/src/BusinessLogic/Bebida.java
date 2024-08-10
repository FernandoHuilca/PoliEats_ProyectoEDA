package BusinessLogic;

public class Bebida extends ProductoDeVenta{
    public Bebida(String nombre, double precio, int stock, String rutaImagen) {
        super(nombre, precio,stock, rutaImagen);
    }

    @Override
    public Categoría getCategoría() {
        return Categoría.BEBIDA;
    }
}
