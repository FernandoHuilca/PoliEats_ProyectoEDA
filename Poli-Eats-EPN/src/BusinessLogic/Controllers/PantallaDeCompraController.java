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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class PantallaDeCompraController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private Button regresarCarritoButton; 
    
    @FXML
    public void regresarALaPantallaDelCarrito(){
        cambiarVentana("/Presentation/Carrito.fxml","Pantalla del Bar");
    }

    private void cambiarVentana(String rutaFXML, String titulo) {
        try {
            // Obtener el Stage actual
            Stage currentStage = (Stage) regresarCarritoButton.getScene().getWindow();

            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(titulo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
