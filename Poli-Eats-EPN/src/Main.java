import BusinessLogic.*;

// Author: Fernando Huilca
public class Main {
    public static void main(String[] args) {

        // Códigos de escape ANSI para colores
        String ANSI_RESET = "\u001B[0m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_YELLOW = "\u001B[33m";
        // Crear app
        AppBarPoliEats appBarPoliEats = AppBarPoliEats.getInstance();
        // Pedir datos para administrador
        String nombre = "Fernando Huilca";
        String correo = "fernandohuilca3@gmail.com";
        String contraseña = "1234";

        System.out.println(ANSI_YELLOW +  "CASO 1. Agregar Administrador a la app __________________________" + ANSI_RESET);
        Administrador nuevoAdministrador = new Administrador(nombre, correo, contraseña);
        Administrador nuevoAdministrador1 = new Administrador("Mateo", "mateonsg12@gmail.com", "1234");
        if (appBarPoliEats.agregarAdministrador(nuevoAdministrador)) {
            System.out.println("Se agregó con éxito");
        } else {
            System.out.println("ERROR: No se pudo agregar el administrador");
        }
        if (appBarPoliEats.agregarAdministrador(nuevoAdministrador1)) {
            System.out.println("Se agregó con éxito");
        } else {
            System.out.println("ERROR: No se pudo agregar el administrador");
        }


        System.out.println(ANSI_YELLOW +"CASO 2. Agregar producto a la derecha en la app __________________________"+ ANSI_RESET);
        ProductoDeVenta nuevoProducto = new Bebida("CocaCola", 0.75, 100);
        ProductoDeVenta nuevoProducto1 = new Postre(" Dona ", 0.80, 25);
        ProductoDeVenta nuevoProducto2 = new Bebida("Inka Cola", 0.25, 100);

        if (appBarPoliEats.agregarProductoDerecha(nuevoProducto)) {
            System.out.println("Se agregó con éxito");
        } else {
            System.out.println("ERROR: No se pudo agregar el producto");
        }
        appBarPoliEats.agregarProductoDerecha(nuevoProducto1);
        appBarPoliEats.agregarProductoDerecha(nuevoProducto2);


        System.out.println(ANSI_YELLOW +"CASO 3. Imprimir Productos de Venta __________________________"+ ANSI_RESET);
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventario(); i++) {
            System.out.println("Producto " + i + " " + appBarPoliEats.getProductoDelInventario(i));
        }


        System.out.println(ANSI_YELLOW +"CASO 3.1: Imprimir un producto con un índice fuera del rango"+ ANSI_RESET);
        System.out.println("PRODUCTO FUERA DE RANGO " + appBarPoliEats.getProductoDelInventario(4));

        System.out.println("CASO 4. Imprimir administradores __________________________");
        for (int i = 0; i < appBarPoliEats.getAdministradores().length; i++) {
            if (appBarPoliEats.getNombreAdministrador(i) != null) {
                System.out.println(" num " + i + " " + appBarPoliEats.getNombreAdministrador(i));
            }
        }

        System.out.println(ANSI_YELLOW +"CASO 5. Eliminar producto a la izquierda en la app __________________________"+ ANSI_RESET);
           if(appBarPoliEats.eliminarProductoIzquierda()){
               System.out.println("Se eliminó con éxito a la izquierda ");
           }
           else System.out.println("ERROR No se pudo eliminar a la izquierda");

           for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventario(); i++) {
            System.out.println("Producto " + i + " " + appBarPoliEats.getProductoDelInventario(i));
           }


        System.out.println(ANSI_YELLOW +"CASO 6. Eliminar producto a la derecha en la app __________________________"+ ANSI_RESET);
        if(appBarPoliEats.eliminarProductoDerecha()){
            System.out.println("Se eliminó con éxito a la derecha");
        }
        else System.out.println("ERROR No se pudo eliminar a la derecha");

        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventario(); i++) {
            System.out.println("Producto " + i + " " + appBarPoliEats.getProductoDelInventario(i));
        }


        System.out.println(ANSI_YELLOW +"CASO 7. Eliminar producto en un índice dato en la app __________________________"+ ANSI_RESET);
        int índice = 0;
        if(appBarPoliEats.eliminarProductoEnElÍndice(índice)){
            System.out.println("Se eliminó con éxito en el índice " +  índice);
        }
        else System.out.println("ERROR No se pudo eliminar en el índice " + índice);

        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventario(); i++) {
            System.out.println("Producto " + i + " " + appBarPoliEats.getProductoDelInventario(i));
        }


        System.out.println(ANSI_YELLOW +"Caso 8 : Agregar pedido a la cola en la app ____________________________________"+ ANSI_RESET);
        appBarPoliEats.agregarProductoIzquierda(nuevoProducto);
        //Comprando aun
        ListaSimple<Item> items = new ListaSimple<>();
        items.agregarALaDerecha(new Item(appBarPoliEats.getProductoDelInventario(0), 3));
        items.agregarALaDerecha(new Item(appBarPoliEats.getProductoDelInventario(0), 4));
        //Aqui das click
        Pedido nuevoPedido = new Pedido("FernandoHuilca", items);
        Pedido nuevoPedido1 = new Pedido("MateoQuisilema", items);
        appBarPoliEats.agregarPedido(nuevoPedido1);
        if (appBarPoliEats.agregarPedido(nuevoPedido)){
            System.out.println("Se agregó con éxito el pedido ");
        }
        else System.out.println("ERROR No se pudo agregar el pedido");
        for (int i = 0 ; i < appBarPoliEats.getNúmeroDePedidos(); i++){
            System.out.println(appBarPoliEats.getNombreDelClienteDelPedido(i));
           appBarPoliEats.imprimePedido(i);
        }

        System.out.println(ANSI_YELLOW +"Caso 9 : Eliminar pedido de la cola en la app ____________________________________"+ ANSI_RESET);
        System.out.println("El pedido eliminado es : " + appBarPoliEats.eliminarPedido());

        System.out.println(ANSI_YELLOW +"Caso 10 : ImprimirProductos que tengo ____________________________________"+ ANSI_RESET);
        appBarPoliEats.imprimirProductosEnInventario();

        System.out.println(ANSI_YELLOW +"Caso 11 : ImprimirProductos que tengo por Categoría ____________________________________"+ ANSI_RESET);
        ProductoDeVenta nuevoProducto3 = new Postre("Pastel", 0.75, 100);
        ProductoDeVenta nuevoProducto4 = new Postre("Gelatina", 0.75, 100);
        ProductoDeVenta nuevoProducto5 = new Snack("Doritos", 0.65, 100);
        ProductoDeVenta nuevoProducto6 = new Snack("Ruffles", 0.65, 100);
        ProductoDeVenta nuevoProducto7 = new ComidaRápida("Hamburguesa", 0.65, 100);
        ProductoDeVenta nuevoProducto8 = new ComidaRápida("Pizza", 0.65, 100);
        ProductoDeVenta nuevoProducto9 = new ComidaRápida("Salchipapa", 0.65, 100);

        appBarPoliEats.agregarProductoDerecha(nuevoProducto9);
        appBarPoliEats.agregarProductoDerecha(nuevoProducto8);
        appBarPoliEats.agregarProductoDerecha(nuevoProducto7);
        appBarPoliEats.agregarProductoDerecha(nuevoProducto6);
        appBarPoliEats.agregarProductoIzquierda(nuevoProducto5);
        appBarPoliEats.agregarProductoDerecha(nuevoProducto4);
        appBarPoliEats.agregarProductoDerecha(nuevoProducto3);
        System.out.println("_________________________________");
        appBarPoliEats.imprimirProductosBebidas();
        System.out.println("_________________________________");
        appBarPoliEats.imprimirProductosSnacks();
        System.out.println("_________________________________");
        appBarPoliEats.imprimirProductosComidaRápida();
        System.out.println("_________________________________");
        appBarPoliEats.imprimirProductosPostre();
        System.out.println("_________________________________");

        appBarPoliEats.imprimirProductosPorCategoría();

        System.out.println(ANSI_YELLOW +"Caso 12 : Modificar producto ____________________________________"+ ANSI_RESET);
        appBarPoliEats.modificarProductoNombre(0, "Big Cola");
        appBarPoliEats.modificarProductoPrecio(0, 0.30);
        appBarPoliEats.modificarProductoStock(0, 15000);
        appBarPoliEats.imprimirProductosPorCategoría();
        System.out.println(ANSI_YELLOW +"Caso 13 : Método que desea mi Mateo Simbaña ____________________________________"+ ANSI_RESET);
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventario(); i++){
            System.out.println("Nombre: " + appBarPoliEats.getProductoDelInventario(i).getNombre());
            System.out.println("Stock: " + appBarPoliEats.getProductoDelInventario(i).getStock());
            System.out.println("Precio: " + appBarPoliEats.getProductoDelInventario(i).getPrecio());
        }

        System.out.println(ANSI_YELLOW +"Caso 14 : Método que desea mi Mateo Simbaña get Producto Por categoría ____________________________________"+ ANSI_RESET);
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventarioBebidas(); i++){
            System.out.println("Nombre: " + appBarPoliEats.getProductosDelInventarioBebidas()[i].getNombre());
            System.out.println("Stock: " + appBarPoliEats.getProductosDelInventarioBebidas()[i].getStock());
            System.out.println("Precio: " + appBarPoliEats.getProductosDelInventarioBebidas()[i].getPrecio());
        }
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventarioPostres(); i++){
            System.out.println("Nombre: " + appBarPoliEats.getProductosDelInventarioPostres()[i].getNombre());
            System.out.println("Stock: " + appBarPoliEats.getProductosDelInventarioPostres()[i].getStock());
            System.out.println("Precio: " + appBarPoliEats.getProductosDelInventarioPostres()[i].getPrecio());
        }
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventarioComidaRápida(); i++){
            System.out.println("Nombre: " + appBarPoliEats.getProductosDelInventarioComidaRápida()[i].getNombre());
            System.out.println("Stock: " + appBarPoliEats.getProductosDelInventarioComidaRápida()[i].getStock());
            System.out.println("Precio: " + appBarPoliEats.getProductosDelInventarioComidaRápida()[i].getPrecio());
        }
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventarioSnacks(); i++){
            System.out.println("Nombre: " + appBarPoliEats.getProductosDelInventarioSnacks()[i].getNombre());
            System.out.println("Stock: " + appBarPoliEats.getProductosDelInventarioSnacks()[i].getStock());
            System.out.println("Precio: " + appBarPoliEats.getProductosDelInventarioSnacks()[i].getPrecio());
            System.out.println("AQUIIIII" + appBarPoliEats.getProductoDelInventario(0).getCategoría());
        }

    }
}