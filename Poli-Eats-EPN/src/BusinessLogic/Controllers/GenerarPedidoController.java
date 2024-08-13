/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebida;
import BusinessLogic.Categoría;
import BusinessLogic.ComidaRápida;
import BusinessLogic.Item;
import BusinessLogic.ListaSimple;
import BusinessLogic.MetodosFrecuentes;
import BusinessLogic.Otro;
import BusinessLogic.Pedido;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
    private ObservableList<ProductoDeVenta> productosSeleccionados;
    
    @FXML
    private TableView<Pedido> tblTotalAPagar;
    @FXML
    private TableColumn colNombreFinal;
    @FXML
    private TableColumn colValorFinalAPagar;
    private ObservableList<Pedido> pedidoGenerado;
    
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
    private MenuItem txtCategoriaFrutas;
    @FXML
    private MenuItem txtCategoriaOtros;
    
    @FXML
    private TableView<ProductoDeVenta> tblMostrarProductos;
    @FXML
    private TableColumn colProducto;
    @FXML
    private TableColumn<ProductoDeVenta, ImageView> colImagen;
    @FXML
    private TableColumn colPrecio;
    private ObservableMap<ProductoDeVenta, Image> productImageMap;
    
    @FXML
    private TextField txtNombreCliente;
    
    @FXML
    private Button botonConfirmarPedido;
    @FXML
    private Button botonConfirmarNombre;
    @FXML
    private Button botonConfirmarProducto;
    
    ProductoDeVenta productoMostradoAux, productoAuxGuardar;
    AppBarPoliEats bar = AppBarPoliEats.getInstance();
    
    ListaSimple<ProductoDeVenta> productosCargados;
    ListaSimple<Item> items;
    private int cantidadDadaPorSpinner;
    private String categoriaSeleccionada;
    int stockReal;
    String nombreReal, nombreAuxDelCliente;
    double precioReal;  
    private boolean verificaciónDeNuevoNombre = false;
    @FXML
    private Button btnRegresar;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicializar Observable
        productImageMap = FXCollections.observableHashMap();
        ingresarSetCellValueFactory(this.colNombre, "nombre");
        ingresarSetCellValueFactory(this.colCantidad, "stock");
        ingresarSetCellValueFactory(this.colValor, "precio");
        
        productosSeleccionados = FXCollections.observableArrayList();
        ingresarSetCellValueFactory(this.colProducto, "nombre");
        ingresarSetCellValueFactory(this.colPrecio, "precio");
        this.colImagen.setCellValueFactory(cellData -> {
            ProductoDeVenta product = cellData.getValue();
            
            Image image = productImageMap.get(product);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(50);
            imageView.setFitWidth(88);
            //imageView.setPreserveRatio(true);
            return new SimpleObjectProperty<>(imageView);
        });
        
        pedidoGenerado = FXCollections.observableArrayList();
        ingresarSetCellValueFactory(this.colValorFinalAPagar, "valorTotalAPagar");
        ingresarSetCellValueFactory(this.colNombreFinal, "nombreDelCliente");
        
        //Cargar Categorías
        txtCategoriaBebida.setOnAction(e -> seleccionarCategoria("Bebida", Categoría.BEBIDA));
        txtCategoriaComidaRápida.setOnAction(e -> seleccionarCategoria("Comida rápida", Categoría.COMIDA_RÁPIDA));
        txtCategoriaPostre.setOnAction(e -> seleccionarCategoria("Postre", Categoría.POSTRE));
        txtCategoriaSnack.setOnAction(e -> seleccionarCategoria("Snack", Categoría.SNACK));
        txtCategoriaFrutas.setOnAction(e -> seleccionarCategoria("Snack", Categoría.FRUTA));
        txtCategoriaOtros.setOnAction(e ->seleccionarCategoria("Comida rápida", Categoría.OTRO));
        
        //Declarar Paginación
        //paginaDeProductos.
        paginaDeProductos.setPageCount(5); 
        
        //escucha de la fila
        tblMostrarProductos.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<ProductoDeVenta>() {
                @Override
                public void changed(ObservableValue<? extends ProductoDeVenta> observable, 
                                    ProductoDeVenta oldValue, 
                                    ProductoDeVenta newValue) {
                    if (newValue != null) {
                        int selectedIndex = tblMostrarProductos.getSelectionModel().getSelectedIndex();
                        paginaDeProductos.setCurrentPageIndex(selectedIndex);
                    }
                }
            }
        );
        
        //Inicialización de Items
        items = new ListaSimple();
        
        IntegerProperty numero = new SimpleIntegerProperty(0);
        // Listener que actualizará el TextField cuando cambie la propiedad
        numero.addListener((obs, oldVal, newVal) -> {
            txtNombreCliente.setText(newVal.toString());
        });
    }    

    @FXML
    public void confirmarProducto(ActionEvent event) {
        if(productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock() < 1 || spinnerContadorProductos.getValue() == 0){
            return;
        }
        
        pedidoGenerado.clear();
        this.tblTotalAPagar.getItems().clear();
        
        cantidadDadaPorSpinner = spinnerContadorProductos.getValue();
        productoMostradoAux.setStock(cantidadDadaPorSpinner);
        productoMostradoAux.setPrecio(precioReal * cantidadDadaPorSpinner);
        this.productosSeleccionados.add(productoMostradoAux);
        this.tblProductosSeleccionados.setItems(productosSeleccionados);
        
        int nuevoStock = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock() - cantidadDadaPorSpinner;
        productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).setStock(nuevoStock);
        productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).setPrecio(precioReal);
        ((SpinnerValueFactory.IntegerSpinnerValueFactory) 
                spinnerContadorProductos.getValueFactory()).setMax(productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock());
        
        productoAuxGuardar.setStock(nuevoStock);
        productoAuxGuardar.setPrecio(precioReal);
        Item itemAux = new Item(productoAuxGuardar ,cantidadDadaPorSpinner);
        items.agregarALaDerecha(itemAux);
    }
    
    @FXML
    private void confirmarPedido(ActionEvent event) {
        if(!verificaciónDeNuevoNombre){
            MetodosFrecuentes.mostrarAlerta("SIN NOMBRE DEL CLIENTE", "Antes de Continuar Ingrese y Confirme el Nombre del Cliente...");
            return;
        }

        Pedido nuevoPedido = new Pedido(nombreAuxDelCliente, items);
        bar.agregarPedido(nuevoPedido); // Agregar el pedido al singleton

        // Resetear estado
        verificaciónDeNuevoNombre = false;
        productosSeleccionados.clear();
        tblProductosSeleccionados.getItems().clear();
        items = new ListaSimple();

        this.pedidoGenerado.add(nuevoPedido);
        this.tblTotalAPagar.setItems(pedidoGenerado);
    }

    @FXML
    private void confirmarNombre(ActionEvent event) {
        verificaciónDeNuevoNombre = true;
        nombreAuxDelCliente = txtNombreCliente.getText();
    }
    
    private void ingresarSetCellValueFactory(TableColumn columna, String nombreDelAtributo){
        columna.setCellValueFactory(new PropertyValueFactory(nombreDelAtributo));
    }
    
    private void seleccionarCategoria(String categoria, Categoría tipoDeCategoría) {
        this.categoriaSeleccionada = categoria;
        botonElegircategorias.setText("Categoría seleccionada: " + categoria);
        productosCargados = new ListaSimple();
        guardarEnListaSimpleProductosDeCategoría(productosCargados, bar.getProductosDelInventarioCategoría(tipoDeCategoría));
        paginaDeProductos.setPageCount(bar.getNúmeroDeProductosDelInventarioCategoría(tipoDeCategoría));
        mostrarProductosPorPágina(tipoDeCategoría);
        
        mostrarTodosLosProductosDeLaCategoría(tipoDeCategoría);
    }
    private void mostrarTodosLosProductosDeLaCategoría(Categoría tipoDeCategoría) {
            productImageMap.clear();
            this.tblMostrarProductos.getItems().clear();
        for (int i = 0; i < bar.getNúmeroDeProductosDelInventarioCategoría(tipoDeCategoría); i++){
            if(bar.getProductosDelInventarioCategoría(tipoDeCategoría)[i].getStock() != 0){
                ProductoDeVenta producto = bar.getProductosDelInventarioCategoría(tipoDeCategoría)[i];
                Image imageAux = new Image(producto.getRutaImagen());
                
                productImageMap.put(producto, imageAux);
                this.tblMostrarProductos.getItems().addAll(producto);
                
            }
        } 
        //seleccionador de Categorías 
    }
    
    private void mostrarProductosPorPágina(Categoría tipoDeCategoría) {
        
        paginaDeProductos.setPageFactory(new Callback<Integer, Node>() {
    @Override
    public Node call(Integer pageIndex) {
        stockReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock();
        nombreReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getNombre();
        precioReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getPrecio();
        String rutaReal = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getRutaImagen();
        switch (tipoDeCategoría) {
                case Categoría.BEBIDA:
                    productoMostradoAux = new Bebida(nombreReal, 0, 0, rutaReal);
                    productoAuxGuardar = new Bebida(nombreReal, 0, 0, rutaReal);
                    break;
                case Categoría.COMIDA_RÁPIDA:
                    productoMostradoAux = new ComidaRápida(nombreReal, 0, 0, rutaReal);
                    productoAuxGuardar = new ComidaRápida(nombreReal, 0, 0, rutaReal);
                break;
                case Categoría.POSTRE:
                    productoMostradoAux = new Postre(nombreReal, 0, 0, rutaReal);
                    productoAuxGuardar = new Postre(nombreReal, 0, 0, rutaReal);
                    break;
                case Categoría.SNACK:
                    productoMostradoAux = new Snack(nombreReal, 0, 0, rutaReal);
                    productoAuxGuardar = new Snack(nombreReal, 0, 0, rutaReal);
                    break;
                case Categoría.OTRO:
                    productoMostradoAux = new Otro(nombreReal, 0, 0, rutaReal);
                    productoAuxGuardar = new Otro(nombreReal, 0, 0, rutaReal);
                    break;
                case Categoría.FRUTA:
                    productoMostradoAux = new Otro(nombreReal, 0, 0, rutaReal);
                    productoAuxGuardar = new Otro(nombreReal, 0, 0, rutaReal);
                    break;
                default:
                    throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
            }
        SpinnerValueFactory<Integer> spinnerControlador = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getStock());
        spinnerControlador.setValue(0);
        spinnerContadorProductos.setValueFactory(spinnerControlador);
        String imagePath = productosCargados.getDato(paginaDeProductos.getCurrentPageIndex()).getRutaImagen();
        
        //String imagePath = "/Data/imagenes/Hamburguesa.jpg";
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

    @FXML
    private void regresarAPantallaDelAdmin(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) btnRegresar.getScene().getWindow(), "/Presentation/PantallaDelAdministrador.fxml", "Pantalla del Admin");
    }
}
