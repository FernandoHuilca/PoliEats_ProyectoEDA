/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebida;
import BusinessLogic.ComidaRápida;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class PantallaProductosController implements Initializable {
    
    
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
    
    ObservableList <ProductoDeVenta> listaProductos;
    
    private String categoriaSeleccionada;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaProductos = FXCollections.observableArrayList();
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colStock.setCellValueFactory(new PropertyValueFactory("stock"));
        this.colCategoría.setCellValueFactory(new PropertyValueFactory("categoría"));
        
        // Asignar manejadores de eventos a los MenuItem
        txtCategoriaBebida.setOnAction(e -> seleccionarCategoria("Bebida"));
        txtCategoriaComidaRápida.setOnAction(e -> seleccionarCategoria("Comida rápida"));
        txtCategoriaPostre.setOnAction(e -> seleccionarCategoria("Postre"));
        txtCategoriaSnack.setOnAction(e -> seleccionarCategoria("Snack")); // Añade este manejador si tienes más categorías
    }    
    
    @FXML
    private void agregarProducto(ActionEvent event) {
        try {
            String nombre = this.txtNombre.getText();
            double precio = Double.parseDouble(this.txtPrecio.getText());
            int stock = Integer.parseInt(this.txtStock.getText());
            //Strin ruta = 

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
                case "Bebida":
                    producto = new Bebida(nombre, precio, stock, null);
                    break;
                case "Comida rápida":
                    producto = new ComidaRápida(nombre, precio, stock, null);
                break;
                case "Postre":
                    producto = new Postre(nombre, precio, stock, null);
                    break;
                case "Snack":
                    producto = new Snack(nombre, precio, stock, null); // Crea esta clase si es necesario
                    break;
                default:
                    throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
            }

            this.listaProductos.add(producto);
            this.tblCategorias.setItems(listaProductos);
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
        btnMenuCategorias.setText("Categoría seleccionada: " + categoria);
    }
    
}
