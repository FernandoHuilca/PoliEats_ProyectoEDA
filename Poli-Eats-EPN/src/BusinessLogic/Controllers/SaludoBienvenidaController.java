package BusinessLogic.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class SaludoBienvenidaController implements Initializable {

    private Stage stage;

    @FXML
    private ProgressBar progressBar;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Este método se deja vacío ya que la inicialización se hace en el método start
    }

    public void start() {
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        final int loadingTime = 2000; // 2 segundos
        final int steps = 100;
        final double stepTime = loadingTime / (double) steps;

        new Thread(() -> {
            for (int i = 0; i <= steps; i++) {
                final int progress = i;
                Platform.runLater(() -> progressBar.setProgress(progress / (double) steps));
                try {
                    Thread.sleep((long) stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Platform.runLater(() -> showExplanationScreen());
        }).start();
    }

    private void showExplanationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/LoginAdmin.fxml"));
            Parent root = loader.load();
            // Cambia la escena en el Stage actual
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
