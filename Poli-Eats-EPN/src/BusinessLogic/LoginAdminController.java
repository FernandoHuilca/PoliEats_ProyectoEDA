
package BusinessLogic;

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


public class LoginAdminController implements Initializable {

    @FXML
    private Button RegresarButton;
    @FXML
    private Button SolicitarButton;
    
    @FXML
    public void RegresarLoginAdmOrUser(){
        try {
                // Obtener el Stage actual
                Stage currentStage = (Stage) RegresarButton.getScene().getWindow();

                // Cargar el archivo FXML para Login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/LoginAdminOrUser.fxml"));
                Parent root = loader.load();

                // Cambiar la escena del Stage actual
                currentStage.setScene(new Scene(root));
                currentStage.setTitle("Login");

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo cargar la interfaz de usuario.");
                alert.showAndWait();
            }
    }
    
    @FXML
    public void SolicitarCuentaAdmin(){
         try {
                // Obtener el Stage actual
                Stage currentStage = (Stage) SolicitarButton.getScene().getWindow();

                // Cargar el archivo FXML para Login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/SolicitarCuentaAdmin.fxml"));
                Parent root = loader.load();

                // Cambiar la escena del Stage actual
                currentStage.setScene(new Scene(root));
                currentStage.setTitle("SolicitarCuenta");

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo cargar la interfaz de usuario.");
                alert.showAndWait();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
