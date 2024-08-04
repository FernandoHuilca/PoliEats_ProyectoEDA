package BusinessLogic;
// ProductoDeVenta.java
public abstract class ProductoDeVenta {
    private String nombre;
    private double precio;

    public ProductoDeVenta(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void aplicarDescuento(double porcentaje) {
        this.precio -= this.precio * (porcentaje / 100);
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }
}
