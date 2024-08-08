package BusinessLogic;

public class ComidaRápida extends ProductoDeVenta{
    public ComidaRápida(String nombre, double precio, int stock) {
        super(nombre, precio, stock);
    }

    @Override
    public String getCategoría() {
        return "Comida rápida";
    }
}
