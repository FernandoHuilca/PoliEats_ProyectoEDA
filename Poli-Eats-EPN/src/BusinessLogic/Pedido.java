package BusinessLogic;

public class Pedido {
    private String nombreDelCliente;
    private ListaSimple <Item> items;
    private double valorTotalAPagar;

    public Pedido(String nombreDelCliente, ListaSimple<Item> items) {
        this.nombreDelCliente = nombreDelCliente;
        this.items = items;
        valorTotalAPagar = 0;
        for (int i = 0 ; i < items.getNúmeroDeDatos(); i++){
            this.valorTotalAPagar += items.getDato(i).getValorAPagar();
        }
    }


    public String getNombreDelCliente() {
        return nombreDelCliente;
    }

    public void setNombreDelCliente(String nombreDelCliente) {
        this.nombreDelCliente = nombreDelCliente;
    }

    public ListaSimple<Item> getItems() {
        return items;
    }

    public void setItems(ListaSimple<Item> items) {
        this.items = items;
    }

    public int getNúmeroDeItems() {
    return items.getNúmeroDeDatos();
    }

    public ProductoDeVenta getProductos(int índiceDelProducto) {
        return items.getDato(índiceDelProducto).getProducto();
    }

    public double getValorTotalAPagar(){
        return valorTotalAPagar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido ")
                .append("nombreDelCliente ").append(nombreDelCliente).append('\'');

        sb.append(" \n");

        for (int i = 0; i < items.getNúmeroDeDatos(); i++) {
            sb.append(items.getDato(i).toString());
            if (i < items.getNúmeroDeDatos() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" \n Valor a pagar = " + getValorTotalAPagar());

        return sb.toString();
    }
}

