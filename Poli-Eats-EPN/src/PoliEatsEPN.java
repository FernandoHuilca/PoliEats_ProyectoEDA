// Import statements...
import BusinessLogic.Administrador;
import BusinessLogic.AppBarPoliEats;
import BusinessLogic.Bebida;
import BusinessLogic.ComidaRápida;
import BusinessLogic.Controllers.SaludoBienvenidaController;
import BusinessLogic.Fruta;
import BusinessLogic.Ganancia;
import BusinessLogic.Otro;
import BusinessLogic.Postre;
import BusinessLogic.ProductoDeVenta;
import BusinessLogic.Snack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
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
        cargarGananciasDesdeArchivo();

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
     // Nuevo método para cargar ganancias desde un archivo
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

                appBarPoliEats.agregarGanancia(nuevaGanancia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
