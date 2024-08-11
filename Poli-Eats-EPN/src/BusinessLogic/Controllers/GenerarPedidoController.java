/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebida;
import BusinessLogic.Categoría;
import BusinessLogic.ComidaRápida;
import BusinessLogic.ListaSimple;
import BusinessLogic.MetodosFrecuentes;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class GenerarPedidoController implements Initializable {

    @FXML
    private Spinner<Integer> spinnerContadorProductos;
    @FXML
    private TableView<ProductoDeVenta> tblProductosSeleccionados;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colValor;
    @FXML
    private TableView<?> tblTotalAPagar;
    @FXML
    private TableColumn colTotalAPagar;
    @FXML
    private Button botonConfirmarProducto;
    @FXML
    private Pagination paginaDeProductos;
    @FXML
    private MenuButton botonElegircategorias;
    @FXML
    private MenuItem txtCategoriaBebida;
    @FXML
    private MenuItem txtCategoriaComidaRápida;
    @FXML
    private MenuItem txtCategoriaPostre;
    @FXML
    private MenuItem txtCategoriaSnack;
    
    @FXML
    private TableView<ProductoDeVenta> tblMostrarProductos;
    @FXML
    private TableColumn colProducto;
    @FXML
    private TableColumn colImagen;
    @FXML
    private TableColumn colPrecio;
    
    ProductoDeVenta productoMostradoAux, cambiarParametrosDelProducto;
    private ObservableList<ProductoDeVenta> productosSeleccionados;
    private int cantidadDadaPorSpinner;
    private String categoriaSeleccionada;
    AppBarPoliEats bar = AppBarPoliEats.getInstance();
    int stockReal;
    String nombreReal;
    double precioReal; 
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializar Observable
        productosSeleccionados = FXCollections.observableArrayList();
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("stock"));
        this.colValor.setCellValueFactory(new PropertyValueFactory("precio"));
        
        txtCategoriaBebida.setOnAction(e -> seleccionarCategoria("Bebida", Categoría.BEBIDA));
        txtCategoriaComidaRápida.setOnAction(e -> seleccionarCategoria("Comida rápida", Categoría.COMIDA_RÁPIDA));
        txtCategoriaPostre.setOnAction(e -> seleccionarCategoria("Postre", Categoría.POSTRE));
        txtCategoriaSnack.setOnAction(e -> seleccionarCategoria("Snack", Categoría.SNACK));
        
        //Declarar Paginación
        //paginaDeProductos.
        paginaDeProductos.setPageCount(5);
        
    }    

    @FXML
    public void confirmarProducto(ActionEvent event) {
        cantidadDadaPorSpinner = spinnerContadorProductos.getValue();
        productoMostradoAux.setStock(cantidadDadaPorSpinner);
        productoMostradoAux.setPrecio(productoMostradoAux.getPrecio() * cantidadDadaPorSpinner);
        this.productosSeleccionados.add(productoMostradoAux);
        this.tblProductosSeleccionados.setItems(productosSeleccionados);
        
        ProductoDeVenta aux;
        cambiarParametrosDelProducto.setStock(stockReal - spinnerContadorProductos.getValue());
        ((SpinnerValueFactory.IntegerSpinnerValueFactory) spinnerContadorProductos.getValueFactory()).setMax(cambiarParametrosDelProducto.getStock());
    }
    
    private void seleccionarCategoria(String categoria, Categoría tipoDeCategoría) {
        this.categoriaSeleccionada = categoria;
        botonElegircategorias.setText("Categoría seleccionada: " + categoria);
        paginaDeProductos.setPageCount(bar.getNúmeroDeProductosDelInventarioCategoría(tipoDeCategoría));
        mostrarProductosPorPágina(tipoDeCategoría);
    }

    private void mostrarProductosPorPágina(Categoría tipoDeCategoría) {
        ListaSimple<ProductoDeVenta> productosCargados = new ListaSimple();
        guardarEnListaSimpleProductosDeCategoría(productosCargados, bar.getProductosDelInventarioCategoría(tipoDeCategoría));
        paginaDeProductos.setPageFactory(new Callback<Integer, Node>() {
    @Override
    public Node call(Integer pageIndex) {
        cambiarParametrosDelProducto = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex());
        stockReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock();
        nombreReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getNombre();
        precioReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getPrecio();
        switch (tipoDeCategoría) {
                case Categoría.BEBIDA:
                    productoMostradoAux = new Bebida(nombreReal, precioReal, stockReal);
                    break;
                case Categoría.COMIDA_RÁPIDA:
                    productoMostradoAux = new ComidaRápida(nombreReal, precioReal, stockReal);
                break;
                case Categoría.POSTRE:
                    productoMostradoAux = new Postre(nombreReal, precioReal, stockReal);
                    break;
                case Categoría.SNACK:
                    productoMostradoAux = new Snack(nombreReal, precioReal, stockReal); // Crea esta clase si es necesario
                    break;
                default:
                    throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
            }
        SpinnerValueFactory<Integer> spinnerControlador = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock());
        spinnerControlador.setValue(0);
        spinnerContadorProductos.setValueFactory(spinnerControlador);
        /*
        String imagePath = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getRutaDeImagen();
        */
        String imagePath = "/Data/imagenes/Hamburguesa.jpg";
        Image comprobar = new Image(imagePath);
        // Cargar la imagen correspondiente al índice de la página
         ImageView imageView = new ImageView(comprobar);
        imageView.setFitWidth(245);  // Ajusta el ancho de la imagen
        imageView.setFitHeight(220); // Ajusta la altura de la imagen
        imageView.setPreserveRatio(true); // Mantiene la proporción de la imagen

        // Devolver el contenedor con la imagen
        return new StackPane(imageView);
    }
});  
    } 
    private void guardarEnListaSimpleProductosDeCategoría(ListaSimple<ProductoDeVenta> cargar, ProductoDeVenta[] original) {
        for(ProductoDeVenta sacarInfo : original){
            cargar.agregarALaDerecha(sacarInfo);
        }
    }
}
