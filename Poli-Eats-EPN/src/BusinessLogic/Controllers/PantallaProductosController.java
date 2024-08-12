package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebida;
import BusinessLogic.ComidaRápida;
import BusinessLogic.Fruta;
import BusinessLogic.ListaSimple;
import BusinessLogic.MetodosFrecuentes;
import BusinessLogic.Otro;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class PantallaProductosController implements Initializable {
    
    AppBarPoliEats appBarPoliEats = AppBarPoliEats.getInstance();
    
    @FXML
    private Button btnRegresar;
               
    @FXML
    private Button btnAnadirImagen;
    
    @FXML
    private Button btnModificar;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private Button btnAgregar;
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtStock;
    
    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colPrecio;

    @FXML
    private TableColumn colStock;
    
    @FXML
    private TableColumn colCategoría;
    
    @FXML
    private TableView<ProductoDeVenta> tblCategorias;
    
    @FXML
    private MenuButton btnMenuCategorias;
    
    @FXML
    private MenuItem txtCategoriaBebida;
    
    @FXML
    private MenuItem txtCategoriaPostre;
    
    @FXML
    private MenuItem txtCategoriaSnack;
    
    @FXML
    private MenuItem txtCategoriaComidaRápida;
    
    @FXML
    private MenuItem txtCategoriaFruta;
    
    @FXML
    private MenuItem txtCategoriaOtro;
    
    @FXML
    private MenuButton btnFiltroCategorías;
    
    @FXML
    private MenuItem filtroCategoriaBebida;
    
    @FXML
    private MenuItem filtroCategoriaComidaRapida;
    
    @FXML 
    private MenuItem filtroCategoriaPostre;
    
    @FXML
    private MenuItem filtroCategoriaSnack;
    
    @FXML
    private MenuItem filtroCategoriaFruta;
    
    @FXML
    private MenuItem filtroCategoriaOtro;
    
    @FXML
    private MenuItem filtroMostrarTodos;
    
    ObservableList <ProductoDeVenta> listaProductos;
    FilteredList <ProductoDeVenta> listaFiltrada;
    
    private String categoriaSeleccionada;
    
    private String rutaImagen;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaProductos = FXCollections.observableArrayList();
        listaFiltrada = new FilteredList<>(listaProductos, p -> true);
        
        for (int i = 0; i < appBarPoliEats.getNúmeroDeProductosDelInventario(); i++){
            this.listaProductos.add(appBarPoliEats.getProductoDelInventario(i));
        }
        
        this.tblCategorias.setItems(listaProductos);
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colStock.setCellValueFactory(new PropertyValueFactory("stock"));
        this.colCategoría.setCellValueFactory(new PropertyValueFactory("categoría"));
        
        // Asignar manejadores de eventos a los MenuItem
        txtCategoriaBebida.setOnAction(e -> seleccionarCategoria("Bebida"));
        txtCategoriaComidaRápida.setOnAction(e -> seleccionarCategoria("Comida rápida"));
        txtCategoriaPostre.setOnAction(e -> seleccionarCategoria("Postre"));
        txtCategoriaSnack.setOnAction(e -> seleccionarCategoria("Snack")); // Añade este manejador si tienes más categorías
        txtCategoriaFruta.setOnAction(e -> seleccionarCategoria("Fruta"));
        txtCategoriaOtro.setOnAction(e -> seleccionarCategoria("Otro"));
        
        // Asignar manejadores de eventos a los MenuItem del btnFiltroCategorias
        filtroCategoriaBebida.setOnAction(e -> filtrarPorCategoria("Bebida"));
        filtroCategoriaComidaRapida.setOnAction(e -> filtrarPorCategoria("Comida rápida"));
        filtroCategoriaPostre.setOnAction(e -> filtrarPorCategoria("Postre"));
        filtroCategoriaSnack.setOnAction(e -> filtrarPorCategoria("Snack"));
        filtroCategoriaFruta.setOnAction(e -> filtrarPorCategoria("Fruta"));
        filtroCategoriaOtro.setOnAction(e -> filtrarPorCategoria("Otro"));
        filtroMostrarTodos.setOnAction(e -> mostrarTodos());
        
        btnAnadirImagen.setOnAction(e -> anadirImagen());
    }    
    
    @FXML
    private void agregarProducto(ActionEvent event) {
        try {
            String nombre = this.txtNombre.getText();
            double precio = Double.parseDouble(this.txtPrecio.getText());
            int stock = Integer.parseInt(this.txtStock.getText());
            
            if (rutaImagen == null || rutaImagen.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Advertencia");
            alert.setContentText("Por favor, selecciona una imagen.");
            alert.showAndWait();
            return;
            }
           
            if (categoriaSeleccionada == null || categoriaSeleccionada.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setTitle("Advertencia");
                alert.setContentText("Por favor, selecciona una categoría.");
                alert.showAndWait();
                return;
            }

            ProductoDeVenta producto;
            switch (categoriaSeleccionada) {
                case "Bebida" -> producto = new Bebida(nombre, precio, stock, rutaImagen);
                case "Comida rápida" -> producto = new ComidaRápida(nombre, precio, stock, rutaImagen);
                case "Postre" -> producto = new Postre(nombre, precio, stock, rutaImagen);
                case "Snack" -> producto = new Snack(nombre, precio, stock, rutaImagen); // Crea esta clase si es necesario
                case "Fruta" -> producto = new Fruta(nombre, precio, stock, rutaImagen);
                case "Otro" -> producto = new Otro(nombre, precio, stock, rutaImagen);
                default -> throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
            }

            if (!this.listaProductos.contains(producto)){
                
                this.appBarPoliEats.agregarProductoDerecha(producto);
                
                this.listaProductos.add(producto);
                this.tblCategorias.setItems(listaProductos);
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("El producto ya existe");
                alert.showAndWait();
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
    }
    
    private void seleccionarCategoria(String categoria) {
        this.categoriaSeleccionada = categoria;
        btnMenuCategorias.setText("Categoría: " + categoria);
    }
    
    private void filtrarPorCategoria(String categoria) {
        btnFiltroCategorías.setText("Filtro: " + categoria);
        listaFiltrada.setPredicate(producto -> producto.getCategoría().getCategoríaString().equals(categoria));
        tblCategorias.setItems(listaFiltrada);
        tblCategorias.refresh();
    }
    
    private void mostrarTodos() {
        btnFiltroCategorías.setText("Mostrar Todos");
        tblCategorias.setItems(listaProductos);
        tblCategorias.refresh();
    }

    @FXML
    private void seleccionarProducto(MouseEvent event) {
        ProductoDeVenta producto = this.tblCategorias.getSelectionModel().getSelectedItem();

        if (producto != null) {
            this.txtNombre.setText(producto.getNombre());
            this.txtPrecio.setText(String.valueOf(producto.getPrecio()));
            this.txtStock.setText(String.valueOf(producto.getStock()));

            // Determinar la categoría del producto y mostrarla en el MenuButton
            String categoria = producto.getCategoría().getCategoríaString();
            this.categoriaSeleccionada = categoria;
            btnMenuCategorias.setText("Categoría: " + categoria);
            
            this.rutaImagen = producto.getRutaImagen();
        }
    }
   
    @FXML
    private void modificarProducto(ActionEvent event) {
        ProductoDeVenta producto = this.tblCategorias.getSelectionModel().getSelectedItem();
        
        if (producto == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un producto");
            alert.showAndWait();
        }
        else {
            try {
                String nombre = this.txtNombre.getText();
                double precio = Double.parseDouble(this.txtPrecio.getText());
                int stock = Integer.parseInt(this.txtStock.getText());
                
                if (rutaImagen == null || rutaImagen.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Advertencia");
                    alert.setContentText("Por favor, selecciona una imagen.");
                    alert.showAndWait();
                    return;
                }

                if (categoriaSeleccionada == null || categoriaSeleccionada.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText(null);
                    alert.setTitle("Advertencia");
                    alert.setContentText("Por favor, selecciona una categoría.");
                    alert.showAndWait();
                    return;
                }

                ProductoDeVenta auxProducto;
                switch (categoriaSeleccionada) {
                    case "Bebida" -> auxProducto = new Bebida(nombre, precio, stock, rutaImagen);
                    case "Comida rápida" -> auxProducto = new ComidaRápida(nombre, precio, stock, rutaImagen);
                    case "Postre" -> auxProducto = new Postre(nombre, precio, stock, rutaImagen);
                    case "Snack" -> auxProducto = new Snack(nombre, precio, stock, rutaImagen); // Crea esta clase si es necesario
                    case "Fruta" -> auxProducto = new Fruta(nombre, precio, stock, rutaImagen);
                    case "Otro" -> auxProducto = new Otro(nombre, precio, stock, rutaImagen);
                    default -> throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
                }
                
                if (!this.listaProductos.contains(auxProducto)){
                                        
                    this.appBarPoliEats.modificarProductoDelInventario(producto, auxProducto);
                    
                    producto.setNombre(auxProducto.getNombre());
                    producto.setPrecio(auxProducto.getPrecio());
                    producto.setStock(auxProducto.getStock());
                    producto.setRutaImagen(auxProducto.getRutaImagen());
                    
                    this.tblCategorias.refresh();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("El producto ya existe");
                    alert.showAndWait();
                }
                
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Formato incorrecto");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void eliminarProducto(ActionEvent event) {
        ProductoDeVenta producto = this.tblCategorias.getSelectionModel().getSelectedItem();
        
        if (producto == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe seleccionar un producto");
            alert.showAndWait();
        }
        else {
            this.appBarPoliEats.eliminarProductoDelInventario(producto);
            
            this.listaProductos.remove(producto);
            this.tblCategorias.refresh();
        }
    }

    private void anadirImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(rutaImagen);
            rutaImagen = selectedFile.getAbsolutePath();
            System.out.println("Imagen seleccionada: " + rutaImagen); // Puedes mostrar esto en un label si lo prefieres
        }
    }

    @FXML
    private void regresarInterfazListaPedidos(ActionEvent event) {
        actualizarDatosProductos();
        MetodosFrecuentes.cambiarVentana((Stage) btnRegresar.getScene().getWindow(), "/Presentation/PantallaDelAdministrador.fxml", "Inicio");
    }
    
    
    private void actualizarDatosProductos() {
        // Lista de archivos de productos y sus tipos correspondientes
        String[][] archivosProductos = {
            {"src/Data/ProductosDeVenta/ProductosBebidas.txt", "Bebida"},
            {"src/Data/ProductosDeVenta/ProductosComidaRapida.txt", "ComidaRápida"},
            {"src/Data/ProductosDeVenta/ProductosPostre.txt", "Postre"},
            {"src/Data/ProductosDeVenta/ProductosSnacks.txt", "Snack"},
            {"src/Data/ProductosDeVenta/ProductosFruta.txt", "Fruta"},
            {"src/Data/ProductosDeVenta/ProductosOtro.txt", "Otro"}
            // Agrega más archivos y tipos según sea necesario
        };

        for (String[] archivoYTipo : archivosProductos) {
            actualizarArchivoConProductos(archivoYTipo[0], archivoYTipo[1]);
        }
    }

    private void actualizarArchivoConProductos(String archivo, String tipoProducto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (ProductoDeVenta producto : listaProductos) {
                if (producto.getClass().getSimpleName().equals(tipoProducto)) {
                    writer.write(producto.getNombre());
                    writer.newLine();
                    writer.write(String.valueOf(producto.getPrecio()));
                    writer.newLine();
                    writer.write(String.valueOf(producto.getStock()));
                    writer.newLine();
                    writer.write(producto.getRutaImagen()); // Escribe la dirección de la imagen
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
