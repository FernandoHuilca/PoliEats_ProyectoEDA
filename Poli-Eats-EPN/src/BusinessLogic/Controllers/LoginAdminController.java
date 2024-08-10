package BusinessLogic.Controllers;

import BusinessLogic.Administrador;
import BusinessLogic.AppBarPoliEats;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginAdminController implements Initializable {

    @FXML
    private Button SolicitarButton;
    @FXML
    private Button LogIn;
    @FXML
    private TextField txtAdministrador;
    @FXML
    private PasswordField txtContraseña;

    @FXML
    public void SolicitarCuentaAdmin() {
        cambiarVentana("/Presentation/SolicitarCuentaAdmin.fxml", "Solicitar Cuenta");
    }

    @FXML
    public void LoginAcceso() {
        String usuario = txtAdministrador.getText();
        String contraseña = txtContraseña.getText();
        boolean credencialesValidas = false;

        AppBarPoliEats appBarPoliEats = AppBarPoliEats.getInstance();
        for (int i = 0 ; i < appBarPoliEats.getContadorAdministradores(); i++) {
            Administrador admin = appBarPoliEats.getAdministradores()[i];
            System.out.println("nombre: " + admin.getNombre() + "  usuario: " + usuario);
            if (admin.getNombre().equals(usuario) && admin.getContraseña().equals(contraseña)) {
                credencialesValidas = true;
                break;
            }
        }

        if (credencialesValidas) {
            cambiarVentana("/Presentation/PantallaInicioBarUsuarios.fxml", "Pantalla de Inicio Bar");
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.");
        }
    }

    private void cambiarVentana(String rutaFXML, String titulo) {
        try {
            // Obtener el Stage actual
            Stage currentStage = (Stage) LogIn.getScene().getWindow();

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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
