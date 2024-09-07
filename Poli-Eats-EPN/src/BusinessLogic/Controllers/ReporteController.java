package BusinessLogic.Controllers;

import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Ganancia;
import BusinessLogic.MetodosFrecuentes;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReporteController implements Initializable {

    AppBarPoliEats app = AppBarPoliEats.getInstance();
    
    @FXML
    private Button ButtonRegresar;
    
    @FXML
    private TableView<Ganancia> TablaReporte;
    
    @FXML
    private TableColumn colFecha;
    
    @FXML
    private TableColumn colPedidos;
    
    @FXML
    private TableColumn colGanancias;

    private ObservableList<Ganancia> listaGanancias;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializar las columnas
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colPedidos.setCellValueFactory(new PropertyValueFactory<>("pedidosVendidos"));
        colGanancias.setCellValueFactory(new PropertyValueFactory<>("totalGanancia"));

        
       
        // Cargar datos de ejemplo (reemplazar con datos reales)
        listaGanancias = FXCollections.observableArrayList();
        for (int i = 0 ; i < app.getContadorDeGanancias(); i++){
            listaGanancias.add(app.getGanancia(i));
        }
        TablaReporte.setItems(listaGanancias);
        
        // Asignar la lista a la tabla
        TablaReporte.setItems(listaGanancias);
    }    

    @FXML
    private void regresarGananciasGeneral(ActionEvent event) {
                MetodosFrecuentes.cambiarVentana((Stage) ButtonRegresar.getScene().getWindow(), "/Presentation/Ganancias.fxml", "Ganancia");

    }
}
