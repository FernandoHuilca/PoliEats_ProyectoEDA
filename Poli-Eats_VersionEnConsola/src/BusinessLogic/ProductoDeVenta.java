package BusinessLogic;
// ProductoDeVenta.java
public abstract class ProductoDeVenta {
    private String nombre;
    private double precio;
    private int stock;
    private String categoría;

    public ProductoDeVenta(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoría = getCategoría();
    }


    public void aplicarDescuento(double porcentaje) {
        this.precio -= this.precio * (porcentaje / 100);
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " Stock : " + stock;
    }

    public boolean setNombre(String nombre) {
        this.nombre = nombre;
        return true;
    }
    public boolean setPrecio(double precio) {
        this.precio = precio;
        return true;
    }

    public boolean setStock(int cantidadDeStock) {
        this.stock = cantidadDeStock;
        return true;
    }

    public String getNombre() {
        return nombre;
    }
    public int getStock(){
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public abstract String getCategoría();

}
