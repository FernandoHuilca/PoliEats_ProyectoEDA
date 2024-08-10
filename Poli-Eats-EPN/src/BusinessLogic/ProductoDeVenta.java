package BusinessLogic;
// ProductoDeVenta.java

import java.util.Objects;

public abstract class ProductoDeVenta {
    private String nombre;
    private double precio;
    private int stock;
    private Categoría categoría;
    private String rutaImagen;

    public ProductoDeVenta(String nombre, double precio, int stock, String rutaImagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.rutaImagen = rutaImagen;
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
    public String getRutaImagen(){
        return rutaImagen ;
    }
    public void setRutaImagen(String nuevaRuta){
        this.rutaImagen = nuevaRuta;
    }

    public abstract Categoría getCategoría();

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductoDeVenta other = (ProductoDeVenta) obj;
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.stock != other.stock) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.categoría, other.categoría);
    }
    
}
