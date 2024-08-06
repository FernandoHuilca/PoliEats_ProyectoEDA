/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package BusinessLogic;

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
 * @author Fernando_Huilca
 */
public class PantallaInicioBarUsuariosController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private Button Carrito;
    
    @FXML
    public void accederCarrito(){
        cambiarVentana("/Presentation/Carrito.fxml","Pantalla del Carrito");
    }

     

    private void cambiarVentana(String rutaFXML, String titulo) {
        try {
            // Obtener el Stage actual
            Stage currentStage = (Stage) Carrito.getScene().getWindow();

            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(titulo);

        } catch (IOException e) {
            System.out.println("No puedo acceder");
            e.printStackTrace();
        }
    }
    
}
