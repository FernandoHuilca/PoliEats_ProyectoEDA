/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Item;
import BusinessLogic.MetodosFrecuentes;
import BusinessLogic.Pedido;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * PantallaProductos.fxml
 *
 * @author Ali
 */
public class PantallaDelAdministradorController implements Initializable {

    AppBarPoliEats bar = AppBarPoliEats.getInstance();
    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private TableColumn<Pedido, Integer> colNumeroPedido;
    @FXML
    private TableColumn<Pedido, String> colNombre;
    @FXML
    private TableColumn colTotal;
    @FXML
    private Button botonAnadirPedido;
    @FXML
    private Button botonEliminarPedido;
    @FXML
    private Button botonDeProductos;
    @FXML
    private Button btnRegresar;
    
    private ObservableList<Pedido> pedidos;
    @FXML
    private TableColumn<Pedido, String> colProducto;
    @FXML
    private TableColumn<Pedido, String> colCantidad;
    @FXML
    private Label lblUsuario;
    @FXML
    private Button btnGanancias;
 


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidos = FXCollections.observableArrayList();
        
        // Configurar la columna para el número de pedido
        this.colNumeroPedido.setCellValueFactory(column -> 
            new ReadOnlyObjectWrapper<>(tablaPedidos.getItems().indexOf(column.getValue()) + 1)
        );

        ingresarSetCellValueFactory(this.colNombre, "nombreDelCliente");
        
        // Configurar las columnas de productos y cantidades
        this.colProducto.setCellValueFactory(cellData -> 
            new SimpleStringProperty(getFormattedProducts(cellData.getValue()))
        );
        this.colCantidad.setCellValueFactory(cellData -> 
            new SimpleStringProperty(getFormattedQuantities(cellData.getValue()))
        );
      
        ingresarSetCellValueFactory(this.colTotal, "valorTotalAPagar");
        
        // Cargar los pedidos desde el singleton
        cargarPedidos();
    }
    
    
    // Método para obtener una cadena formateada con los nombres de los productos
    private String getFormattedProducts(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pedido.getNúmeroDeItems(); i++) {
            sb.append(pedido.getProductos(i).getNombre());
            sb.append("\n"); // Nueva línea después de cada producto
        }
        return sb.toString();
    }

    // Método para obtener una cadena formateada con las cantidades de los productos
    private String getFormattedQuantities(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pedido.getNúmeroDeItems(); i++) {
            sb.append(pedido.getItems().getDato(i).getCantidadDeEsteProducto());
            sb.append("\n"); // Nueva línea después de cada cantidad
        }
        return sb.toString();
    }

    
    
    private void ingresarSetCellValueFactory(TableColumn columna, String nombreDelAtributo){
        columna.setCellValueFactory(new PropertyValueFactory(nombreDelAtributo));
    }


    private void cargarPedidos() {
        pedidos.clear(); // Limpia la lista actual para evitar duplicados
        for (int i = 0; i < bar.getNúmeroDePedidos(); i++) {
            pedidos.add(bar.getPedido(i));
        }
        tablaPedidos.setItems(pedidos);
    }

    public void actualizarTabla() {
        cargarPedidos();
        tablaPedidos.refresh();
    }

    
    @FXML
    public void anadirPedido(){
        MetodosFrecuentes.cambiarVentana(((Stage) botonAnadirPedido.getScene().getWindow()), "/Presentation/GenerarPedido.fxml", "Pantalla Generar Pedido");
    }
    
    @FXML
    public void eliminarPedido() {
        if (!pedidos.isEmpty()) {
            // Elimina el primer pedido de la cola de pedidos en el modelo de datos
            Pedido pedidoEliminado = bar.eliminarPedido();

            // Elimina el primer pedido de la lista observable
            pedidos.remove(0);

            // Refresca la tabla para reflejar los cambios
            tablaPedidos.refresh();
        } else {
            // Aquí podrías mostrar un mensaje indicando que no hay pedidos para eliminar
            System.out.println("No hay pedidos para eliminar.");
        }
    }
    
      public void setUsuario(String nombre) {
        lblUsuario.setText(nombre);
    }
    
    @FXML
    public void dirigirAPantallaDeProductos(){
        MetodosFrecuentes.cambiarVentana(((Stage) botonDeProductos.getScene().getWindow()), "/Presentation/PantallaProductos.fxml", "Pantalla De Productos");
    }

    @FXML
    private void regresarAPantallaDelLogin(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage) btnRegresar.getScene().getWindow(), "/Presentation/LoginAdmin.fxml", "Pantalla del Login");
    }

    @FXML
    private void dirigirAPantallaDeGananacias(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana(((Stage) btnGanancias.getScene().getWindow()), "/Presentation/ganancias.fxml", "Pantalla De Productos");
    }
}