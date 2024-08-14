package BusinessLogic;

public class AppBarPoliEats {
    // APLICACIÓN DEL PATRÓN SINGLETON
    private static AppBarPoliEats instance;

    public static final int MAX_ADMINISTRADORES = 500;
    private Administrador[] administradores;
    private ListaSimple<ProductoDeVenta> listSimpleProductos;
    private ColaSimple<Pedido> colaDePedidos;
    private int contadorDeAdministradores;
    private Ganancia[] ganancias;
    private int contadorDeGanancias; 

    private AppBarPoliEats() {
        this.administradores = new Administrador[MAX_ADMINISTRADORES];
        this.listSimpleProductos = new ListaSimple<>();
        this.colaDePedidos = new ColaSimple<>();
        this.contadorDeAdministradores = 0;
        this.ganancias = new Ganancia[1000];
        this.contadorDeGanancias = 0; 
       // inicializarAdministradores();
    }

    public static AppBarPoliEats getInstance() {
        if (instance == null) {
            instance = new AppBarPoliEats();
        }
        return instance;
    }
    
    public boolean agregarGanancia(Ganancia ganancia) {
    for (int i = 0; i < contadorDeGanancias; i++) {
        if (ganancias[i].getFecha().equals(ganancia.getFecha())) {
            ganancias[i].añadirGanancia(ganancias[i].getTotalGanancia() + ganancia.getTotalGanancia());
            return true; // Salir del método una vez que se encuentra la fecha
        }
    }
    // Si no se encuentra una ganancia con la misma fecha, se agrega la nueva
    ganancias[contadorDeGanancias++] = ganancia;
    return true;
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
    
    public int getContadorAdministradores(){
        return contadorDeAdministradores;
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
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            System.out.println(listSimpleProductos.getDato(i));
        }
    }

    //Método para imprimir productos por categoría GENERAL:
    public void imprimirProductosDelInventarioPorCategoría(Categoría categoría) {
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            ProductoDeVenta producto = listSimpleProductos.getDato(i);
            if (producto.getCategoría() == categoría) {
                System.out.println(producto);
            }
        }
    }

    // Método para devolver el número de productos según alguna categoría
    public int getNúmeroDeProductosDelInventarioCategoría(Categoría categoría) {
        int count = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i).getCategoría() == categoría) {
                count++;
            }
        }
        return count;
    }

    // Método para devolver un arreglo de la lista de productos según la categoría
    public ProductoDeVenta[] getProductosDelInventarioCategoría(Categoría categoría) {
        int númeroDeCategoría = getNúmeroDeProductosDelInventarioCategoría(categoría);
        ProductoDeVenta[] auxProductos = new ProductoDeVenta[númeroDeCategoría];
        int contador = 0;
        for (int i = 0; i < listSimpleProductos.getNúmeroDeDatos(); i++) {
            if (listSimpleProductos.getDato(i).getCategoría() == categoría) {
                auxProductos[contador] = listSimpleProductos.getDato(i);
                contador++;
            }
        }
        return auxProductos;
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

    public void eliminarProductoDelInventario(ProductoDeVenta producto){
        listSimpleProductos.eliminarDato(producto);
    }
    public ProductoDeVenta buscarProductoDelInventario(ProductoDeVenta producto){
        return listSimpleProductos.buscarDato(producto);
    }
    public boolean modificarProductoDelInventario(ProductoDeVenta productoActual, ProductoDeVenta nuevoProducto){
        return listSimpleProductos.modificarDato(productoActual, nuevoProducto);
    }

    public int getContadorDeGanancias() {
        return contadorDeGanancias;
    }

    public Ganancia getGanancia(int índice) {
        return ganancias[índice];
    }
}

