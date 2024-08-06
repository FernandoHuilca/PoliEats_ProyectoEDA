
package BusinessLogic.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
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
    private final String archivoAdministradoresContraseñas = "src/Data/Login/NombresContraseniasAdministradores.txt";
    @FXML
    private Button RegresarButton;
    @FXML
    private Button SolicitarButton;
     @FXML
    private Button LogIn;
    @FXML
    private TextField txtAdministrador;
    @FXML
    private PasswordField txtContraseña;

    
    @FXML
    public void RegresarLoginAdmOrUser(){
        cambiarVentana("/Presentation/LoginAdminOrUser.fxml","Login admin");
    }
    
    @FXML
    public void SolicitarCuentaAdmin(){
        cambiarVentana("/Presentation/SolicitarCuentaAdmin.fxml","SolicitarCuenta"); 
    }
    @FXML
    public void LoginAcceso() {
        String usuario = txtAdministrador.getText();
        String contraseña = txtContraseña.getText();
        boolean credencialesValidas = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoAdministradoresContraseñas))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String archivoUsuario = linea;
                String archivoContraseña = reader.readLine();

                if (usuario.equals(archivoUsuario) && contraseña.equals(archivoContraseña)) {
                    credencialesValidas = true;
                    break;
                }
            }
        } catch (IOException e) {
            mostrarAlerta("Error", "Ocurrió un error al leer el archivo.");
            e.printStackTrace();
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
