/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.MetodosFrecuentes;
import BusinessLogic.ProductoDeVenta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableColumn colProducto;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colValor;
    @FXML
    private TableView<?> tblTotalAPagar;
    @FXML
    private TableColumn<?, ?> colTotalAPagar;
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
    
    private ObservableList<ProductoDeVenta> productos;
    private int cantidadDadaPorSpinner;
    private String categoriaSeleccionada;

    AppBarPoliEats bar = AppBarPoliEats.getInstance();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> spinnerControlador = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        spinnerControlador.setValue(1);
        spinnerContadorProductos.setValueFactory(spinnerControlador);
        
        //Inicializar Observable
        productos = FXCollections.observableArrayList();
        this.colProducto.setCellValueFactory(new PropertyValueFactory("producto"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        this.colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        
        txtCategoriaBebida.setOnAction(e -> seleccionarCategoria("Bebida", bar.getNúmeroDeProductosDelInventarioBebidas()));
        txtCategoriaComidaRápida.setOnAction(e -> seleccionarCategoria("Comida rápida", bar.getNúmeroDeProductosDelInventarioComidaRápida()));
        txtCategoriaPostre.setOnAction(e -> seleccionarCategoria("Postre", bar.getNúmeroDeProductosDelInventarioPostres()));
        txtCategoriaSnack.setOnAction(e -> seleccionarCategoria("Snack", 10/*bar.getNúmeroDeProductosDelInventarioSnacks()*/));
        
        //Declarar Paginación
        //paginaDeProductos.
        paginaDeProductos.setPageCount(5);
        
    }    

    @FXML
    public void confirmarProducto(ActionEvent event) {
        cantidadDadaPorSpinner = spinnerContadorProductos.getValue();
    }
    
    private void seleccionarCategoria(String categoria, int cantidadDeProductos) {
        this.categoriaSeleccionada = categoria;
        botonElegircategorias.setText("Categoría seleccionada: " + categoria);
        paginaDeProductos.setPageCount(cantidadDeProductos);
    }
 
}
