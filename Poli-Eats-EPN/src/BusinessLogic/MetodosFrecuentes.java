package BusinessLogic;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MetodosFrecuentes {
    
         private static OrdenamientoPorMergeSort ordenamientoPorMergeSort = new OrdenamientoPorMergeSort();

    public static void cambiarVentana(Stage currentStage, String rutaFXML, String titulo) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(MetodosFrecuentes.class.getResource(rutaFXML));
            Parent root = loader.load();

            // Cambiar la escena del Stage actual
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(titulo);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la interfaz de usuario.");
            e.printStackTrace();
        }
    }

    public static void ordenarMegerSort(int[] arreglo){
        ordenamientoPorMergeSort.ordenar(arreglo);
    }
    
    public static void ordenarMegerSort(double[] arreglo){
        ordenamientoPorMergeSort.ordenar(arreglo);
    }
    
    public static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
