package BusinessLogic;

public class Item {
    private ProductoDeVenta producto;
    private int cantidadDeEsteProducto;

    public Item(ProductoDeVenta producto, int cantidadDeEsteProducto) {
        this.producto = producto;
        this.cantidadDeEsteProducto = cantidadDeEsteProducto;
    }

    public ProductoDeVenta getProducto() {
        return producto;
    }

    public int getCantidadDeEsteProducto() {
        return cantidadDeEsteProducto;
    }

    public void setProducto(ProductoDeVenta producto) {
        this.producto = producto;
    }

    public void setCantidadDeEsteProducto(int cantidadDeEsteProducto) {
        this.cantidadDeEsteProducto = cantidadDeEsteProducto;
    }

    @Override
    public String toString() {
        return "Item{" +
                "producto=" + producto +
                ", cantidadDeEsteProducto=" + cantidadDeEsteProducto +
                '}';
    }

    public double getValorAPagar() {
        return producto.getPrecio() * cantidadDeEsteProducto;
    }
}
