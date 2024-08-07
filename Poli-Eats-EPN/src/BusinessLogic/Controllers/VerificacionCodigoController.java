package BusinessLogic.Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VerificacionCodigoController {

    @FXML
    private TextField txtCodigoVerificacion;
    @FXML
    private Button btnVerificarCodigo;
    @FXML
    private Button regresar;
    @FXML
    private Label lblIntentosRestantes;

    private String codigoVerificacion;
    private int intentosRestantes;
    private SolicitarCuentaAdminController solicitarCuentaAdminController;
    private String administrador;
    private String contraseña;

    public void setDatos(String codigoVerificacion, int intentosMaximos, SolicitarCuentaAdminController solicitarCuentaAdminController) {
        this.codigoVerificacion = codigoVerificacion;
        this.intentosRestantes = intentosMaximos;
        this.solicitarCuentaAdminController = solicitarCuentaAdminController;
        this.administrador = this.solicitarCuentaAdminController.txtNombre.getText();
        this.contraseña = this.solicitarCuentaAdminController.txtContraseña.getText();
        actualizarIntentosRestantes();
    }

    
    @FXML
    public void regresarLongIn() {
       try {
            // Obtener el Stage actual
            Stage currentStage = (Stage) regresar.getScene().getWindow();

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
    
    @FXML
    public void verificarCodigo() {
        String codigoIngresado = txtCodigoVerificacion.getText();

        if (codigoVerificacion.equals(codigoIngresado)) {
            solicitarCuentaAdminController.guardarAdministradoresYRegresar(administrador, contraseña);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcto");
            alert.setHeaderText(null);
            alert.setContentText("El código es correcto, se ha creado su cuenta.");
            alert.showAndWait();
            regresarLongIn();
            
        } else {
            intentosRestantes--;
            if (intentosRestantes <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Número máximo de intentos alcanzado.");
                alert.showAndWait();
                regresarLongIn();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Código incorrecto. Intentos restantes: " + intentosRestantes);
                alert.showAndWait();
                actualizarIntentosRestantes();
            }
        }
    }

    private void actualizarIntentosRestantes() {
        lblIntentosRestantes.setText("Intentos restantes: " + intentosRestantes);
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnVerificarCodigo.getScene().getWindow();
        stage.close();
    }
}
