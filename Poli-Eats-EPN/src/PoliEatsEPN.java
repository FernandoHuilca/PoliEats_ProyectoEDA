// Import statements...
import BusinessLogic.Administrador;
import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebida;
import BusinessLogic.ComidaRápida;
import BusinessLogic.Controllers.SaludoBienvenidaController;
import BusinessLogic.Fruta;
import BusinessLogic.Otro;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PoliEatsEPN extends Application {
    AppBarPoliEats appBarPoliEats = AppBarPoliEats.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargar todos los datos del txt
        cargarAdministradores();
        cargarProductos();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Presentation/SaludoBienvenida.fxml"));
        Parent root = loader.load();

        SaludoBienvenidaController controller = loader.getController();
        controller.setStage(primaryStage);
        controller.start();

        primaryStage.setTitle("Bienvenida");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void cargarAdministradores() {
        String archivoAdministradores = "src/Data/Login/NombresContraseniasAdministradores.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoAdministradores))) {
            String nombre;
            while ((nombre = reader.readLine()) != null) {
                String correo = reader.readLine();
                String contraseña = reader.readLine();

                Administrador nuevoAdministrador = new Administrador(nombre, correo, contraseña);
                appBarPoliEats.agregarAdministrador(nuevoAdministrador);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarProductos() {
        // Lista de archivos de productos y sus tipos correspondientes
        String[][] archivosProductos = {
            {"src/Data/ProductosDeVenta/ProductosBebidas.txt", "Bebida"},
            {"src/Data/ProductosDeVenta/ProductosComidaRapida.txt", "ComidaRapida"},
            {"src/Data/ProductosDeVenta/ProductosPostre.txt", "Postre"},
            {"src/Data/ProductosDeVenta/ProductosSnacks.txt", "Snack"},
            {"src/Data/ProductosDeVenta/ProductosFruta.txt", "Fruta"},
            {"src/Data/ProductosDeVenta/ProductosOtro.txt", "Otro"}
            // Agrega más archivos y tipos según sea necesario
        };

        for (String[] archivoYTipo : archivosProductos) {
            cargarProductosDeArchivo(archivoYTipo[0], archivoYTipo[1]);
        }
    }

    private void cargarProductosDeArchivo(String archivo, String tipoProducto) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String nombre;
            while ((nombre = reader.readLine()) != null) {
                String precio = reader.readLine();
                String stock = reader.readLine();
                String imagen = reader.readLine(); // Nueva línea para la dirección de la imagen

                ProductoDeVenta nuevoProducto;
                switch (tipoProducto) {
                    case "Bebida":
                        nuevoProducto = new Bebida(nombre, Double.parseDouble(precio), Integer.parseInt(stock), imagen);
                        break;
                    case "ComidaRapida":
                        nuevoProducto = new ComidaRápida(nombre, Double.parseDouble(precio), Integer.parseInt(stock), imagen);
                        break;
                    case "Postre":
                        nuevoProducto = new Postre(nombre, Double.parseDouble(precio), Integer.parseInt(stock), imagen);
                        break;
                    case "Snack":
                        nuevoProducto = new Snack(nombre, Double.parseDouble(precio), Integer.parseInt(stock), imagen);
                        break;
                    case "Fruta":
                        nuevoProducto = new Fruta(nombre, Double.parseDouble(precio), Integer.parseInt(stock), imagen);
                        break;
                    case "Otro":
                        nuevoProducto = new Otro(nombre, Double.parseDouble(precio), Integer.parseInt(stock), imagen);
                        break;
                    // Agrega más casos según sea necesario
                    default:
                        throw new IllegalArgumentException("Tipo de producto desconocido: " + tipoProducto);
                }

                appBarPoliEats.agregarProductoDerecha(nuevoProducto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
