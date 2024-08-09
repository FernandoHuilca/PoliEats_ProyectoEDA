/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.Bebida;
import BusinessLogic.ComidaRápida;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
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

/**
 * FXML Controller class
 *
 * @author mateo
 */
public class PantallaProductosController implements Initializable {
    
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
    private MenuItem filtroMostrarTodos;
    
    ObservableList <ProductoDeVenta> listaProductos;
    FilteredList <ProductoDeVenta> listaFiltrada;
    
    private String categoriaSeleccionada;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaProductos = FXCollections.observableArrayList();
        listaFiltrada = new FilteredList<>(listaProductos, p -> true);
        
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colStock.setCellValueFactory(new PropertyValueFactory("stock"));
        this.colCategoría.setCellValueFactory(new PropertyValueFactory("categoría"));
        
        // Asignar manejadores de eventos a los MenuItem
        txtCategoriaBebida.setOnAction(e -> seleccionarCategoria("Bebida"));
        txtCategoriaComidaRápida.setOnAction(e -> seleccionarCategoria("Comida rápida"));
        txtCategoriaPostre.setOnAction(e -> seleccionarCategoria("Postre"));
        txtCategoriaSnack.setOnAction(e -> seleccionarCategoria("Snack")); // Añade este manejador si tienes más categorías
        
        // Asignar manejadores de eventos a los MenuItem del btnFiltroCategorias
        filtroCategoriaBebida.setOnAction(e -> filtrarPorCategoria("Bebida"));
        filtroCategoriaComidaRapida.setOnAction(e -> filtrarPorCategoria("Comida rápida"));
        filtroCategoriaPostre.setOnAction(e -> filtrarPorCategoria("Postre"));
        filtroCategoriaSnack.setOnAction(e -> filtrarPorCategoria("Snacks"));
        filtroMostrarTodos.setOnAction(e -> mostrarTodos());
    }    
    
    @FXML
    private void agregarProducto(ActionEvent event) {
        try {
            String nombre = this.txtNombre.getText();
            double precio = Double.parseDouble(this.txtPrecio.getText());
            int stock = Integer.parseInt(this.txtStock.getText());

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
                    producto = new Bebida(nombre, precio, stock);
                    break;
                case "Comida rápida":
                    producto = new ComidaRápida(nombre, precio, stock);
                break;
                case "Postre":
                    producto = new Postre(nombre, precio, stock);
                    break;
                case "Snack":
                    producto = new Snack(nombre, precio, stock); // Crea esta clase si es necesario
                    break;
                default:
                    throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
            }

            if (!this.listaProductos.contains(producto)){
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
        btnMenuCategorias.setText("Categoría seleccionada: " + categoria);
    }
    
    private void filtrarPorCategoria(String categoria) {
        btnFiltroCategorías.setText("Filtro: " + categoria);
        listaFiltrada.setPredicate(producto -> producto.getCategoría().equals(categoria));
        tblCategorias.setItems(listaFiltrada);
        tblCategorias.refresh();
    }
    
    private void mostrarTodos() {
        btnFiltroCategorías.setText("Mostrar Todos");
        listaFiltrada.setPredicate(null); // Eliminar el filtro para mostrar todos los productos
        tblCategorias.setItems(listaFiltrada);
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
            String categoria = obtenerCategoria(producto);
            this.categoriaSeleccionada = categoria;
            btnMenuCategorias.setText("Categoría seleccionada: " + categoria);
        }
    }
    
    private String obtenerCategoria(ProductoDeVenta producto) {
        if (producto instanceof Bebida) {
            return "Bebida";
        } else if (producto instanceof ComidaRápida) {
            return "Comida rápida";
        } else if (producto instanceof Postre) {
            return "Postre";
        } else if (producto instanceof Snack) {
            return "Snack";
        } else {
            return "Desconocido";
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
                    case "Bebida":
                        auxProducto = new Bebida(nombre, precio, stock);
                        break;
                    case "Comida rápida":
                        auxProducto = new ComidaRápida(nombre, precio, stock);
                    break;
                    case "Postre":
                        auxProducto = new Postre(nombre, precio, stock);
                        break;
                    case "Snack":
                        auxProducto = new Snack(nombre, precio, stock); // Crea esta clase si es necesario
                        break;
                    default:
                        throw new IllegalArgumentException("Categoría desconocida: " + categoriaSeleccionada);
                }
                
                if (!this.listaProductos.contains(auxProducto)){
                    producto.setNombre(auxProducto.getNombre());
                    producto.setPrecio(auxProducto.getPrecio());
                    producto.setStock(auxProducto.getStock());
                    
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
            
            this.listaProductos.remove(producto);
            this.tblCategorias.refresh();
          
        }
    }
    
}
