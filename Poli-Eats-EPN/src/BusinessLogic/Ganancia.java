
package BusinessLogic;
import java.time.LocalDate;

public class Ganancia {
    private LocalDate fecha;
    private double totalGanancia;
    private int pedidosVendidos;

    // Constructor
    public Ganancia(LocalDate fecha, double totalGanancia) {
        this.fecha = fecha;
        this.totalGanancia = totalGanancia;
        this.pedidosVendidos = 1;
    }

    // Getters
    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotalGanancia() {
        return totalGanancia;
    }

    // Método para añadir una ganancia al total existente
    public void añadirGanancia(double ganancia) {
        if (ganancia < 0) {
            throw new IllegalArgumentException("La ganancia no puede ser negativa.");
        }
        this.totalGanancia += ganancia;
        pedidosVendidos++;
        
    }

    public void setPedidosVendidos(int pedidos){
        
    }
    public int getPedidosVendidos(){
        return pedidosVendidos;
    }
    
    
    // Método para mostrar la información de la ganancia
    @Override
    public String toString() {
        return "Fecha: " + fecha + ", Total Ganancia: $" + totalGanancia;
    }
}
