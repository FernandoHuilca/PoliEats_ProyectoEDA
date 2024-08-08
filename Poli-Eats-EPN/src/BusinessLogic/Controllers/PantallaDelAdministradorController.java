/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import BusinessLogic.MetodosFrecuentes;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * PantallaProductos.fxml
 *
 * @author PC
 */
public class PantallaDelAdministradorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private Button botonAnadirPedido;
    @FXML
    private Button botonEliminarPedido;
    @FXML
    private Button botonDeProductos;
    
    
    @FXML
    public void anadirPedido(){
        MetodosFrecuentes.cambiarVentana(((Stage) botonAnadirPedido.getScene().getWindow()), "/Presentation/GenerarPedido.fxml", "Pantalla Generar Pedido");
    }
    @FXML 
    public void eliminarPedido(){
        
        MetodosFrecuentes.mostrarAlerta("Ventana de Comprobacion", "Comprobar que si entro");
    }
    @FXML
    public void dirigirAPantallaDeProductos(){
        MetodosFrecuentes.cambiarVentana(((Stage) botonDeProductos.getScene().getWindow()), "/Presentation/PantallaProductos.fxml", "Pantalla De Productos");
    }
}