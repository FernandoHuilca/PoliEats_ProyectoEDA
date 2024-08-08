package BusinessLogic;

public class Snack extends ProductoDeVenta{
    public Snack(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String getCategoría() {
        return "Snacks";
    }
}
