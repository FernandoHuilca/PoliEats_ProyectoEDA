package BusinessLogic;

public class Bebida extends ProductoDeVenta{
    public Bebida(String nombre, double precio, int stock) {
        super(nombre, precio,stock);
    }

    @Override
    public String getCategoría() {
        return "Bebida";
    }
}
