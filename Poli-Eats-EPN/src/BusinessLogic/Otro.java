/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLogic;

/**
 *
 * @author Fernando_Huilca
 */
public class Otro extends ProductoDeVenta {

     public Otro(String nombre, double precio, int stock, String rutaImagen) {
        super(nombre, precio, stock, rutaImagen);
    }

    @Override
    public Categoría getCategoría() {
        return Categoría.OTRO;
    }
}
