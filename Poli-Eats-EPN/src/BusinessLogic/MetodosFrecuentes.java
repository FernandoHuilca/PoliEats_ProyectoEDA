package BusinessLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class MetodosFrecuentes {
    
         private static OrdenamientoPorMergeSort ordenamientoPorMergeSort = new OrdenamientoPorMergeSort();
         private AppBarPoliEats app = AppBarPoliEats.getInstance();

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
    private void cargarGananciasDesdeArchivo() {
        String archivoGanancias = "src/Data/Ganancias/GananciasFechas.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoGanancias))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Leer año, mes, día y ganancia de la línea
                int año = Integer.parseInt(linea);
                int mes = Integer.parseInt(reader.readLine());
                int dia = Integer.parseInt(reader.readLine());
                double ganancia = Double.parseDouble(reader.readLine());

                LocalDate fecha = LocalDate.of(año, mes, dia);
                Ganancia nuevaGanancia = new Ganancia(fecha, ganancia);

                app.agregarGanancia(nuevaGanancia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void guardarGananciasEnArchivo() {
        String archivoGanancias = "src/Data/Ganancias/GananciasFechas.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoGanancias))) {
            for (int i = 0; i < AppBarPoliEats.getInstance().getContadorDeGanancias(); i++) {
                Ganancia ganancia = AppBarPoliEats.getInstance().getGanancia(i);
                LocalDate fecha = ganancia.getFecha();
                
                // Escribir la fecha y la ganancia en el archivo
                writer.write(fecha.getYear() + "\n");
                writer.write(fecha.getMonthValue() + "\n");
                writer.write(fecha.getDayOfMonth() + "\n");
                writer.write(ganancia.getTotalGanancia() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
