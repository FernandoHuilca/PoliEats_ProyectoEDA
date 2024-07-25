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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginAdminOrUserController implements Initializable {

    @FXML
    private Button botonAdmin;
    @FXML
    private Button botonUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonActionAdminLogin() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Bienvenida Administrador");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenido");
        alert.showAndWait();
    }

    @FXML
    private void handleButtonActionUserLogin() {
            try {
                // Obtener el Stage actual
                Stage currentStage = (Stage) botonUser.getScene().getWindow();

                // Cargar el archivo FXML para LoginUsuario
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/LoginUsuario.fxml"));
                Parent root = loader.load();

                // Cambiar la escena del Stage actual
                currentStage.setScene(new Scene(root));
                currentStage.setTitle("Login Usuario");

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo cargar la interfaz de usuario.");
                alert.showAndWait();
            }
    }
}
