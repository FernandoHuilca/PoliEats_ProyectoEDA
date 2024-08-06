/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import BusinessLogic.Controllers.SaludoBienvenidaController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fernando_Huilca
 */
public class PoliEatsEPN extends Application{
  @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/SaludoBienvenida.fxml"));
        Parent root = loader.load();
        // Hola
        SaludoBienvenidaController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.start();
        
        primaryStage.setTitle("Bienvenida");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
    
}
