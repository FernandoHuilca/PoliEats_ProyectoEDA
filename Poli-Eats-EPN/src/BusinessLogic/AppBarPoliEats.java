package BusinessLogic;

public class AppBarPoliEats {
    public static final int MAX_ADMINISTRADORES = 500;
    private Administrador[] administradores;
    private ListaSimple<ProductoDeVenta> listSimpleProductos;
    private ColaSimple<Pedido> colaDePedidos;
    private int contadorDeAdministradores;

    public AppBarPoliEats() {
        this.administradores = new Administrador[MAX_ADMINISTRADORES];
        this.listSimpleProductos = new ListaSimple<>();
        this.colaDePedidos = new ColaSimple<>();
        this.contadorDeAdministradores = 0;
        inicializarAdministradores();
    }

    private void inicializarAdministradores() {
        for (int i = 0; i < MAX_ADMINISTRADORES; i++) {
            administradores[i] = new Administrador();
        }
    }

    public boolean agregarAdministrador(Administrador nuevoAdministrador) {
        if (contadorDeAdministradores < MAX_ADMINISTRADORES) {
            administradores[contadorDeAdministradores++] = nuevoAdministrador;
            return true;
        }
        return false;
    }

    public boolean agregarProductoIzquierda(ProductoDeVenta nuevoProducto) {
        return listSimpleProductos.agregarALaIzquierda(nuevoProducto);
    }


    public Administrador[] getAdministradores() {
        return administradores;
    }

    public String getNombreAdministrador(int numAdministrador) {
        return administradores[numAdministrador].getNombre();
    }

    public int getNúmeroDeProductosDelInventario() {
        return listSimpleProductos.getNúmeroDeDatos();
    }

    public ProductoDeVenta getProductoDelInventario(int i) {
        return listSimpleProductos.getDato(i);
    }

    public boolean agregarProductoDerecha(ProductoDeVenta nuevoProducto) {
        return listSimpleProductos.agregarALaDerecha(nuevoProducto);
    }

    public boolean eliminarProductoIzquierda() {
        return listSimpleProductos.eliminarALaIzquierda();
    }

    public boolean eliminarProductoDerecha() {
        return listSimpleProductos.eliminarALaDerecha();
    }

    public boolean eliminarProductoEnElÍndice(int índice) {
        return listSimpleProductos.eliminarEnIndice(índice);
    }

    public boolean agregarPedido(Pedido nuevoPedido) {
        return colaDePedidos.agregarDato(nuevoPedido);
    }

    public Pedido getPedido(int i) {
        return colaDePedidos.getDato(i);
    }

    public int getNúmeroDePedidos() {
        return colaDePedidos.getNumeroDeDatos();
    }

    public String getNombreDelClienteDelPedido(int índiceDelPedido) {
        return colaDePedidos.getDato(índiceDelPedido).getNombreDelCliente();
    }


    public void imprimePedido(int índiceDelPedido) {
        System.out.println(colaDePedidos.getDato(índiceDelPedido));
    }

    public Pedido eliminarPedido() {
        return colaDePedidos.eliminarDato();
    }

    public void imprimirProductosEnInventario() {
        for(int i = 0 ; i < listSimpleProductos.getNúmeroDeDatos(); i ++){
            System.out.println(listSimpleProductos.getDato(i));
        }
    }

    // Método para imprimir productos por categoría
    public void imprimirProductosPorCategoría() {
        System.out.println("BEBIDAS");
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof Bebida) {
                System.out.println(producto);
            }
        }
        System.out.println("SNACKS");
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof Snack) {
                System.out.println(producto);
            }
        }
        System.out.println("COMIDA RÁPIDA");
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof ComidaRápida) {
                System.out.println(producto);
            }
        }
        System.out.println("POSTRE");
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof Postre) {
                System.out.println(producto);
            }
        }
    }

    public void imprimirProductosBebidas() {
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof Bebida) {
                System.out.println(producto);
            }
        }
    }

    public void imprimirProductosSnacks() {
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof Snack) {
                System.out.println(producto);
            }
        }
    }

    public void imprimirProductosComidaRápida() {
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof ComidaRápida) {
                System.out.println(producto);
            }
        }
    }

    public void imprimirProductosPostre() {
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto instanceof Postre) {
                System.out.println(producto);
            }
        }
    }

    public boolean modificarProductoNombre(int índiceDelProducto, String nombre) {
        return listSimpleProductos.getDato(índiceDelProducto).setNombre(nombre);
    }

    public boolean modificarProductoPrecio(int índiceDelProducto, double precio) {
        return listSimpleProductos.getDato(índiceDelProducto).setPrecio(precio);
    }

    public boolean modificarProductoStock(int índiceDelProducto, int cantidadDeStock) {
        return listSimpleProductos.getDato(índiceDelProducto).setStock(cantidadDeStock);
    }

    // Métodos para contar productos por categoría
    public int getNúmeroDeProductosDelInventarioBebidas() {
        int count = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof Bebida) {
                count++;
            }
        }
        return count;
    }

    public int getNúmeroDeProductosDelInventarioSnacks() {
        int count = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof Snack) {
                count++;
            }
        }
        return count;
    }

    public int getNúmeroDeProductosDelInventarioComidaRápida() {
        int count = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof ComidaRápida) {
                count++;
            }
        }
        return count;
    }

    public int getNúmeroDeProductosDelInventarioPostres() {
        int count = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof Postre) {
                count++;
            }
        }
        return count;
    }


    public ProductoDeVenta[] getProductosDelInventarioBebidas() {
        int númeroDeBebidas = getNúmeroDeProductosDelInventarioBebidas();
        ProductoDeVenta[] auxProductos = new ProductoDeVenta[númeroDeBebidas];
        int contador = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof Bebida) {
                auxProductos[contador] = listSimpleProductos.getDato(i);
                contador++;
            }
        }
        return auxProductos;
    }
    public ProductoDeVenta[] getProductosDelInventarioSnacks() {
        int númeroDeSnacks = getNúmeroDeProductosDelInventarioSnacks();
        ProductoDeVenta[] auxProductos = new ProductoDeVenta[númeroDeSnacks];
        int contador = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof Snack) {
                auxProductos[contador] = listSimpleProductos.getDato(i);
                contador++;
            }
        }
        return auxProductos;
    }

    public ProductoDeVenta[] getProductosDelInventarioComidaRápida() {
        int númeroDeComidaRapida = getNúmeroDeProductosDelInventarioComidaRápida();
        ProductoDeVenta[] auxProductos = new ProductoDeVenta[númeroDeComidaRapida];
        int contador = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof ComidaRápida) {
                auxProductos[contador] = listSimpleProductos.getDato(i);
                contador++;
            }
        }
        return auxProductos;
    }

    public ProductoDeVenta[] getProductosDelInventarioPostres() {
        int númeroDePostres = getNúmeroDeProductosDelInventarioPostres();
        ProductoDeVenta[] auxProductos = new ProductoDeVenta[númeroDePostres];
        int contador = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i) instanceof Postre) {
                auxProductos[contador] = listSimpleProductos.getDato(i);
                contador++;
            }
        }
        return auxProductos;
    }

}
