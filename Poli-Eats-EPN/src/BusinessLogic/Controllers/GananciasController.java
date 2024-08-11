package BusinessLogic.Controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Ganancia;
import BusinessLogic.MetodosFrecuentes;
import java.time.Month;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GananciasController implements Initializable {

    // Obtener la instancia de AppBarPoliEats
        AppBarPoliEats app = AppBarPoliEats.getInstance();
    @FXML
    private BarChart<String, Number> BarrasGananciasDias;
    @FXML
    private Button ButtonRegresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usoEjemplo();
        cargarDatosDeGanancias();
    }
    
    private void usoEjemplo(){
        //INDICACIONES
    //FECHA ACTUAL
    LocalDate fechaActual = LocalDate.now();
    double ganancia1 = 102.2;
    //FECHA ESPECÍFICA 
    LocalDate fechaEspecifica = LocalDate.of(2024, 8, 9);
    double ganancia2 = 202.58;
    //FEHCA STRING 
    //LocalDate fechaDesdeCadena = LocalDate.parse("2024-08-8");
    double ganancia3 = 130.98;
    LocalDate fechaEspecifica2 = LocalDate.of(2024, 8, 8);

    
    Ganancia nuevaGanancia1 = new Ganancia(fechaActual,ganancia1);
    Ganancia nuevaGanancia2 = new Ganancia(LocalDate.of(2024, Month.MARCH, 6),40.9);
    Ganancia nuevaGanancia3 = new Ganancia(fechaEspecifica2,ganancia3);
    Ganancia nuevaGanancia4 = new Ganancia(LocalDate.of(2024, Month.MARCH, 7),ganancia3);
    Ganancia nuevaGanancia5 = new Ganancia(fechaEspecifica,ganancia2);

    app.agregarGanancia(nuevaGanancia1);
    app.agregarGanancia(nuevaGanancia2);
    app.agregarGanancia(nuevaGanancia3);
    app.agregarGanancia(nuevaGanancia4);
    app.agregarGanancia(nuevaGanancia5);
    }
    
    
    private void cargarDatosDeGanancias() {
        
        // Crear una nueva serie para la gráfica de barras
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Ganancias Diarias");

        // Llenar la serie con los datos de ganancias
        for (int i = 0; i < app.getContadorDeGanancias(); i++) {
            Ganancia ganancia = app.getGanancia(i);
            if (ganancia != null) {
                LocalDate fecha = ganancia.getFecha();
                double totalGanancia = ganancia.getTotalGanancia();
                
                // Añadir un dato a la serie (fecha como X, totalGanancia como Y)
                series.getData().add(new XYChart.Data<>(fecha.toString(), totalGanancia));
            }
        }

        // Añadir la serie a la gráfica de barras
        BarrasGananciasDias.getData().add(series);
    }

    @FXML
    private void RegresarAnteriorPeztania(ActionEvent event) {
        MetodosFrecuentes.cambiarVentana((Stage)ButtonRegresar.getScene().getWindow(), "/Presentation/PantallaInicioBarUsuarios.fxml", "Inicio");
    }

}
