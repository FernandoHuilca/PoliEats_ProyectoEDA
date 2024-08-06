/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class CarritoController implements Initializable {
    
    @FXML
    private Button RegresarButton;
    @FXML
    private Button confirmarYPagar;
    
    
    @FXML
    public void RegresarInterzasPrincipal(){
        cambiarVentana("/Presentation/PantallaInicioBarUsuarios.fxml","Pantalla Inicio del Bar");
    }
    @FXML
    public void irALaInterfazDePago(){
        cambiarVentana("/Presentation/PantallaDeCompra.fxml","Pantalla Finalización de Compra");
    }
    
    private void cambiarVentana(String rutaFXML, String titulo) {
        try {
            // Obtener el Stage actual
            Stage currentStage = (Stage) RegresarButton.getScene().getWindow();

            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(titulo);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la interfaz de usuario.");
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
