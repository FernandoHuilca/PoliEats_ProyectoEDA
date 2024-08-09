package BusinessLogic;

public class Postre extends ProductoDeVenta {
    public Postre(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public Categoría getCategoría() {
        return Categoría.POSTRE;
    }
}
