
package BusinessLogic.Controllers;

import BusinessLogic.EnviarCorreosElectronicos;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SolicitarCuentaAdminController implements Initializable {

    
    private final String archivoAdministradoresContraseñas = "src/Data/Login/NombresContraseniasAdministradores.txt";
    private final int intentosMaximos = 3;
    private int intentosRestantes = intentosMaximos;
    private String codigoVerificacion;

  
    @FXML
    private Button SolicitarButton;
    @FXML
    private Button RegresarButton;
    
    @FXML
     TextField txtNombre;
    @FXML
    private TextField txtCorreo;
    @FXML
     PasswordField txtContraseña;
    
    @FXML
    private TextField txtCodigoVerificacion;
    @FXML
    private Label lblIntentosRestantes;
    
    @FXML
    private Button btnVerificarCodigo;
    
    
    @FXML
    public void Solicitar(){
       String administrador = txtNombre.getText();
        String contraseña = txtContraseña.getText();
        String correo = txtCorreo.getText();    

        // Generar y enviar el código de verificación
        codigoVerificacion = generarCodigoRandom(1000, 9999);
        EnviarCorreosElectronicos.enviarMensajeDeVerificacionDeRegistro(correo, codigoVerificacion, administrador);

        // Mostrar la ventana de verificación
        mostrarVentanaVerificacion();
    }
    private void mostrarVentanaVerificacion() {
        try {
            // Obtener el Stage actual
            Stage currentStage = (Stage) SolicitarButton.getScene().getWindow();

            // Cargar el archivo FXML para Login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/VerificacionCodigo.fxml"));
            Parent root = loader.load();
            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Verificacion");

            // Obtener el controlador de la nueva ventana y pasarle los datos necesarios
            VerificacionCodigoController controller = loader.getController();
            controller.setDatos(codigoVerificacion, intentosMaximos, this);

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
    public void RegresarLongInAdmin(){
        try {
                // Obtener el Stage actual
                Stage currentStage = (Stage) RegresarButton.getScene().getWindow();

                // Cargar el archivo FXML para Login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/LoginAdmin.fxml"));
                Parent root = loader.load();

                // Cambiar la escena del Stage actual
                currentStage.setScene(new Scene(root));
                currentStage.setTitle("LoginAdmin");

            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No se pudo cargar la interfaz de usuario.");
                alert.showAndWait();
            }
    }
    
    public void guardarAdministradoresYRegresar(String administrador, String contraseña) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoAdministradoresContraseñas, true))) {
            writer.write(administrador);
            writer.newLine();
            writer.write(contraseña);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo guardar el usuario en el archivo.");
            alert.showAndWait();
            return;
        }
    }

    private static String generarCodigoRandom(int limiteInferior, int limiteSuperior) {
        Random random = new Random();
        return Integer.toString(random.nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
