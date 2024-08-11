package BusinessLogic;

public class ComidaRápida extends ProductoDeVenta{
    public ComidaRápida(String nombre, double precio, int stock, String rutaImagen) {
        super(nombre, precio, stock, rutaImagen);
    }

    @Override
    public Categoría getCategoría() {
        return Categoría.COMIDA_RÁPIDA;
    }
}
